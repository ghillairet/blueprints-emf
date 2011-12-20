package org.eclipselabs.blueprints.emf.rexster.junit.support;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipselabs.blueprints.emf.GraphURIHandlerImpl;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.util.Tokens;
import org.junit.Before;

import com.tinkerpop.blueprints.pgm.impls.rexster.RexsterGraph;

public class TestSupport {
	
	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected ResourceSet resourceSet;
	protected RexsterGraph graph;
	protected String baseURI = "http://localhost:7788/graphs/emptygraph";
	
	@Before
	public void tearUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		
		graph = new RexsterGraph(baseURI);
		options.put(Tokens.BLUEPRINTS_GRAPH_OBJECT, graph);
		
		resourceSet = new ResourceSetImpl();
		resourceSet.getLoadOptions().putAll(options);
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl());
	}
}
