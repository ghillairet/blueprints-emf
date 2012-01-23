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
package org.eclipselabs.blueprints.emf.impl;

import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipselabs.blueprints.emf.GraphURIHandler.Registry;

import com.tinkerpop.blueprints.pgm.IndexableGraph;

/**
 * 
 * @author ghillairet
 *
 */
public class GraphRegistryImpl extends HashMap<String, IndexableGraph> implements Registry {

	@Override
	public IndexableGraph getGraph(URI uri) {
		URI key = uri;
		if (key.hasFragment())
			key = key.trimFragment();
		if (key.hasQuery())
			key = key.trimQuery();
		
		return get(key.toString());
	}

	@Override
	public boolean containsURI(URI uri) {
		URI key = uri;
		if (key.hasFragment())
			key = key.trimFragment();
		if (key.hasQuery())
			key = key.trimQuery();
		if (containsKey(key.toString()))
			return true;
		return false;
	}
	
	private static final long serialVersionUID = -5307175763573424417L;
	
}
