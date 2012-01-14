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
package org.eclipselabs.blueprints.emf.neo4j.junit.support;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipselabs.blueprints.emf.GraphURIHandlerImpl;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;

public abstract class TestSupport {
	
	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected static ResourceSet resourceSet;
	protected static IndexableGraph graph;
	
	@AfterClass
	public static void tearDown() {
		graph.clear();
		graph.shutdown();
	}
	
	@Before
	public void clear() {
		for (Vertex vertex: graph.getVertices()) {
			graph.removeVertex(vertex);
		}
		for (Edge edge: graph.getEdges()) {
			graph.removeEdge(edge);
		}
		
		graph.clear();
	}
	
	@BeforeClass
	public static void tearUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		
		graph = new Neo4jGraph("/tmp/neo4j/tests");
		
		resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl(graph));
	}
	
}
