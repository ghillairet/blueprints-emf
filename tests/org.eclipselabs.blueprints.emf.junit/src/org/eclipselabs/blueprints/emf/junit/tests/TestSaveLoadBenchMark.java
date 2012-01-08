package org.eclipselabs.blueprints.emf.junit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.PrimaryObject;
import org.eclipselabs.blueprints.emf.junit.model.TargetObject;
import org.eclipselabs.blueprints.emf.junit.support.TestSupport;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Vertex;

public class TestSaveLoadBenchMark extends TestSupport {

	@Test
	public void testSaveOneParentHundredsOfChild() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		long start = System.currentTimeMillis();
		
		PrimaryObject parent = ModelFactory.eINSTANCE.createPrimaryObject();
		parent.setIdAttribute("0");
		parent.setName("parent");
		
		for (int i=0;i<100;i++) {
			TargetObject child = ModelFactory.eINSTANCE.createTargetObject();
			child.setSingleAttribute("child"+i);
			parent.getMultipleContainmentReferenceNoProxies().add(child);
		}
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://test/hundreds"));
		resource.getContents().add(parent);
		resource.save(options);
		
		long end = (System.currentTimeMillis() - start);
		
		System.out.println("Save 101 objects in "+end/1000.+" s.");
		
		int count = 0;
		for (Iterator<Vertex> it = graph.getVertices().iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		assertEquals(101, count);
	}
	
	@Test
	public void testSaveThousands() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		long start = System.currentTimeMillis();
		
		PrimaryObject parent = ModelFactory.eINSTANCE.createPrimaryObject();
		parent.setIdAttribute("0");
		parent.setName("parent");
		
		for (int i=0;i<1000;i++) {
			TargetObject child = ModelFactory.eINSTANCE.createTargetObject();
			child.setSingleAttribute("child"+i);
			parent.getMultipleContainmentReferenceNoProxies().add(child);
		}
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://test/thousands"));
		resource.getContents().add(parent);
		resource.save(options);
		
		long end = (System.currentTimeMillis() - start);
		
		System.out.println("Save 1001 objects in "+end/1000.+" s.");
		
		int count = 0;
		for (Iterator<Vertex> it = graph.getVertices().iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		assertEquals(1001, count);
	}
	
	@Test
	public void testSaveHundredThousands() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		long start = System.currentTimeMillis();
		
		PrimaryObject parent = ModelFactory.eINSTANCE.createPrimaryObject();
		parent.setIdAttribute("0");
		parent.setName("parent");
		
		for (int i=0;i<100000;i++) {
			TargetObject child = ModelFactory.eINSTANCE.createTargetObject();
			child.setSingleAttribute("child"+i);
			parent.getMultipleContainmentReferenceNoProxies().add(child);
		}
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://test/millions"));
		resource.getContents().add(parent);
		resource.save(options);
		
		long end = (System.currentTimeMillis() - start);
		
		System.out.println("Save 100001 objects in "+end/1000.+" s.");
		
		int count = 0;
		for (Iterator<Vertex> it = graph.getVertices().iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		assertEquals(100001, count);
	}
}
