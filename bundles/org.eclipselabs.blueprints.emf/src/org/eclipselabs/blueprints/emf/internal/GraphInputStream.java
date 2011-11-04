package org.eclipselabs.blueprints.emf.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter.Loadable;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;

public class GraphInputStream extends InputStream implements Loadable {
	
	private final Graph graph;
	private final Map<?, ?> options;

	public GraphInputStream(Graph graph, Map<?,?> options) {
		this.graph = graph;
		this.options = options;
	}
	
	@Override
	public void loadResource(Resource resource) throws IOException {
		XMLResource.URIHandler uriHandler = (XMLResource.URIHandler) options.get(XMLResource.OPTION_URI_HANDLER);
		
		if (uriHandler == null)
			uriHandler = new org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl();
		
		for (Vertex vertex: graph.getVertices()) {
			EObject object = buildEObject(vertex, resource, uriHandler);
			
			if (object != null && object.eContainer() == null) {
				resource.getContents().add(object);
			}
			
		}
	}
	
	protected EObject buildEObject(Vertex vertex, Resource resource, XMLResource.URIHandler uriHandler) {
		ResourceSet resourceSet = resource.getResourceSet();
		
		EObject object = createEObject(vertex, resourceSet);
		if (object == null) {
			return null;
		}
		
		for (EStructuralFeature feature: object.eClass().getEAllStructuralFeatures()) {
			if (feature instanceof EAttribute) {
				EAttribute attribute = (EAttribute) feature;
				
				buildEObjectAttribute(vertex, object, attribute);
			} else {
				EReference reference = (EReference) feature;
				
				buildEObjectReference(vertex, object, reference, resource, uriHandler);
			}
		}
		return object;
	}
	
	private void buildEObjectReference(Vertex vertex, EObject object, EReference reference, Resource resource, XMLResource.URIHandler uriHandler) {
		if (reference.isTransient())
			return;
		
		final Iterable<Edge> edges = vertex.getOutEdges(reference.getName());
		
		if (edges.iterator().hasNext()) {
			
			if (reference.isMany()) {
				
				@SuppressWarnings("unchecked")
				EList<EObject> values = (EList<EObject>) object.eGet(reference);
				
				for (Edge edge: edges) {
					EObject target = buildEObjectReference(edge, resource, uriHandler, reference.isResolveProxies());
					if (target != null)
						values.add(target);
				}
				
			}
		}
	}

	private EObject buildEObjectReference(Edge edge, Resource resource, XMLResource.URIHandler uriHandler, boolean isResolveProxies) {
		Vertex vertex = edge.getInVertex();
		if (vertex == null) {
			return null;
		}
		
		URI proxyURI = URI.createURI((String) vertex.getId());
		URI resolvedProxyURI = uriHandler.resolve(proxyURI);
		
		final EObject target;
		if (isResolveProxies) {
			target = resource.getResourceSet().getEObject(resolvedProxyURI, true);
		} else {
			target = createEObject(vertex, resource.getResourceSet());
			if (target == null)
				return null;
			((InternalEObject) target).eSetProxyURI(resolvedProxyURI);
		}
		
		return target;
	}
	
	private void buildEObjectAttribute(Vertex vertex, EObject object, EAttribute attribute) {
		if (!attribute.isTransient() && vertex.getPropertyKeys().contains(attribute.getName())) {
			
			Object rawValue = vertex.getProperty(attribute.getName());
			Object value = EcoreUtil.createFromString(attribute.getEAttributeType(), (String) rawValue);
			
			object.eSet(attribute, value);
		}
	}

	protected EObject createEObject(Vertex vertex, ResourceSet resourceSet) {
		Iterable<Edge> eClassEdges = vertex.getOutEdges("eClass");
		if (!eClassEdges.iterator().hasNext()) {
			return null;
		}
		Vertex eClassVertex = eClassEdges.iterator().next().getInVertex();
		String eClassURI = (String) eClassVertex.getId();
		
		EClass eClass = (EClass) resourceSet.getEObject(URI.createURI(eClassURI), true);
		
		return EcoreUtil.create(eClass);
	}
	
	@Override
	public int read() throws IOException {
		return 0;
	}
	
}
