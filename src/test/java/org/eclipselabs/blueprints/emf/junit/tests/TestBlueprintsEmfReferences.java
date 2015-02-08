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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.Node;
import org.eclipselabs.blueprints.emf.junit.model.User;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.eclipselabs.blueprints.emf.util.PropertyKind;
import org.junit.Test;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class TestBlueprintsEmfReferences extends TestSupport {

	@Test
	public void testSaveTwoObjectsOneReference() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		User user1 = ModelFactory.eINSTANCE.createUser();
		user1.setUserId("1");
		user1.setName("John");
		
		User user2 = ModelFactory.eINSTANCE.createUser();
		user2.setUserId("2");
		user2.setName("Paul");
		
		user1.getFriends().add(user2);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(user1);
		resource.getContents().add(user2);
		
		resource.save(options);
		
		Vertex v1 = GraphUtil.getVertex(user1, graph);
		Vertex v2 = GraphUtil.getVertex(user2, graph);
		
		Iterable<Edge> edges = v1.getEdges(Direction.OUT, "friends");
		Iterator<Edge> it = edges.iterator();
		assertTrue(it.hasNext());
		assertEquals(it.next().getVertex(Direction.IN), v2);
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testSaveThreeObjectsOneReference() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		User user1 = ModelFactory.eINSTANCE.createUser();
		user1.setUserId("1");
		user1.setName("John");
		
		User user2 = ModelFactory.eINSTANCE.createUser();
		user2.setUserId("2");
		user2.setName("Paul");
		
		User user3 = ModelFactory.eINSTANCE.createUser();
		user3.setUserId("3");
		user3.setName("Henry");
		
		user1.getFriends().add(user2);
		user1.getFriends().add(user3);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(user1);
		resource.getContents().add(user2);
		resource.getContents().add(user3);
		
		resource.save(options);
		
		Vertex v1 = GraphUtil.getVertex(user1, graph);
		Vertex v2 = GraphUtil.getVertex(user2, graph);
		Vertex v3 = GraphUtil.getVertex(user3, graph);
		
		assertNotNull(v1);
		assertNotNull(v2);
		assertNotNull(v3);
		
		Iterable<Edge> edges = v1.getEdges(Direction.OUT, "friends");
		Iterator<Edge> it = edges.iterator();
		assertTrue(it.hasNext());
		
		Vertex next = it.next().getVertex(Direction.IN);
		
		assertTrue(next.equals(v2) || next.equals(v3));
		assertTrue(it.hasNext());
		
		next = it.next().getVertex(Direction.IN);
		
		assertTrue(next.equals(v2) || next.equals(v3));
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testLoadTwoObjectsWithOneReference() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Vertex v = createResourceVertex("graph://tmp/test");
		
		Vertex v1 = createVertex("graph://tmp/test#1", ModelPackage.Literals.USER);
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		Vertex v2 = createVertex("graph://tmp/test#2", ModelPackage.Literals.USER);
		v2.setProperty("userId", "2");
		v2.setProperty("name", "Paul");
		
		graph.addEdge(null, v1, v2, "friends");
		graph.addEdge(null, v, v1, "_eContent");
		graph.addEdge(null, v, v2, "_eContent");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test"));
		assertNotNull(resource);
		resource.load(options);
		
		assertEquals(2, resource.getContents().size());
		
		EObject obj1 = resource.getEObject("1");
		EObject obj2 = resource.getEObject("2");
		
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj1.eClass());
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj2.eClass());
		
		User u1 = (User) obj1;
		assertEquals("1", u1.getUserId());
		assertEquals("John", u1.getName());
		
		User u2 = (User) obj2;
		assertEquals("2", u2.getUserId());
		assertEquals("Paul", u2.getName());
		
		assertEquals(1, u1.getFriends().size());
		assertEquals(0, u2.getFriends().size());
				
		assertTrue(u1.getFriends().contains(u2));
		
		User friend = u1.getFriends().get(0);
		assertEquals(u2, friend);
	}
	
	@Test
	public void testLoadThreeObjectsWithReferenceMany() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Vertex v = createResourceVertex("graph://tmp/test");
		
		Vertex v1 = createVertex("graph://tmp/test#1", ModelPackage.Literals.USER);
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		Vertex v2 = createVertex("graph://tmp/test#2", ModelPackage.Literals.USER);
		v2.setProperty("userId", "2");
		v2.setProperty("name", "Paul");
		
		Vertex v3 = createVertex("graph://tmp/test#3", ModelPackage.Literals.USER);
		v3.setProperty("userId", "3");
		v3.setProperty("name", "Jacques");
		
		graph.addEdge(null, v1, v2, "friends").setProperty("_position", 0);
		graph.addEdge(null, v1, v3, "friends").setProperty("_position", 1);;
		
		graph.addEdge(null, v, v1, "_eContent");
		graph.addEdge(null, v, v2, "_eContent");
		graph.addEdge(null, v, v3, "_eContent");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test"));
		assertNotNull(resource);
		resource.load(options);
				
		assertEquals(3, resource.getContents().size());
		
		EObject obj1 = resource.getEObject("1");
		EObject obj2 = resource.getEObject("2");
		EObject obj3 = resource.getEObject("3");
		
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj1.eClass());
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj2.eClass());
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj3.eClass());
		
		User u1 = (User) obj1;
		assertEquals("1", u1.getUserId());
		assertEquals("John", u1.getName());
		
		User u2 = (User) obj2;
		assertEquals("2", u2.getUserId());
		assertEquals("Paul", u2.getName());
		
		User u3 = (User) obj3;
		assertEquals("3", u3.getUserId());
		assertEquals("Jacques", u3.getName());
		
		assertEquals(2, u1.getFriends().size());
		assertEquals(0, u2.getFriends().size());
		assertEquals(0, u3.getFriends().size());
		
		assertTrue(u1.getFriends().contains(u2));
		assertTrue(u1.getFriends().contains(u3));
		
		assertEquals(u2, u1.getFriends().get(0));
		assertEquals(u3, u1.getFriends().get(1));
	}
	
	@Test
	public void testLoadOneObjectFromItsURI() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Vertex v = createResourceVertex("graph://tmp/test");
		
		Vertex v1 = createVertex("graph://tmp/test#1", ModelPackage.Literals.USER);
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		graph.addEdge(null, v, v1, PropertyKind.eContent.toString());
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test#1"));
		assertNotNull(resource);
		
		resource.load(options);
		
		assertEquals(1, resource.getContents().size());
		
		EObject obj1 = resource.getContents().get(0);
		
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj1.eClass());
		
		User u1 = (User) obj1;
		assertEquals("1", u1.getUserId());
		assertEquals("John", u1.getName());
	}
	
	
	@Test
	public void testSaveNodeContainmentOneHierarchyOneRoot() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Node rootNode = ModelFactory.eINSTANCE.createNode();
		rootNode.setName("root");
		
		Node childNode1 = ModelFactory.eINSTANCE.createNode();
		childNode1.setName("child1");
		
		Node childNode2 = ModelFactory.eINSTANCE.createNode();
		childNode2.setName("child2");
		
		Node childNode11 = ModelFactory.eINSTANCE.createNode();
		childNode11.setName("child11");
		
		rootNode.getChildren().add(childNode1);
		rootNode.getChildren().add(childNode2);
		childNode1.getChildren().add(childNode11);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test/nodes"));
		resource.getContents().add(rootNode);
		
		resource.save(null);
		
		Vertex rootVertex = GraphUtil.getVertex(rootNode, graph);
		Vertex child1Vertex = GraphUtil.getVertex(childNode1, graph);
		Vertex child11Vertex = GraphUtil.getVertex(childNode11, graph);
		Vertex child2Vertex = GraphUtil.getVertex(childNode2, graph);
		
		assertNotNull(rootVertex);
		assertNotNull(child1Vertex);
		assertNotNull(child11Vertex);
		assertNotNull(child2Vertex);
		
		assertEquals("root", rootVertex.getProperty("name"));
		assertEquals("child1", child1Vertex.getProperty("name"));
		assertEquals("child11", child11Vertex.getProperty("name"));
		assertEquals("child2", child2Vertex.getProperty("name"));
		
		Iterable<Edge> edges = rootVertex.getEdges(Direction.OUT, "children");
		Iterator<Edge> it = edges.iterator();
		
		assertTrue(it.hasNext());
		Edge edge1 = it.next();
		
		assertEquals(rootVertex, edge1.getVertex(Direction.OUT));
		assertEquals(child1Vertex, edge1.getVertex(Direction.IN));
		assertEquals("children", edge1.getLabel());
		
		assertTrue(it.hasNext());
		Edge edge2 = it.next();
		
		assertEquals(rootVertex, edge2.getVertex(Direction.OUT));
		assertEquals(child2Vertex, edge2.getVertex(Direction.IN));
		assertEquals("children", edge2.getLabel());
		
		assertFalse(it.hasNext());
		
		Iterable<Edge> edges2 = child1Vertex.getEdges(Direction.OUT, "children");
		Iterator<Edge> it2 = edges2.iterator();
		assertTrue(it2.hasNext());
		
		Edge edge11 = it2.next();
		
		assertEquals(child1Vertex, edge11.getVertex(Direction.OUT));
		assertEquals(child11Vertex, edge11.getVertex(Direction.IN));
		assertEquals("children", edge11.getLabel());

		assertFalse(it2.hasNext());
	}
	
	@Test
	public void testLoadNodeContainmentOneHierarchyOneRoot() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Node rootNode = ModelFactory.eINSTANCE.createNode();
		rootNode.setName("root");
		
		Node childNode1 = ModelFactory.eINSTANCE.createNode();
		childNode1.setName("child1");
		
		Node childNode2 = ModelFactory.eINSTANCE.createNode();
		childNode2.setName("child2");
		
		Node childNode11 = ModelFactory.eINSTANCE.createNode();
		childNode11.setName("child11");
		
		rootNode.getChildren().add(childNode1);
		rootNode.getChildren().add(childNode2);
		childNode1.getChildren().add(childNode11);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test/nodes"));
		resource.getContents().add(rootNode);
		
		resource.save(null);
		
		resource.unload();
		assertTrue(resource.getContents().isEmpty());
		assertFalse(resource.isLoaded());
		
		resource.load(null);
		
		EObject rootObject = resource.getContents().get(0);
		
		assertTrue(rootObject instanceof Node);
		
		Node root = (Node) rootObject;
		
		assertEquals(2, root.getChildren().size());
		
		assertEquals("child1", root.getChildren().get(0).getName());
		assertEquals("child2", root.getChildren().get(1).getName());
		
		assertEquals(1, root.getChildren().get(0).getChildren().size());
		assertEquals("child11", root.getChildren().get(0).getChildren().get(0).getName());
	}
}
