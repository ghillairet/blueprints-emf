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

import static org.eclipselabs.blueprints.emf.util.GraphUtil.getEdgeIndex;
import static org.eclipselabs.blueprints.emf.util.GraphUtil.getVertexIndex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipselabs.blueprints.emf.GraphURIHandler;
import org.eclipselabs.blueprints.emf.internal.GraphInputStream;
import org.eclipselabs.blueprints.emf.internal.GraphOutputStream;

import com.tinkerpop.blueprints.pgm.IndexableGraph;

/**
 * 
 * @author ghillairet
 */
public class GraphURIHandlerImpl extends URIHandlerImpl implements GraphURIHandler {
	
	protected IndexableGraph graph;
	
//	public GraphURIHandlerImpl() {
//		Activator.getInstance(); // TODO
//	}
	
	public GraphURIHandlerImpl(IndexableGraph graph) {
		this.graph = graph;
	}
	
	@Override
	public boolean canHandle(URI uri) {
		if (uri.scheme().equalsIgnoreCase("graph"))
			return Registry.INSTANCE.containsURI(uri);
		return false;
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		final IndexableGraph graph = Registry.INSTANCE.getGraph(uri);
		if (graph == null) {
			throw new IllegalArgumentException();
		}
		
		InputStream inStream = null;
		try {
			inStream = new GraphInputStream(graph, getVertexIndex(graph), getEdgeIndex(graph), options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inStream;
	}
	
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		final IndexableGraph graph = Registry.INSTANCE.getGraph(uri);
		if (graph == null) {
			throw new IllegalArgumentException();
		}
		
		OutputStream outStream = null;
		try {
			outStream = new GraphOutputStream(graph, getVertexIndex(graph), getEdgeIndex(graph), options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return outStream;
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
