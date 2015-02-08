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
package org.eclipselabs.blueprints.emf.junit.suite;

import org.eclipselabs.blueprints.emf.junit.tests.TestBlueprintsEmfAttributes;
import org.eclipselabs.blueprints.emf.junit.tests.TestBlueprintsEmfReferences;
import org.eclipselabs.blueprints.emf.junit.tests.TestObjectID;
import org.eclipselabs.blueprints.emf.junit.tests.TestResourceProxies;
import org.eclipselabs.blueprints.emf.junit.benchs.BenchSaveLoadNeo4J;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestBlueprintsEmfAttributes.class, 
	TestBlueprintsEmfReferences.class,
	TestObjectID.class,
	TestResourceProxies.class,
	BenchSaveLoadNeo4J.class
})
public class TestSuite {}
