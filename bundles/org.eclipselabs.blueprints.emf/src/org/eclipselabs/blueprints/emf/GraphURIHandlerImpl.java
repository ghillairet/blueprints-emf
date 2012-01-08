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
package org.eclipselabs.blueprints.emf;

import static org.eclipselabs.blueprints.emf.util.GraphUtil.getEdgeIndex;
import static org.eclipselabs.blueprints.emf.util.GraphUtil.getVertexIndex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipselabs.blueprints.emf.internal.GraphInputStream;
import org.eclipselabs.blueprints.emf.internal.GraphOutputStream;

import com.tinkerpop.blueprints.pgm.IndexableGraph;

/**
 * 
 * @author ghillairet
 */
public class GraphURIHandlerImpl extends URIHandlerImpl {
	
	protected IndexableGraph graph;
	protected final String scheme;
	protected final String authority;

//	public GraphURIHandlerImpl() {
//		Activator.getInstance(); // TODO
//	}
	
	public GraphURIHandlerImpl(IndexableGraph graph) {
		this(graph, null, null);
	}
	
	public GraphURIHandlerImpl(IndexableGraph graph, String scheme, String authority) {
		this.graph = graph;
		this.scheme = scheme;
		this.authority = authority;
	}
	
	@Override
	public boolean canHandle(URI uri) {
		if (scheme != null)
			if (authority != null)
				return uri.scheme().equalsIgnoreCase(scheme) && uri.authority().equalsIgnoreCase(authority);
			else
				return uri.scheme().equalsIgnoreCase(scheme);
		else
			return uri.scheme().equalsIgnoreCase("graph");
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		return new GraphInputStream(graph, getVertexIndex(graph), getEdgeIndex(graph), options);	
	}
	
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		return new GraphOutputStream(graph, getVertexIndex(graph), getEdgeIndex(graph), options);
	}
	
	public static final String USE_ID_ATTRIBUTE_AS_KEY = "USE_ID_ATTRIBUTE_AS_KEY";
	public static final String EXTRINSIC_ID_KEY = "EXTRINSIC_ID_KEY"; 
}
