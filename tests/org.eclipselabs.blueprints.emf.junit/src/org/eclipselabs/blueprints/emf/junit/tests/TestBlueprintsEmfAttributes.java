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
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Vertex;

/**
 * 
 * @author ghillairet
 *
 */
public class TestBlueprintsEmfAttributes extends TestSupport {
	
	@Test
	public void testSaveObjectWithTwoStringAttributes() throws IOException {
		User user = ModelFactory.eINSTANCE.createUser();
		user.setUserId("1");
		user.setName("John");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/tmp/test"));
		
		assertNotNull(resource);
		
		resource.getContents().add(user);
		
		resource.save(options);
		
		assertTrue(graph.getVertices().iterator().hasNext());
		assertNotNull(graph.getVertex(EcoreUtil.getURI(user)));
		Vertex vertex = graph.getVertex(EcoreUtil.getURI(user));
		
		assertNotNull(vertex.getProperty("userId"));
		assertEquals("1", vertex.getProperty("userId"));
		assertTrue(vertex.getProperty("userId") instanceof String);
		assertNotNull(vertex.getProperty("name"));
		assertTrue(vertex.getProperty("name") instanceof String);
		assertEquals("John", vertex.getProperty("name"));
	}
	
	@Test
	public void testLoadOneObjectWithTwoAttributes() throws IOException {
		Vertex eC = graph.addVertex(EcoreUtil.getURI(ModelPackage.eINSTANCE.getUser()));
		
		Vertex v1 = graph.addVertex("graph:/tmp/test#1");
		v1.setProperty("userId", "1");
		v1.setProperty("name", "John");
		
		graph.addEdge(null, v1, eC, "eClass");
		
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
	
}
