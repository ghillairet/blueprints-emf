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
package org.eclipselabs.blueprints.emf.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipselabs.blueprints.emf.GraphURIHandler;
import org.eclipselabs.blueprints.emf.streams.GraphInputStream;
import org.eclipselabs.blueprints.emf.streams.GraphOutputStream;

import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;

/**
 * 
 * @author ghillairet
 */
public class GraphURIHandlerImpl extends URIHandlerImpl implements GraphURIHandler {
	
	public GraphURIHandlerImpl() {}
	
	@Override
	public boolean canHandle(URI uri) {
		if (uri.scheme().equalsIgnoreCase("graph"))
			return Registry.INSTANCE.containsURI(uri);
		return false;
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		final KeyIndexableGraph graph = Registry.INSTANCE.getGraph(uri);
		if (graph == null) {
			throw new IllegalArgumentException("Cannot find graph for URI "+uri+", please register a graph using GraphURIHandler.Registry.");
		}
		
		prepareIndexes(graph);
		InputStream inStream = null;
		try {
			inStream = new GraphInputStream(graph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inStream;
	}
	
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		final KeyIndexableGraph graph = Registry.INSTANCE.getGraph(uri);
		
		if (graph == null) {
			throw new IllegalArgumentException("Cannot find graph for URI "+uri+", please register a graph using GraphURIHandler.Registry.");
		}
		
		prepareIndexes(graph);
		OutputStream outStream = null;
		try {
			outStream = new GraphOutputStream(graph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return outStream;
	}

	private void prepareIndexes(KeyIndexableGraph graph) {
		graph.createKeyIndex("_eClass", Vertex.class);
		graph.createKeyIndex("_eResource", Vertex.class);
		graph.createKeyIndex("_eURI", Vertex.class);
		graph.createKeyIndex("_eContent", Vertex.class);
	}
	
	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
		// TODO Auto-generated method stub
	}
	
}
