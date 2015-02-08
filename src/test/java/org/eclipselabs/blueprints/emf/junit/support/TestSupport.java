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

import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.blueprints.emf.GraphHandler;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.resource.BlueprintsResourceFactory;
import org.eclipselabs.blueprints.emf.util.PropertyKind;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public abstract class TestSupport {

	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected ResourceSet resourceSet;
	protected KeyIndexableGraph graph;

	@Before
	public void tearUp() {
        EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);

		graph = new TinkerGraph();
		resourceSet = new ResourceSetImpl();
        resourceSet
                .getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new BlueprintsResourceFactory(graph));
		resourceSet
                .getURIConverter()
                .getURIHandlers()
                .add(0, new GraphHandler());
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
