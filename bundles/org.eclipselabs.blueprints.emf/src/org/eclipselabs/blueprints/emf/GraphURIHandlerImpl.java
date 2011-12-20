package org.eclipselabs.blueprints.emf;

import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_GRAPH_OBJECT;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipselabs.blueprints.emf.internal.GraphInputStream;
import org.eclipselabs.blueprints.emf.internal.GraphOutputStream;
import org.eclipselabs.blueprints.emf.util.GraphUtil;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.IndexableGraph;

public class GraphURIHandlerImpl extends URIHandlerImpl {
	
	@Override
	public boolean canHandle(URI uri) {
		return true;
//		return uri.scheme().equalsIgnoreCase("graph");
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		final Object objectGraph = options.get(BLUEPRINTS_GRAPH_OBJECT);
		if (objectGraph instanceof Graph) {
			return new GraphInputStream((Graph)objectGraph, options);	
		}
		return null;
	}
	
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		final Object objectGraph = options.get(BLUEPRINTS_GRAPH_OBJECT);
		if (objectGraph instanceof IndexableGraph) {
			IndexableGraph graph = (IndexableGraph)objectGraph;
			
			return new GraphOutputStream(graph, GraphUtil.getVertexIndex(graph), GraphUtil.getEdgeIndex(graph), options);
		}
		return null;
	}
	
}
