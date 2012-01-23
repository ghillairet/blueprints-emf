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
package org.eclipselabs.blueprints.emf.neo4j.junit.suite;

import org.eclipselabs.blueprints.emf.neo4j.junit.tests.TestNeo4jBlueprintsEmfAttributes;
import org.eclipselabs.blueprints.emf.neo4j.junit.tests.TestNeo4jBlueprintsEmfReferences;
import org.eclipselabs.blueprints.emf.neo4j.junit.tests.TestSaveLoadBenchMark;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestNeo4jBlueprintsEmfAttributes.class, TestNeo4jBlueprintsEmfReferences.class,
	TestSaveLoadBenchMark.class
})
public class TestSuite {}

