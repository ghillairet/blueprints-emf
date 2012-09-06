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
package org.eclipselabs.blueprints.emf;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipselabs.blueprints.emf.impl.GraphRegistryImpl;
import com.tinkerpop.blueprints.KeyIndexableGraph;

/**
 * 
 * @author ghillairet
 *
 */
public interface GraphURIHandler extends URIHandler {
	
	public static final String USE_ID_ATTRIBUTE_AS_KEY = "USE_ID_ATTRIBUTE_AS_KEY";
	public static final String EXTRINSIC_ID_KEY = "EXTRINSIC_ID_KEY";
	
	public static interface Registry extends Map<String, KeyIndexableGraph> {
		
		KeyIndexableGraph getGraph(URI uri);
		
		boolean containsURI(URI uri);
		
		Registry INSTANCE = new GraphRegistryImpl();
		
	}
	
}
