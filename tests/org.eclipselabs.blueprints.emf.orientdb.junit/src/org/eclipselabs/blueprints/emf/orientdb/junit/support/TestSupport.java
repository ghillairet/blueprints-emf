package org.eclipselabs.blueprints.emf.orientdb.junit.support;

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
import org.junit.After;
import org.junit.Before;

import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class TestSupport {

	protected final Map<String ,Object> options = new HashMap<String, Object>();
	protected static ResourceSet resourceSet;
	protected static IndexableGraph graph;

	@After
	public void tearDown() {
		graph.dropIndex(Tokens.BLUEPRINTS_EMF_EDGE_INDEX);
		graph.dropIndex(Tokens.BLUEPRINTS_EMF_VERTEX_INDEX);
		graph.clear();
		graph.shutdown();
	}
	
	@Before
	public void tearUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);

		graph = new OrientGraph("local:/tmp/orient/tests");
		
		resourceSet = new ResourceSetImpl();

		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl(graph));
	}
	
}
