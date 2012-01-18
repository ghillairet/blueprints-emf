/*******************************************************************************
 * Copyright (c) 2011 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Hillairet - initial API and implementation
 *******************************************************************************/
package org.eclipselabs.blueprints.emf.internal;

import static org.eclipselabs.blueprints.emf.util.GraphUtil.safeURI;
import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_INDEX_KEY;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter.Loadable;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.URIHandler;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.eclipselabs.blueprints.emf.util.Tokens;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.Vertex;

/**
 * 
 * @author ghillairet
 */
public class GraphInputStream extends InputStream implements Loadable {

	private final IndexableGraph graph;
	private final Map<?, ?> options;
	private final Index<Vertex> vertexIndex;
	@SuppressWarnings("unused")
	private final Index<Edge> edgeIndex;
	
	public GraphInputStream(IndexableGraph graph, Index<Vertex> vertexIndex, Index<Edge> edgeIndex, Map<?,?> options) {
		this.graph = graph;
		this.vertexIndex = vertexIndex;
		this.edgeIndex = edgeIndex;
		this.options = options;
	}

	@Override
	public void loadResource(Resource resource) throws IOException {
		XMLResource.URIHandler uriHandler = (XMLResource.URIHandler) options.get(XMLResource.OPTION_URI_HANDLER);

		if (uriHandler == null)
			uriHandler = new org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl();

		if (resource.getURI().hasQuery())
			uriHandler.setBaseURI(resource.getURI().trimSegments(1).appendSegment("-1"));
		else
			uriHandler.setBaseURI(resource.getURI());

		Iterable<Vertex> vertices;
		if (resource.getURI().hasFragment()) {
			vertices = vertexIndex.get(BLUEPRINTS_EMF_INDEX_KEY, safeURI(resource.getURI()));
		} else {
			vertices = findRootVertex(resource);
		}

		EList<EObject> contents = resource.getContents();

		for (Vertex vertex: vertices) {

			URI vertexURI = GraphUtil.getURI(vertex);
			final EObject obj;

			if (vertexURI.hasFragment()) {
				obj = resource.getEObject(vertexURI.fragment());
			} else {
				obj = resource.getResourceSet().getEObject(vertexURI, false);
			}

			if (obj == null) {
				EObject object = buildEObject(vertex, resource, uriHandler, false);
				if (object != null) {
					contents.add(object);
				}
			}
		}
	}

	protected EObject buildEObject(Vertex vertex, Resource resource, XMLResource.URIHandler uriHandler, boolean isProxy) {
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}

		EObject object = createEObject(vertex, resource.getResourceSet());

		if (isProxy) {
			URI eURI = GraphUtil.getURI(vertex);
			if (eURI != null) {
				((InternalEObject)object).eSetProxyURI(uriHandler.resolve(eURI));
			}
		}

		for (EStructuralFeature feature: object.eClass().getEAllStructuralFeatures()) {
			if (feature instanceof EAttribute) 
			{
				EAttribute attribute = (EAttribute) feature;

				if (!isProxy || !FeatureMapUtil.isFeatureMap(attribute))
					buildEObjectAttribute(vertex, object, attribute);
			} 
			else if (!isProxy) 
			{
				EReference reference = (EReference) feature;

				if (!reference.isTransient())
					buildEObjectReference(vertex, object, reference, resource, uriHandler);
			}
		}
		return object;
	}

	private void buildEObjectReference(Vertex vertex, EObject object, EReference reference, Resource resource, XMLResource.URIHandler uriHandler) {

		final Iterable<Edge> edges = vertex.getOutEdges(reference.getName());
		boolean isResolveProxies = 	reference.isResolveProxies();

		if (edges.iterator().hasNext()) {

			if (reference.isMany()) {	
				@SuppressWarnings("unchecked")
				EList<EObject> values = (EList<EObject>) object.eGet(reference);

				for (Edge edge: edges) {

					EObject target = buildEObjectReference(edge, resource, uriHandler, isResolveProxies);

					if (target != null) {
						values.add(target);
					}
				}

			} else {
				Edge edge = edges.iterator().next();

				EObject target = buildEObjectReference(edge, resource, uriHandler, isResolveProxies);

				if (target != null) {
					object.eSet(reference, target);
				}
			}
		}
	}

	private EObject buildEObjectReference(Edge edge, Resource resource, XMLResource.URIHandler uriHandler, boolean isResolveProxies) {
		Vertex vertex = edge.getInVertex();
		if (vertex == null) {
			return null;
		}

		Object objectURI = vertex.getProperty(Tokens.BLUEPRINTS_EMF_PROXY_URI);
		if (objectURI != null) {
			return buildProxy(vertex, resource, uriHandler, isResolveProxies);	
		} else {
			return buildEObject(vertex, resource, uriHandler, isResolveProxies);
		}
	}

	private EObject buildProxy(Vertex vertex, Resource resource, URIHandler uriHandler, boolean isResolveProxies) {
		EObject eObject;
		URI vertexURI = GraphUtil.getURI(vertex);
		URI resolvedProxyURI = uriHandler.resolve(vertexURI);

		if (!isResolveProxies) {
			eObject = resource.getResourceSet().getEObject(resolvedProxyURI, true);
		} else {
			eObject = createEObject(vertex, resource.getResourceSet());
		}

		return eObject;
	}

	private void buildEObjectAttribute(Vertex vertex, EObject object, EAttribute attribute) {
		if (!attribute.isTransient() && vertex.getPropertyKeys().contains(attribute.getName())) {

			Object rawValue = vertex.getProperty(attribute.getName());

			if (attribute.isMany()) {
				ArrayList<Object> values = new ArrayList<Object>();
				EDataType eDataType = attribute.getEAttributeType();

				if (rawValue.getClass().isArray()) {
					Object[] objects = (Object[]) rawValue;

					for (Object o: objects) {
						values.add(EcoreUtil.createFromString(eDataType, o.toString()));
					}

				} else if (rawValue.getClass().isAssignableFrom(Collection.class)) {
					Collection<?> objects = (Collection<?>) rawValue;

					for (Object o: objects) {
						values.add(EcoreUtil.createFromString(eDataType, o.toString()));
					}
				}

				object.eSet(attribute, values);

			} else {
				object.eSet(attribute, EcoreUtil.createFromString(attribute.getEAttributeType(), rawValue.toString()));
			}
		}
	}

	protected EObject createEObject(Vertex vertex, ResourceSet resourceSet) {
			final Object eClassURI = vertex.getProperty(Tokens.BLUEPRINTS_EMF_ECLASS);
			if (eClassURI == null) {
				return null;
			}

			EClass eClass = (EClass) resourceSet.getEObject(URI.createURI(eClassURI.toString()), true);

			if (eClass == null) {
				return null;
			}

			return EcoreUtil.create(eClass);
	}

	protected Iterable<Vertex> findRootVertex(Resource resource) {
		Iterable<Vertex> vertices = vertexIndex.get(Tokens.RESOURCE_URI, resource.getURI().toString());

		if (vertices == null || !vertices.iterator().hasNext())
			vertices = graph.getVertices();

		Collection<Vertex> roots = new ArrayList<Vertex>();

		for (Vertex vertex: vertices) {
			URI vertexURI = GraphUtil.getURI(vertex);

			if (vertexURI != null) {
				if (vertexURI.equals(resource.getURI().appendFragment("/"))) {
					roots.add(vertex);
				} else {
					if (vertexURI.hasFragment()) {
						String fragment = vertexURI.fragment();
						String[] fragments = fragment.split("/");
						if (fragments.length  < 2) {
							roots.add(vertex);
						}
					}
				}
			}
		}

		return roots.isEmpty() ? vertices : roots; 
	}
	@Override
	public int read() throws IOException {
		return 0;
	}

}
