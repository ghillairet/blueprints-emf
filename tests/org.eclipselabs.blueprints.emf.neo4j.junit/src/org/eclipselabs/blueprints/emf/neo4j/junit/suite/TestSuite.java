package org.eclipselabs.blueprints.emf.neo4j.junit.suite;

import org.eclipselabs.blueprints.emf.neo4j.junit.tests.TestNeo4jBlueprintsEmfAttributes;
import org.eclipselabs.blueprints.emf.neo4j.junit.tests.TestNeo4jBlueprintsEmfReferences;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestNeo4jBlueprintsEmfAttributes.class, TestNeo4jBlueprintsEmfReferences.class})
public class TestSuite {}

