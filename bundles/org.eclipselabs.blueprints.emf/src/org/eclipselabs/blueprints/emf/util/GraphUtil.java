package org.eclipselabs.blueprints.emf.util;

import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_EDGE_INDEX;
import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_INDEX_KEY;
import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_VERTEX_INDEX;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.tinkerpop.blueprints.pgm.CloseableSequence;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.Vertex;

public class GraphUtil {
	
	public static String getEdgeID(EObject source, EObject target, EReference reference) {
		return safeURI(EcoreUtil.getURI(source)) + "_" + safeName(reference.getName()) + "_" + safeURI(EcoreUtil.getURI(target));
	}
	
	public static String safeName(String name) {
		return name.replace("#", "%23").replace(" ", "%20");
	}

	public static String safeURI(URI uri) {
		return uri.toString().replace("#", "%23").replace(" ", "%20");
	}

	public static boolean hasVertex(EObject eObject, IndexableGraph graph) {
		if (eObject == null || graph == null)
			return false;
		final URI uri = EcoreUtil.getURI(eObject);
		if (uri == null)
			return false;
		
		final Index<Vertex> index = getVertexIndex(graph);
		final CloseableSequence<Vertex> vertices = index.get(BLUEPRINTS_EMF_INDEX_KEY, safeURI(uri));
		
		if (vertices.hasNext())
			return true;
		return false;
	}
	
	public static Vertex getVertex(EObject eObject, IndexableGraph graph) {
		if (eObject == null || graph == null)
			return null;
		final URI uri = EcoreUtil.getURI(eObject);
		if (uri == null)
			return null;
		
		final Index<Vertex> index = getVertexIndex(graph);
		final CloseableSequence<Vertex> vertices = index.get(BLUEPRINTS_EMF_INDEX_KEY, safeURI(uri));
		
		if (vertices.hasNext())
			return vertices.next();
		else
			return null;
	}
	
	public static Index<Vertex> getVertexIndex(IndexableGraph graph) {
		Index<Vertex> vertexIndex = graph.getIndex(BLUEPRINTS_EMF_VERTEX_INDEX,Vertex.class);
		if (vertexIndex == null) {
			vertexIndex= graph.createAutomaticIndex(BLUEPRINTS_EMF_VERTEX_INDEX, Vertex.class, indexKeys);
		}
		return vertexIndex;
	}
	
	public static Index<Edge> getEdgeIndex(IndexableGraph graph) {
		Index<Edge> edgeIndex = graph.getIndex(BLUEPRINTS_EMF_EDGE_INDEX, Edge.class);
		if (edgeIndex == null) {
			edgeIndex = graph.createAutomaticIndex(BLUEPRINTS_EMF_EDGE_INDEX, Edge.class, indexKeys);
		}
		return edgeIndex;
	}
	
	private final static Set<String> indexKeys = new HashSet<String>();
	static {
		indexKeys.add(BLUEPRINTS_EMF_INDEX_KEY);
	}
	
}
