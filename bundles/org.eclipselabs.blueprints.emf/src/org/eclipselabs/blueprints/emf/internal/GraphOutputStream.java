package org.eclipselabs.blueprints.emf.internal;

import static org.eclipselabs.blueprints.emf.util.BlueprintsEmfUtil.isNativeType;
import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_ECLASS;
import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_INDEX_KEY;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.URIHandler;
import org.eclipselabs.blueprints.emf.util.GraphUtil;

import com.tinkerpop.blueprints.pgm.CloseableSequence;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.Vertex;

/**
 * 
 * @author ghillairet
 *
 */
public class GraphOutputStream extends ByteArrayOutputStream implements URIConverter.Saveable {

	private final IndexableGraph graph;
	private final Map<?, ?> options;
	private Resource resource;
	private final Index<Vertex> vertexIndex;
	private final Index<Edge> edgeIndex;
	
	public GraphOutputStream(Graph graph, Index<Vertex> vertexIndex, Index<Edge> edgeIndex, Map<?, ?> options) {
		this.graph = (IndexableGraph) graph;
		this.vertexIndex = vertexIndex;
		this.edgeIndex = edgeIndex;
		this.options = options;
	}

	@Override
	public void saveResource(Resource resource) throws IOException {
		this.resource = resource;
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		
		XMLResource.URIHandler uriHandler = (XMLResource.URIHandler) options.get(XMLResource.OPTION_URI_HANDLER);

		if (uriHandler == null)
			uriHandler = new org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl();
		
		uriHandler.setBaseURI(resource.getURI());
		
		for (TreeIterator<EObject> it = resource.getAllContents(); it.hasNext();) {
			saveEObject(it.next(), uriHandler);
		}
		
//		graph.shutdown();
	}

	private Vertex getVertex(EObject object) {
		final URI uri = EcoreUtil.getURI(object);
		String safeURI = GraphUtil.safeURI(uri);
		
		final CloseableSequence<Vertex> vertices = vertexIndex.get(BLUEPRINTS_EMF_INDEX_KEY, safeURI);
		final Vertex vertex;
		
		if (vertices.hasNext()) {
			vertex = vertices.next();
		} else {
			vertex = graph.addVertex(null);
			vertex.setProperty(BLUEPRINTS_EMF_INDEX_KEY, safeURI);
			
			URI eClassURI = EcoreUtil.getURI(object.eClass());
			vertex.setProperty(BLUEPRINTS_EMF_ECLASS, eClassURI.toString());
		}
		
		return vertex;
	}
	
	private Edge getEdge(EReference reference, EObject source, EObject target) {
		final String edgeID = GraphUtil.getEdgeID(source, target, reference);
		final Iterable<Edge> edges = edgeIndex.get(BLUEPRINTS_EMF_INDEX_KEY, edgeID);
		final Edge edge;
		
		if (edges.iterator().hasNext()) {
			edge = edges.iterator().next();
		} else {
			edge = graph.addEdge(null, getVertex(source), getVertex(target), reference.getName());
			edge.setProperty(BLUEPRINTS_EMF_INDEX_KEY, edgeID);
			edgeIndex.put(BLUEPRINTS_EMF_INDEX_KEY, edgeID, edge);
		}
		return edge;
	}
	
	protected void saveEObject(EObject object, URIHandler uriHandler) {
		final Vertex vertex = getVertex(object);
		
		for (EAttribute attr: object.eClass().getEAllAttributes()) {
			if (object.eIsSet(attr)) {
				buildVertexProperty(vertex, object, attr);
			}
		}
		
		for (EReference ref: object.eClass().getEAllReferences()) {
			if (object.eIsSet(ref) && ref.isChangeable() && !ref.isTransient()) {
				
				if (ref.isMany()) {
					@SuppressWarnings("unchecked")
					Collection<EObject> values = (Collection<EObject>) object.eGet(ref);
					for (EObject value: values) {
						getEdge(ref, object, value);
					}
				} else {
					EObject value = (EObject) object.eGet(ref);
					getEdge(ref, object, value);
				}
			}
		}
	}
	
	protected void buildVertexProperty(Vertex vertex, EObject object, EAttribute attribute) {
		if (!attribute.isTransient() && (object.eIsSet(attribute) || (!attribute.isUnsettable()))) {
			Object value = object.eGet(attribute);

			if (FeatureMapUtil.isFeatureMap(attribute)) {
				FeatureMap.Internal featureMap = (FeatureMap.Internal) value;
				Iterator<FeatureMap.Entry> iterator = featureMap.basicIterator();
				
				while (iterator.hasNext()) {
					// TODO
					FeatureMap.Entry entry = iterator.next();
					EStructuralFeature feature = entry.getEStructuralFeature();
					
					if (feature instanceof EAttribute) {
						value = getEAttributeValue((EAttribute) feature, entry.getValue());
					} else {
						// TODO
					}
				}
			} else if (attribute.isMany()) {
				
				EDataType eDataType = attribute.getEAttributeType();

				if (!isNativeType(eDataType)) {
					EList<?> rawValues = (EList<?>) value;
					ArrayList<String> values = new ArrayList<String>(rawValues.size());

					for (Object rawValue : rawValues) {
						values.add(EcoreUtil.convertToString(eDataType, rawValue));
					}
					
					value = values;
				}
				
			} else {
				value = getEAttributeValue(attribute, value);
			}

			vertex.setProperty(attribute.getName(), value);
		}
	}

	private Object getEAttributeValue(EAttribute attribute, Object value) {
		final EDataType eDataType = attribute.getEAttributeType();
		if (!isNativeType(eDataType))
			return EcoreUtil.convertToString(eDataType, value);
		
		return value;
	}
	
}
