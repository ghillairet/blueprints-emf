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

import org.eclipse.emf.ecore.EPackage;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.junit.Test;

public class TestURIHandler {

	@Test
	public void testGraphInFolder() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		
//		IndexableGraph graph = new TinkerGraph("/blueprints/graphs/tests");
//		ResourceSet resourceSet = new ResourceSetImpl();
		
//		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
//		uriHandlers.add(0, new GraphURIHandlerImpl(graph));
		
		
	}
}
