package org.eclipselabs.blueprints.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipselabs.blueprints.emf.internal.GraphInputStream;
import org.eclipselabs.blueprints.emf.internal.GraphOutputStream;

import com.tinkerpop.blueprints.pgm.Graph;

public class GraphURIHandlerImpl extends URIHandlerImpl {

	public static final String OPTION_GRAPH_OBJECT = "OPTION_GRAPH_OBJECT";
	
	@Override
	public boolean canHandle(URI uri) {
		return uri.scheme().equalsIgnoreCase("graph");
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		final Object objectGraph = options.get(OPTION_GRAPH_OBJECT);
		if (objectGraph instanceof Graph) {
			return new GraphInputStream((Graph)objectGraph, options);	
		}
		return null;
	}
	
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		final Object objectGraph = options.get(OPTION_GRAPH_OBJECT);
		if (objectGraph instanceof Graph) {
			return new GraphOutputStream((Graph)objectGraph, options);	
		}
		return null;
	}
}
