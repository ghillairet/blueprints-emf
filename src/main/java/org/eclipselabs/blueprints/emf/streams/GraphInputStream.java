package org.eclipselabs.blueprints.emf.streams;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.resource.BlueprintsResource;
import org.eclipselabs.blueprints.emf.util.PropertyKind;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

public class GraphInputStream extends InputStream implements URIConverter.Loadable {

	private final HashMap<Vertex, EObject> cache = new HashMap<Vertex, EObject>();

	@Override
	public void loadResource(Resource resource) throws IOException {
        if (!(resource instanceof BlueprintsResource))
            throw new IOException("Bad resource");

        cache.clear();

        final Graph graph = ((BlueprintsResource) resource).getGraph();
		final URI uri = resource.getURI();
		
		if (uri.hasFragment()) {
			final Vertex vertex = getOne( graph.getVertices(PropertyKind.eURI.toString(), uri.toString()) );
			if (vertex != null) {
				getOrCreateEObject(vertex, resource);
			}
		} else {
			final Vertex resourceVertex = getResourceVertex(uri, graph);

			if (resourceVertex == null)
				return;

			loadContent(resource, resourceVertex);
		}
	}

	private void loadContent(Resource resource, Vertex resourceVertex) {
		final Iterable<Edge> edges = resourceVertex.getEdges(Direction.OUT, PropertyKind.eContent.toString());
		for (Edge edge: edges) {
			getOrCreateEObject(edge.getVertex(Direction.IN), resource);
		}
	}

	private EObject createEObject(Vertex vertex, Resource resource) {
		final String eClassURI = vertex.getProperty(PropertyKind.eClass.toString());
        final ResourceSet resourceSet = resource.getResourceSet();
		EObject eObject = null;
		
		if (eClassURI != null) {

			final EClass eClass = (EClass) resourceSet.getEObject(URI.createURI(eClassURI), false);

			if (eClass != null) {
				eObject = EcoreUtil.create(eClass);
				attach(eObject, (String) vertex.getProperty(PropertyKind.eURI.toString()), resource, resourceSet);
				cache.put(vertex, eObject);
				
				for (EAttribute eAttribute: eClass.getEAllAttributes()) {
					if (!eAttribute.isTransient())
						fill(vertex, eObject, eAttribute);
				}

				for (EReference eReference: eClass.getEAllReferences()) {
					if (!eReference.isTransient())
						fill(vertex, eObject, eReference);
				}
			}
		}
		
		return eObject;
	}

	private void attach(EObject eObject, String uri, Resource resource, ResourceSet resourceSet) {
		if (uri.equals(resource.getURI().toString())) {
			resource.getContents().add(eObject);
		} else {
			final URI resourceURI = URI.createURI(uri).trimFragment();
			if (resourceURI.equals(resource.getURI())) {
				resource.getContents().add(eObject);	
			} else {
				final Resource otherResource = resourceSet.getResource(resourceURI, true);
				otherResource.getContents().add(eObject);
			}
		}
	}

	private void fill(Vertex vertex, EObject eObject, EReference eReference) {
		final Iterable<Edge> edges = vertex.getEdges(Direction.OUT, eReference.getName());

        if (edges.iterator().hasNext()) {
			
			if (eReference.isMany()) {
				@SuppressWarnings("unchecked")
                final EList<EObject> values = (EList<EObject>) eObject.eGet(eReference);

                for (Edge edge: edges) {
                    final EObject eTarget = getReferenceEObject(eObject, vertex, edge);

                    if (eTarget != null) {
						Integer position = edge.getProperty("_position");

						if (position != null && position > -1 && values.size() > 0 && position < values.size())  {
							values.add(position, eTarget);
						} else {
							values.add(eTarget);
						}
					}
				}

			} else {

                final Edge edge = getOne( edges );
				final EObject eTarget = getReferenceEObject(eObject, vertex, edge);

				if (eTarget != null) {
					eObject.eSet(eReference, eTarget);
				}

			}
		}
	}
	
	private EObject getReferenceEObject(EObject eObject, Vertex source, Edge edge) {
		final Vertex target = edge.getVertex(Direction.IN);
        final EObject eTarget;

		if (target.equals(source)) {
			eTarget = eObject;
		} else {
			eTarget = getOrCreateEObject(target, eObject.eResource());
		}

		return eTarget;
	}

	private void fill(Vertex vertex, EObject eObject, EAttribute eAttribute) {
		final Object value = vertex.getProperty(eAttribute.getName());
		
		if (value == null)
			return;
		
		if (!eAttribute.isMany() && value instanceof String) {
			eObject.eSet(eAttribute, EcoreUtil.createFromString(eAttribute.getEAttributeType(), (String) value));
		} else if (eAttribute.isMany()) {
			final ArrayList<Object> values = new ArrayList<Object>();
			final EDataType eDataType = eAttribute.getEAttributeType();
			
			if (value.getClass().isArray()) {
				final Object[] objects = (Object[]) value;
				for (Object o: objects) {
					values.add(EcoreUtil.createFromString(eDataType, o.toString()));
				}
			} else if (value.getClass().isAssignableFrom(Collection.class)) {
				final Collection<?> objects = (Collection<?>) value;
				for (Object o: objects) {
					values.add(EcoreUtil.createFromString(eDataType, o.toString()));
				}
			}
			eObject.eSet(eAttribute, values);
		}
	}

	private EObject getOrCreateEObject(Vertex vertex, Resource resource) {
		if (cache.containsKey(vertex)) {
			return cache.get(vertex);
		} else {
			return createEObject(vertex, resource);
		}
	}
	
	private Vertex getResourceVertex(URI resourceURI, Graph graph) {
		return getOne( graph.getVertices(PropertyKind.eResource.toString(), resourceURI.trimFragment().toString()) );
	}

	private <T extends Element> T getOne(Iterable<T> elements) {
		if (elements.iterator().hasNext()) {
			return elements.iterator().next();
		}
		return null;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

}
