package org.eclipselabs.blueprints.emf.resource;

import com.tinkerpop.blueprints.Graph;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class BlueprintsResource extends ResourceImpl {

    private final Graph graph;

    public BlueprintsResource(URI uri, Graph graph) {
        super(uri);
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
        if (outputStream instanceof URIConverter.Saveable) {
            ((URIConverter.Saveable) outputStream).saveResource(this);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
        if (inputStream instanceof URIConverter.Loadable) {
            ((URIConverter.Loadable) inputStream).loadResource(this);
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
