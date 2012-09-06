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
package org.eclipselabs.blueprints.emf.neo4j.junit.support;

import java.util.ArrayList;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.IndexProvider;
import org.neo4j.index.lucene.LuceneIndexProvider;
import org.neo4j.kernel.ListIndexIterable;
import org.neo4j.tooling.GlobalGraphOperations;

import com.tinkerpop.blueprints.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;

public abstract class TestSupport {

	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected static ResourceSet resourceSet;
	protected static GraphDatabaseService graphDb;
	protected static Neo4jGraph graph;
	
	protected static void clearGraphDb() {
		System.out.println("Cleaning Database ...");
		for (Node node: GlobalGraphOperations.at(graphDb).getAllNodes()) {
			Transaction tx = graphDb.beginTx();
			try {
				for (Relationship relationship: node.getRelationships()) {
					relationship.delete();
				}
				node.delete();
				tx.success();
			} finally {
				tx.finish();
			}
		}
		
		System.out.println("... Database cleaned.");
	}

	protected static void registerShutdownHook( final GraphDatabaseService graphDb ) {
		Runtime.getRuntime().addShutdownHook( new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
	
	@AfterClass
	public static void tearDown() {
		graphDb.shutdown();
	}
	
	@BeforeClass
	public static void tearUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);

		final GraphDatabaseFactory gdbf = new GraphDatabaseFactory();
        
		//the cache providers 1.8
        // ArrayList<CacheProvider> cacheList = new ArrayList<CacheProvider>();
        // cacheList.add( new SoftCacheProvider() );
        
		 //the index providers
        IndexProvider lucene = new LuceneIndexProvider();
        ArrayList<IndexProvider> provs = new ArrayList<IndexProvider>();
        provs.add( lucene );
        ListIndexIterable providers = new ListIndexIterable();
        providers.setIndexProviders( provs );
        
//        gdbf.setCacheProviders( cacheList );
        gdbf.setIndexProviders( providers );
        
		graphDb = gdbf.newEmbeddedDatabase( "/data/neo4j/tests/1/" );
		graph = new Neo4jGraph(graphDb);
		GraphURIHandler.Registry.INSTANCE.put("graph://tmp/test", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://tmp/test/nodes", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://test/hundreds", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://test/thousands", graph);
		GraphURIHandler.Registry.INSTANCE.put("graph://test/millions", graph);
		
		registerShutdownHook( graphDb );
		
		clearGraphDb();
		
		resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl());
	}
	
	@After
	public void setDown() {
		graph.stopTransaction(Conclusion.SUCCESS);
		clearGraphDb();
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
