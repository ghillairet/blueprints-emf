package org.eclipselabs.blueprints.emf.junit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.User;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Vertex;

public class TestBlueprintsEmfReferences extends TestSupport {
	
	@Test
	public void testSaveTwoObjectsOneReference() throws IOException {
		User user1 = ModelFactory.eINSTANCE.createUser();
		user1.setUserId("1");
		user1.setName("John");
		
		User user2 = ModelFactory.eINSTANCE.createUser();
		user2.setUserId("2");
		user2.setName("Paul");
		
		user1.getFriends().add(user2);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(user1);
		resource.getContents().add(user2);
		
		resource.save(options);
		
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user1.eClass())));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user1)));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user2)));
		
		assertNotNull(graph.getEdge(GraphUtil.getEdgeID(user1, user2, ModelPackage.eINSTANCE.getUser_Friends())));
	}
	
	@Test
	public void testSaveThreeObjectsOneReference() throws IOException {
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
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(user1);
		resource.getContents().add(user2);
		resource.getContents().add(user3);
		
		resource.save(options);
		
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user1.eClass())));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user1)));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user2)));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user3)));
		
		assertNotNull(graph.getEdge(GraphUtil.getEdgeID(user1, user2, ModelPackage.eINSTANCE.getUser_Friends())));
		assertNotNull(graph.getEdge(GraphUtil.getEdgeID(user1, user3, ModelPackage.eINSTANCE.getUser_Friends())));
	}
	
	@Test
	public void testLoadTwoObjectsWithOneReference() throws IOException {
		Vertex eC = graph.addVertex(EcoreUtil.getURI(ModelPackage.eINSTANCE.getUser()));
		
		Vertex v1 = graph.addVertex("graph:/tmp/test#1");
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		Vertex v2 = graph.addVertex("graph:/tmp/test#2");
		v2.setProperty("userId", "2");
		v2.setProperty("name", "Paul");
		
		graph.addEdge(null, v1, eC, "eClass");
		graph.addEdge(null, v2, eC, "eClass");
		graph.addEdge(null, v1, v2, "friends");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		resource.load(options);
		
		assertEquals(2, resource.getContents().size());
		
		EObject obj1 = resource.getContents().get(0);
		EObject obj2 = resource.getContents().get(1);
		
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj1.eClass());
		assertEquals(ModelPackage.eINSTANCE.getUser(), obj2.eClass());
		
		User u1 = (User) obj1;
		assertEquals("2", u1.getUserId());
		assertEquals("Paul", u1.getName());
		
		User u2 = (User) obj2;
		assertEquals("1", u2.getUserId());
		assertEquals("John", u2.getName());
		
		assertTrue(u2.getFriends().contains(u1));
	}
	
}
