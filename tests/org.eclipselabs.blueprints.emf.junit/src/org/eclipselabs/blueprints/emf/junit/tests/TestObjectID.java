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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipselabs.blueprints.emf.junit.model.Node;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.eclipselabs.blueprints.emf.util.PropertyKind;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class TestObjectID extends TestSupport {
	
	private Resource resource;
	
	@Before
	public void tearUp() {
		super.tearUp();
		
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Resource nodes = new XMIResourceImpl(URI.createURI("tests/node-1.xmi"));
		try {
			nodes.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resource = resourceSet.createResource(URI.createURI("graph://nodes/"));
		resource.getContents().addAll(nodes.getContents());
		
		try {
			resource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveObjectWithID() {
		
	}
	
	@Test
	public void testSaveObjectWithoutID() {
		
	}
	
	@Test
	public void testSaveObjectWithoutIDInTree() throws IOException {
		assertTrue(graph.getVertices().iterator().hasNext());
		
		for (TreeIterator<EObject> it = resource.getAllContents();it.hasNext();) {
			System.out.println(EcoreUtil.getURI(it.next()));
		}
		EObject root = resource.getContents().get(0);
		Vertex a = GraphUtil.getVertex(root, graph);
		
		assertEquals("a", a.getProperty("name"));
		assertEquals(EcoreUtil.getURI(root).toString(), a.getProperty(PropertyKind.eURI.toString()));
		
		for (Edge edge: a.getEdges(Direction.OUT, "children")) {
			Vertex v = edge.getVertex(Direction.IN);
			assertEquals("b", v.getProperty("name"));
			assertEquals(EcoreUtil.getURI(((Node)root).getChildren().get(0)).toString(), v.getProperty(PropertyKind.eURI.toString()));
		}
		
		for (Edge edge: a.getEdges(Direction.OUT, "oneRefNode")) {
			Vertex v = edge.getVertex(Direction.IN);
			assertEquals("e", v.getProperty("name"));
			assertEquals("graph://nodes/#//@children.0/@children.0/@children.0/@children.0", v.getProperty(PropertyKind.eURI.toString()));
		}
		
	}
	
	@Test
	public void testLoadObjectTreeWithoutID() throws IOException {
		resource.unload();
		assertTrue(resource.getContents().isEmpty());
		
		resource.load(options);
		assertFalse(resource.getContents().isEmpty());
		assertEquals(1, resource.getContents().size());
				
		EObject root = resource.getEObject("/");
		
		assertTrue(root instanceof Node);
		
		Node rootNode = (Node)root;
		assertEquals(1, rootNode.getChildren().size());
		assertEquals("a", rootNode.getName());
		
		assertEquals(1, resource.getContents().size());
		
	}
}
