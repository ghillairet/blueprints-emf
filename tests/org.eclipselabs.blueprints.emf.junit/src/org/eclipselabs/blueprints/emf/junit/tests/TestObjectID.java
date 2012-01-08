package org.eclipselabs.blueprints.emf.junit.tests;

import static org.eclipselabs.blueprints.emf.util.Tokens.BLUEPRINTS_EMF_INDEX_KEY;
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
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

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
		assertEquals(GraphUtil.safeURI(EcoreUtil.getURI(root)), a.getProperty(BLUEPRINTS_EMF_INDEX_KEY));
		
		for (Edge edge: a.getOutEdges("children")) {
			Vertex v = edge.getInVertex();
			assertEquals("b", v.getProperty("name"));
			assertEquals(GraphUtil.safeURI(EcoreUtil.getURI(((Node)root).getChildren().get(0))), v.getProperty(BLUEPRINTS_EMF_INDEX_KEY));
		}
		
		for (Edge edge: a.getOutEdges("oneRefNode")) {
			Vertex v = edge.getInVertex();
			assertEquals("e", v.getProperty("name"));
			assertEquals(GraphUtil.safeName("graph://nodes/#//@children.0/@children.0/@children.0/@children.0"), v.getProperty(BLUEPRINTS_EMF_INDEX_KEY));
		}
		
	}
	
	@Test
	public void testLoadObjectTreeWithoutID() throws IOException {
		resource.unload();
		assertTrue(resource.getContents().isEmpty());
		
		resource.load(options);
		assertFalse(resource.getContents().isEmpty());
		assertEquals(1, resource.getContents().size());
		
//		Resource nodes = new XMIResourceImpl();
//		nodes.getContents().addAll(resource.getContents());
//		nodes.setURI(URI.createURI("tests/out-1.xmi"));
//		nodes.save(null);
		
		EObject root = resource.getEObject("/");
		
		assertTrue(root instanceof Node);
		
		Node rootNode = (Node)root;
		assertEquals(1, rootNode.getChildren().size());
		assertEquals("a", rootNode.getName());
		
		assertEquals(1, resource.getContents().size());
		
	}
}
