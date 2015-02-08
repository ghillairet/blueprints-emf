package org.eclipselabs.blueprints.emf.streams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.resource.BlueprintsResource;
import org.eclipselabs.blueprints.emf.util.PropertyKind;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

public class GraphOutputStream extends ByteArrayOutputStream implements URIConverter.Saveable {

	@Override
	public void saveResource(Resource resource) throws IOException {
		if (!(resource instanceof BlueprintsResource)) {
            throw new IOException("Bad resource");
        }

        final Graph graph = ((BlueprintsResource) resource).getGraph();
		final EList<EObject> content = resource.getContents();
		
		for (EObject eObject: content) {
			getOrCreateVertex( eObject, graph );
		}
	}

    private Vertex getOrCreateVertex(EObject eObject, Graph graph) {
        final URI eObjectURI = EcoreUtil.getURI(eObject);
        Vertex vertex = getOne( graph.getVertices(PropertyKind.eURI.toString(), eObjectURI.toString()) );

        if (vertex == null) {
            vertex = createOrUpdateVertex(eObject, graph);
        }

        return vertex;
    }

	private Vertex createOrUpdateVertex(EObject eObject, Graph graph) {
		final URI eObjectURI = EcoreUtil.getURI(eObject);
		final String eClassURI = EcoreUtil.getURI(eObject.eClass()).toString();
		
		final Vertex vertex = graph.addVertex(null);
		vertex.setProperty(PropertyKind.eURI.toString(), eObjectURI.toString());
		vertex.setProperty(PropertyKind.eClass.toString(), eClassURI);
		
		final Vertex resourceVertex = getOrCreateResourceVertex( eObject.eResource(), graph );
		if (eObject.eContainer() == null) {
			graph.addEdge(null, resourceVertex, vertex, PropertyKind.eContent.toString());
		}
		
		graph.addEdge(null, vertex, resourceVertex, PropertyKind.eResource.toString());
		addVertexProperties(eObject, vertex);
		addVertexOutEdges(eObject, vertex, graph);
		
		return vertex;
	}
	
	private void addVertexProperties(EObject eObject, Vertex vertex) {
		for (EAttribute eAttribute: eObject.eClass().getEAllAttributes()) {
			if (eObject.eIsSet(eAttribute) && !eAttribute.isTransient()) {
				final Object value = eObject.eGet(eAttribute);

                if (!eAttribute.isMany()) {

                    vertex.setProperty(eAttribute.getName(),
                            EcoreUtil.convertToString(eAttribute.getEAttributeType(),
                                    value));

				} else if (!value.getClass().isArray()) {

					Collection<?> values = (Collection<?>) value;
					Object[] arrayValues = values.toArray();
					vertex.setProperty(eAttribute.getName(), arrayValues);

				} else {

					vertex.setProperty(eAttribute.getName(), value);

				}
			}
		}
	}
	
	private void addVertexOutEdges(EObject eObject, Vertex vertex, Graph graph) {
		for (EReference eReference: eObject.eClass().getEAllReferences()) {
			if (eObject.eIsSet(eReference) && !eReference.isTransient() && !eReference.isDerived()) {
				
				if (eReference.isMany()) {

					@SuppressWarnings("unchecked")
					EList<EObject> values = (EList<EObject>) eObject.eGet(eReference);
					for (int i=0; i<values.size(); i++) {
						createEdge(vertex, eObject, eReference, values.get(i), i, graph);
					}

				} else {

					final EObject value = (EObject) eObject.eGet(eReference);
					if (value != null) {
						createEdge(vertex, eObject, eReference, value, 0, graph);
					}

				}
			}
		}
	}
	
	private void createEdge(Vertex vertex, EObject source, EReference eReference, EObject target, int position, Graph graph) {
		final Vertex targetVertex;

		if (target.equals(source)) {
			targetVertex = vertex;
		} else {
			targetVertex = getOrCreateVertex(target, graph);
		}

		final Edge edge = graph.addEdge(null, vertex, targetVertex, eReference.getName());
		edge.setProperty("_position", position);
	}

	private <T extends Element> T getOne(Iterable<T> elements) {
		if (elements.iterator().hasNext()) {
			return elements.iterator().next();
		}
		return null;
	}

	private Vertex getOrCreateResourceVertex(Resource resource, Graph graph) {
		Vertex vertex = getOne( graph.getVertices(PropertyKind.eResource.toString(), resource.getURI().toString()) );
		if (vertex == null) {
			vertex = graph.addVertex(null);
			vertex.setProperty(PropertyKind.eResource.toString(), resource.getURI().toString());
		}
		return vertex;
	}
	
}
