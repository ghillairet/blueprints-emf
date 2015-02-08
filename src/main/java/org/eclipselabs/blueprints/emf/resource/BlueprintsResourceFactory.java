package org.eclipselabs.blueprints.emf.resource;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

public class BlueprintsResourceFactory extends ResourceFactoryImpl {

    private final Graph graph;

    public BlueprintsResourceFactory(Graph graph) {
        this.graph = graph;

        if (graph instanceof KeyIndexableGraph) {
            prepareIndexes((KeyIndexableGraph) graph);
        }
    }

    @Override
    public Resource createResource(URI uri) {
        return new BlueprintsResource(uri, graph);
    }

    private void prepareIndexes(KeyIndexableGraph graph) {
        graph.createKeyIndex("_eClass", Vertex.class);
        graph.createKeyIndex("_eResource", Vertex.class);
        graph.createKeyIndex("_eURI", Vertex.class);
        graph.createKeyIndex("_eContent", Vertex.class);
    }
}
