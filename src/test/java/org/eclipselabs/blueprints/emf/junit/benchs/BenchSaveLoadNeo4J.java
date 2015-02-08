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
package org.eclipselabs.blueprints.emf.junit.benchs;

import com.tinkerpop.blueprints.Vertex;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.blueprints.emf.junit.model.ModelFactory;
import org.eclipselabs.blueprints.emf.junit.model.PrimaryObject;
import org.eclipselabs.blueprints.emf.junit.model.TargetObject;
import org.eclipselabs.blueprints.emf.junit.support.NeoTestSupport;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BenchSaveLoadNeo4J extends NeoTestSupport {

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
		
		long end = (System.currentTimeMillis() - start);
		System.out.println("Created 101 objects in "+end/1000.+" s.");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://test/hundreds"));
		resource.getContents().add(parent);
		
		start = System.currentTimeMillis();
		resource.save(options);
		
		end = (System.currentTimeMillis() - start);
		
		System.out.println("Save 101 objects in "+end/1000.+" s.");
		
		int count = 0;
		for (Iterator<Vertex> it = graph.getVertices().iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		assertEquals(102, count);
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
		
		assertEquals(1002, count);
	}

	@Test
	public void testSaveTenThousands() throws IOException {
		assertFalse(graph.getEdges().iterator().hasNext());
		assertFalse(graph.getVertices().iterator().hasNext());
		
		long start = System.currentTimeMillis();
		
		PrimaryObject parent = ModelFactory.eINSTANCE.createPrimaryObject();
		parent.setIdAttribute("0");
		parent.setName("parent");
		
		for (int i=0;i<10000;i++) {
			TargetObject child = ModelFactory.eINSTANCE.createTargetObject();
			child.setSingleAttribute("child"+i);
			parent.getMultipleContainmentReferenceNoProxies().add(child);
		}
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://test/millions"));
		resource.getContents().add(parent);
		resource.save(options);
		
		long end = (System.currentTimeMillis() - start);
		
		System.out.println("Save 10001 objects in "+end/1000.+" s.");
		
		int count = 0;
		for (Iterator<Vertex> it = graph.getVertices().iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		assertEquals(10002, count);
	}
}
