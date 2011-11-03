package org.eclipselabs.blueprints.emf.junit.tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.User;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Edge;

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
		
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user1)));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user2)));
		
		
		assertNotNull(graph.getEdge(GraphUtil.getEdgeID(user1, user2, ModelPackage.eINSTANCE.getUser_Friends())));
		Edge edge = graph.getEdge(GraphUtil.getEdgeID(user1, user2, ModelPackage.eINSTANCE.getUser_Friends()));
		
		System.out.println(edge);
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
		
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user1)));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user2)));
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user3)));
		
		assertNotNull(graph.getEdge(GraphUtil.getEdgeID(user1, user2, ModelPackage.eINSTANCE.getUser_Friends())));
		Edge edge = graph.getEdge(GraphUtil.getEdgeID(user1, user2, ModelPackage.eINSTANCE.getUser_Friends()));
		
		System.out.println(edge);
	}
	
}
