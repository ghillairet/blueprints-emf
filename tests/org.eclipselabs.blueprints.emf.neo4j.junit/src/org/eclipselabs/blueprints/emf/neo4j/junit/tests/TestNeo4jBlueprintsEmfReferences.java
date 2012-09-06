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
package org.eclipselabs.blueprints.emf.neo4j.junit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.Node;
import org.eclipselabs.blueprints.emf.junit.model.User;
import org.eclipselabs.blueprints.emf.neo4j.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.eclipselabs.blueprints.emf.util.PropertyKind;
import org.junit.Test;

import com.tinkerpop.blueprints.Vertex;

public class TestNeo4jBlueprintsEmfReferences extends TestSupport {

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
		
		assertNotNull(GraphUtil.getVertex(user1, graph));
		assertNotNull(GraphUtil.getVertex(user2, graph));
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
		user2.setUserId("3");
		user2.setName("Henry");
		
		user1.getFriends().add(user2);
		user1.getFriends().add(user3);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(user1);
		resource.getContents().add(user2);
		resource.getContents().add(user3);
		
		resource.save(options);
		
		assertNotNull(GraphUtil.getVertex(user1, graph));
		assertNotNull(GraphUtil.getVertex(user2, graph));
		assertNotNull(GraphUtil.getVertex(user3, graph));
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
		graph.addEdge(null, v, v1, PropertyKind.eContent.toString());
		graph.addEdge(null, v, v2, PropertyKind.eContent.toString());
		
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
		
		System.out.println("u2 "+u2);
		System.out.println("friends "+u1.getFriends());
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
		
		graph.addEdge(null, v1, v2, "friends");
		graph.addEdge(null, v1, v3, "friends");
		graph.addEdge(null, v, v1, PropertyKind.eContent.toString());
		graph.addEdge(null, v, v2, PropertyKind.eContent.toString());
		graph.addEdge(null, v, v3, PropertyKind.eContent.toString());
		
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
		
		
		Vertex v1 = createVertex("graph://tmp/test#1", ModelPackage.Literals.USER);
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		
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
		
//		Iterable<Edge> edges = rootVertex.getOutEdges("children");
//		assertTrue(edges.iterator().hasNext());
//		
//		Edge edge1 = edges.iterator().next();
//		assertEquals(rootVertex, edge1.getOutVertex());
//		assertEquals(child1Vertex, edge1.getInVertex());
//		assertEquals("children", edge1.getLabel());
//		
//		assertTrue(edges.iterator().hasNext());
//		
//		Edge edge2 = edges.iterator().next();
//		assertEquals(rootVertex, edge2.getOutVertex());
//		assertEquals(child2Vertex, edge2.getInVertex());
//		assertEquals("children", edge2.getLabel());
//		
//		assertFalse(edges.iterator().hasNext());
//		
//		Iterable<Edge> edges2 = child1Vertex.getOutEdges("children");
//		assertTrue(edges2.iterator().hasNext());
//		
//		Edge edge11 = edges2.iterator().next();
//		assertEquals(child1Vertex, edge11.getOutVertex());
//		assertEquals(child11Vertex, edge11.getInVertex());
//		assertEquals("children", edge11.getLabel());
//
//		assertFalse(edges2.iterator().hasNext());
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
		
		assertEquals(1, resource.getContents().size());
		
		EObject rootObject = resource.getContents().get(0);
		
		assertTrue(rootObject instanceof Node);
		
		Node root = (Node) resource.getContents().get(0);
		
		assertEquals(2, root.getChildren().size());
		
		assertEquals("child1", root.getChildren().get(0).getName());
		assertEquals("child2", root.getChildren().get(1).getName());
		
		assertEquals(1, root.getChildren().get(0).getChildren().size());
		assertEquals("child11", root.getChildren().get(0).getChildren().get(0).getName());
	}
	
}
