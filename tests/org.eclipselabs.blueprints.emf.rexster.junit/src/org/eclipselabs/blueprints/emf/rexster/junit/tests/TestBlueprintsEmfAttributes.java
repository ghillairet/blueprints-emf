package org.eclipselabs.blueprints.emf.rexster.junit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.junit.model.ETypes;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.User;
import org.eclipselabs.blueprints.emf.rexster.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Vertex;

public class TestBlueprintsEmfAttributes extends TestSupport {
	@Test
	public void testSaveObjectWithTwoStringAttributes() throws IOException {
		User user = ModelFactory.eINSTANCE.createUser();
		user.setUserId("1");
		user.setName("John");
		
		Resource resource = resourceSet.createResource(URI.createURI(baseURI));
		assertNotNull(resource);
		
		resource.getContents().add(user);		
		resource.save(options);
		
		assertTrue(graph.getVertices().iterator().hasNext());
		assertTrue(GraphUtil.hasVertex(user, graph));
		
		Vertex vertex = GraphUtil.getVertex(user, graph);
		
		assertNotNull(vertex.getProperty("userId"));
		assertEquals("1", vertex.getProperty("userId"));
		assertTrue(vertex.getProperty("userId") instanceof String);
		assertNotNull(vertex.getProperty("name"));
		assertTrue(vertex.getProperty("name") instanceof String);
		assertEquals("John", vertex.getProperty("name"));
	}
	
	@Test
	public void testSaveObjectWithManyValueAttribute() throws IOException, JSONException {
		ETypes e = ModelFactory.eINSTANCE.createETypes();
		e.getEStrings().add("one");
		e.getEStrings().add("two");
		e.getEStrings().add("three");
		
		Resource resource = resourceSet.createResource(URI.createURI(baseURI));
		assertNotNull(resource);
		
		resource.getContents().add(e);		
		resource.save(options);
		
		assertTrue(GraphUtil.hasVertex(e, graph));
		
		Vertex v = GraphUtil.getVertex(e, graph);
		
		assertTrue(v.getProperty("eStrings") instanceof JSONArray);
		JSONArray values = (JSONArray) v.getProperty("eStrings");
		assertEquals(3, values.length());
		
		assertEquals("one", ((JSONObject)values.get(0)).get("value"));
		assertEquals("two", ((JSONObject)values.get(1)).get("value"));
		assertEquals("three", ((JSONObject)values.get(2)).get("value"));
	}
	
//	@Test
	public void testLoadOneObjectWithTwoAttributes() throws IOException {
		Vertex v1 = graph.addVertex("1");
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		v1.setProperty("eClass", EcoreUtil.getURI(ModelPackage.eINSTANCE.getUser()));
		
//		graph.addEdge(null, v1, eC, "eClass");
		
		Resource resource = resourceSet.createResource(URI.createURI(baseURI));
		assertNotNull(resource);
		resource.load(options);
		
		assertEquals(1, resource.getContents().size());
		
		EObject object = resource.getContents().get(0);
		assertEquals(ModelPackage.eINSTANCE.getUser(), object.eClass());
		
		User user = (User) object;
		assertEquals("1", user.getUserId());
		assertEquals("John", user.getName());
	}
}
