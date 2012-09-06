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
package org.eclipselabs.blueprints.emf.junit.support;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.GraphURIHandler;
import org.eclipselabs.blueprints.emf.impl.GraphURIHandlerImpl;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.util.PropertyKind;
import org.junit.Before;

import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public abstract class TestSupport {
	
	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected ResourceSet resourceSet;
	protected KeyIndexableGraph graph;
	
	@Before
	public void tearUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		
		graph = new TinkerGraph();
		GraphURIHandler.Registry.INSTANCE.put("graph://tmp/test", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://tmp/tests/first", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://tmp/tests/second", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://tmp/test/nodes", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://nodes/", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://test/hundreds", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://test/thousands", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://test/millions", graph);
		
		resourceSet = new ResourceSetImpl();
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl());
	}
	
	protected Vertex createResourceVertex(String uri) {
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(PropertyKind.eResource.toString(), uri);
		
		return vertex;
	}
	
	protected Vertex createVertex(String uri, EClass eClass) {
		Vertex v = graph.addVertex(null);
		v.setProperty(PropertyKind.eClass.toString(), EcoreUtil.getURI(eClass).toString());
		v.setProperty(PropertyKind.eURI.toString(), uri);
		
		return v;
	}
	
}
