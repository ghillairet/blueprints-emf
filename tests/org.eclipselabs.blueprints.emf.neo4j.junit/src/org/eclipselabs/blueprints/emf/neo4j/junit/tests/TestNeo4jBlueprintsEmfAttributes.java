package org.eclipselabs.blueprints.emf.neo4j.junit.tests;

import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_ECLASS;
import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_INDEX_KEY;
import static org.eclipselabs.blueprints.emf.util.Tokens.RESOURCE_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.junit.model.ETypes;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.User;
import org.eclipselabs.blueprints.emf.neo4j.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Vertex;

public class TestNeo4jBlueprintsEmfAttributes extends TestSupport {
	
	@Test
	public void testSaveObjectWithTwoStringAttributes() throws IOException {
		assertFalse(graph.getVertices().iterator().hasNext());
		
		User user = ModelFactory.eINSTANCE.createUser();
		user.setUserId("1");
		user.setName("John");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(user);		
		resource.save(options);
		
		assertTrue(graph.getVertices().iterator().hasNext());
		
		Vertex vertex = GraphUtil.getVertex(user, graph);
		
		assertNotNull(vertex);
		
		assertNotNull(vertex.getProperty("userId"));
		assertEquals("1", vertex.getProperty("userId"));
		assertTrue(vertex.getProperty("userId") instanceof String);
		assertNotNull(vertex.getProperty("name"));
		assertTrue(vertex.getProperty("name") instanceof String);
		assertEquals("John", vertex.getProperty("name"));
	}
	
	@Test
	public void testSaveObjectWithManyValueAttribute() throws IOException {
		assertFalse(graph.getVertices().iterator().hasNext());
		
		ETypes e = ModelFactory.eINSTANCE.createETypes();
		e.getEStrings().add("one");
		e.getEStrings().add("two");
		e.getEStrings().add("three");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		
		resource.getContents().add(e);		
		resource.save(options);
		
		Vertex v = GraphUtil.getVertex(e, graph);
		assertNotNull(v);
		
		assertTrue(v.getProperty("eStrings").getClass().isArray());
		Object[] array = (Object[]) v.getProperty("eStrings");
		
		assertEquals(3, array.length);
		assertEquals("one", array[0]);
		assertEquals("two", array[1]);
		assertEquals("three", array[2]);
	}
	
	@Test
	public void testLoadOneObjectWithTwoAttributes() throws IOException {
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Vertex v1 = graph.addVertex(null);
		v1.setProperty(BLUEPRINTS_EMF_INDEX_KEY, "graph:/tmp/test#1");
		v1.setProperty(BLUEPRINTS_EMF_ECLASS, EcoreUtil.getURI(ModelPackage.eINSTANCE.getUser()).toString());
		v1.setProperty(RESOURCE_URI, "graph:/tmp/test");
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		resource.load(options);
		
		assertEquals(1, resource.getContents().size());
		
		EObject object = resource.getContents().get(0);
		assertEquals(ModelPackage.eINSTANCE.getUser(), object.eClass());
		
		User user = (User) object;
		assertEquals("1", user.getUserId());
		assertEquals("John", user.getName());
	}
	
	@Test
	public void testLoadTwoRootObjectWithTwoAttributes() throws IOException {
		assertFalse(graph.getVertices().iterator().hasNext());
		
		Vertex v1 = graph.addVertex(null);
		v1.setProperty(BLUEPRINTS_EMF_INDEX_KEY, "graph:/tmp/test#1");
		v1.setProperty(BLUEPRINTS_EMF_ECLASS, EcoreUtil.getURI(ModelPackage.eINSTANCE.getUser()).toString());
		v1.setProperty(RESOURCE_URI, "graph:/tmp/test");
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		Vertex v2 = graph.addVertex(null);
		v2.setProperty(BLUEPRINTS_EMF_INDEX_KEY, "graph:/tmp/test#2");
		v2.setProperty(BLUEPRINTS_EMF_ECLASS, EcoreUtil.getURI(ModelPackage.eINSTANCE.getUser()).toString());
		v2.setProperty(RESOURCE_URI, "graph:/tmp/test");
		v2.setProperty("userId", "2");
		v2.setProperty("name", "Paul");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		assertNotNull(resource);
		assertFalse(resource.isLoaded());
		assertTrue(resource.getContents().isEmpty());
		resource.load(options);
		
		assertEquals(2, resource.getContents().size());
		
		EObject o1 = resource.getEObject("1");
		assertEquals(ModelPackage.eINSTANCE.getUser(), o1.eClass());
		
		User u1 = (User) o1;
		assertEquals("1", u1.getUserId());
		assertEquals("John", u1.getName());
		
		EObject o2 = resource.getEObject("2");
		assertEquals(ModelPackage.eINSTANCE.getUser(), o2.eClass());
		
		User u2 = (User) o2;
		assertEquals("2", u2.getUserId());
		assertEquals("Paul", u2.getName());
	}
	
}
