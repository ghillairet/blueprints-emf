package org.eclipselabs.blueprints.emf.internal;

import static org.eclipselabs.blueprints.emf.util.BlueprintsEmfUtil.isNativeType;
import static org.eclipselabs.blueprints.emf.util.GraphUtil.getEdgeID;

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
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.URIHandler;
import org.eclipselabs.blueprints.emf.util.GraphUtil;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;

/**
 * 
 * @author ghillairet
 *
 */
public class GraphOutputStream extends ByteArrayOutputStream implements URIConverter.Saveable {

	private final Graph graph;
	private final Map<?, ?> options;
	private Resource resource;
	
	public GraphOutputStream(Graph graph, Map<?, ?> options) {
		this.graph = graph;
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
	}

	private Vertex getVertex(EObject object) {
		final URI uri = EcoreUtil.getURI(object);
		
		Vertex vertex = graph.getVertex(uri);
		if (vertex != null) {
			return vertex;
		}
		
		vertex = graph.addVertex(uri);
		final URI eClassURI = EcoreUtil.getURI(object.eClass());
		Vertex eClassVertex = graph.getVertex(eClassURI);
		
		if (eClassVertex == null) {
			eClassVertex = graph.addVertex(eClassURI);
		}
		
		graph.addEdge(getEdgeID(object, object.eClass(), 
				EcorePackage.eINSTANCE.getETypedElement_EType()), 
				vertex, eClassVertex, "eClass");
		
		return vertex;
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
						Vertex valueVertex = getVertex(value);
						graph.addEdge(GraphUtil.getEdgeID(object, value, ref), valueVertex, vertex, ref.getName());
					}
				} else {
					EObject value = (EObject) object.eGet(ref);
					Vertex valueVertex = getVertex(value);
					graph.addEdge(GraphUtil.getEdgeID(object, value, ref), valueVertex, vertex, ref.getName());
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
