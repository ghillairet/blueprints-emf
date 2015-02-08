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
package org.eclipselabs.blueprints.emf.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

public class GraphUtil {
	
	public static Vertex getVertex(EObject eObject, Graph graph) {
		if (eObject == null || graph == null)
			return null;
		
		final URI uri = EcoreUtil.getURI(eObject);
		if (uri == null)
			return null;
		
		
		final Iterable<Vertex> vertices = graph.getVertices(PropertyKind.eURI.toString(), uri.toString());
		
		if (vertices.iterator().hasNext())
			return vertices.iterator().next();
		else
			return null;
	}
	
	public static URI getURI(Vertex vertex) {
		String eKey = vertex.getProperty(PropertyKind.eURI.toString());
		if (eKey != null) {
			return URI.createURI(eKey.replaceAll("%23", "#"));
		}
		return null;
	}
	
}
