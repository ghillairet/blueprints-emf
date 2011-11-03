package org.eclipselabs.blueprints.emf.junit.suite;

import org.eclipselabs.blueprints.emf.junit.tests.TestBlueprintsEmfAttributes;
import org.eclipselabs.blueprints.emf.junit.tests.TestBlueprintsEmfReferences;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestBlueprintsEmfAttributes.class, TestBlueprintsEmfReferences.class})
public class TestSuite {}
