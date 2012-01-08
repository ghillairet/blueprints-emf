package org.eclipselabs.blueprints.emf.junit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.Node;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.eclipselabs.blueprints.emf.util.GraphUtil;
import org.eclipselabs.blueprints.emf.util.Tokens;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

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
		
		System.out.println(firstVertex.getProperty(Tokens.BLUEPRINTS_EMF_PROXY_URI));
		System.out.println(secondVertex.getProperty(Tokens.BLUEPRINTS_EMF_PROXY_URI));
		
		assertNotNull(firstVertex);
		assertNotNull(secondVertex);
		
		assertEquals("a", firstVertex.getProperty("name"));
		assertEquals("b", secondVertex.getProperty("name"));
		
		Edge edge = GraphUtil.getEdge(GraphUtil.getEdgeID(firstNode, secondNode, ModelPackage.eINSTANCE.getNode_OneRefNode()), graph);
		
		assertNotNull(edge);
		
		System.out.println(edge);
	}
}
