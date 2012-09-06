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
package org.eclipselabs.blueprints.emf.orientdb.junit.support;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipselabs.blueprints.emf.GraphURIHandler;
import org.eclipselabs.blueprints.emf.impl.GraphURIHandlerImpl;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.util.Tokens;
import org.junit.After;
import org.junit.Before;

import com.tinkerpop.blueprints.IndexableGraph;
import com.tinkerpop.blueprints.impls.orientdb.OrientGraph;

public class TestSupport {

	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected static ResourceSet resourceSet;
	protected static IndexableGraph graph;

	@After
	public void tearDown() {
		graph.dropIndex(Tokens.BLUEPRINTS_EMF_EDGE_INDEX);
		graph.dropIndex(Tokens.BLUEPRINTS_EMF_VERTEX_INDEX);
		graph.clear();
		graph.shutdown();
	}
	
	@Before
	public void tearUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);

		graph = new OrientGraph("local:/tmp/orient/tests");
		
		GraphURIHandler.Registry.INSTANCE.put("graph:/tmp/test", graph);
		
		resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl());
	}
	
}
