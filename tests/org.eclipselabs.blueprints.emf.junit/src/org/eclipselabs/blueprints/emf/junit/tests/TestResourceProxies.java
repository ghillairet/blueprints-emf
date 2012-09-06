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
package org.eclipselabs.blueprints.emf.junit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.Node;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.junit.Test;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class TestResourceProxies extends TestSupport {
	
	@Test
	public void testSaveTwoObjectsInSeparateResources() throws IOException {
		Node firstNode = ModelFactory.eINSTANCE.createNode();
		firstNode.setName("a");
		
		Node secondNode = ModelFactory.eINSTANCE.createNode();
		secondNode.setName("b");
		
		firstNode.setOneRefNode(secondNode);
		
		Resource firstResource = resourceSet.createResource(URI.createURI("graph://tmp/tests/first"));
		firstResource.getContents().add(firstNode);
		
		Resource secondResource = resourceSet.createResource(URI.createURI("graph://tmp/tests/second"));
		secondResource.getContents().add(secondNode);
		
		firstResource.save(null);
		secondResource.save(null);
		
		Vertex firstVertex = GraphUtil.getVertex(firstNode, graph);
		Vertex secondVertex = GraphUtil.getVertex(secondNode, graph);
		
		assertNotNull(firstVertex);
		assertNotNull(secondVertex);
		
		assertEquals("a", firstVertex.getProperty("name"));
		assertEquals("b", secondVertex.getProperty("name"));
		
		Iterable<Edge> edges = firstVertex.getEdges(Direction.OUT, "oneRefNode");
		Iterator<Edge> it = edges.iterator();
		
		assertTrue(it.hasNext());
		Edge edge = it.next();
		Vertex v = edge.getVertex(Direction.IN);
		
		assertEquals(secondVertex, v);
	}
}
