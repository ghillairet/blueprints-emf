package org.eclipselabs.blueprints.emf.junit.support;

import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NeoTestSupport {

    protected final Map<String ,Object> options = new HashMap<String, Object>();
    protected static ResourceSet resourceSet;
    protected static GraphDatabaseService graphDb;
    protected static Neo4jGraph graph;
    protected static final URL testURI = NeoTestSupport.class.getResource("/tests");

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

    @After
    public void setDown() {
        graph.stopTransaction(TransactionalGraph.Conclusion.SUCCESS);
        clearGraphDb();
    }

    @BeforeClass
    public static void tearUp() {
        EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);

        final GraphDatabaseFactory gdbf = new GraphDatabaseFactory();
        final IndexProvider lucene = new LuceneIndexProvider();
        ArrayList<IndexProvider> provs = new ArrayList<IndexProvider>();
        provs.add( lucene );

        ListIndexIterable providers = new ListIndexIterable();
        providers.setIndexProviders( provs );

        gdbf.setIndexProviders( providers );

        graphDb = gdbf.newEmbeddedDatabase(testURI.getPath());
        graph = new Neo4jGraph(graphDb);

        registerShutdownHook( graphDb );
        clearGraphDb();

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
