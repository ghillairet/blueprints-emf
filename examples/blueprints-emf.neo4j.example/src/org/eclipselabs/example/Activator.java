package org.eclipselabs.example;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipselabs.blueprints.emf.GraphURIHandlerImpl;
import org.eclipselabs.example.socnet.Person;
import org.eclipselabs.example.socnet.SocnetFactory;
import org.eclipselabs.example.socnet.SocnetPackage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.tinkerpop.blueprints.pgm.IndexableGraph;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Neo4J Example");
		
		EPackage.Registry.INSTANCE.put(SocnetPackage.eNS_URI, SocnetPackage.eINSTANCE);
		IndexableGraph graph = new Neo4jGraph("/data/neo4j/socnet");
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl(graph));
		
		Person p = SocnetFactory.eINSTANCE.createPerson();
		p.setName("John");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://data/neo4j/socnet/"));
		resource.getContents().add(p);
		
		resource.save(null);
		
		System.out.println("Shutdown Neo4J");
		graph.shutdown();
		System.out.println("Neo4J down !!");
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start Neo4J Example");
		
		EPackage.Registry.INSTANCE.put(SocnetPackage.eNS_URI, SocnetPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("graph", new XMIResourceFactoryImpl());
		IndexableGraph graph = new Neo4jGraph("/data/neo4j/socnet");
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl(graph));
		
		Person john = SocnetFactory.eINSTANCE.createPerson();
		john.setName("John");
		
		Person paul = SocnetFactory.eINSTANCE.createPerson();
		paul.setName("Paul");
		
		john.getFriends().add(paul);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://data/neo4j/socnet/"));
		resource.getContents().add(john);
		resource.getContents().add(paul);
		
		resource.save(null);
		
		System.out.println(graph.getVertices().iterator().hasNext());
		System.out.println("Shutdown Neo4J");
		graph.shutdown();
		System.out.println("Neo4J down !!");	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye !!");
	}

}
