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
import org.eclipselabs.blueprints.emf.GraphURIHandler;
import org.eclipselabs.blueprints.emf.impl.GraphURIHandlerImpl;
import org.eclipselabs.example.socnet.Person;
import org.eclipselabs.example.socnet.SocnetFactory;
import org.eclipselabs.example.socnet.SocnetPackage;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start Neo4J Example");
		
		EPackage.Registry.INSTANCE.put(SocnetPackage.eNS_URI, SocnetPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("graph", new XMIResourceFactoryImpl());
		Neo4jGraph graph = new Neo4jGraph("data/neo4j/socnet");
		GraphURIHandler.Registry.INSTANCE.put("graph://data/neo4j/socnet/", graph);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl());
		
		Person john = SocnetFactory.eINSTANCE.createPerson();
		john.setName("John");
		
		Person paul = SocnetFactory.eINSTANCE.createPerson();
		paul.setName("Paul");
		
		Person andre = SocnetFactory.eINSTANCE.createPerson();
		andre.setName("Andre");
		
		Person benoit = SocnetFactory.eINSTANCE.createPerson();
		benoit.setName("Benoit");
		
		john.getFriends().add(paul);
		paul.getFriends().add(benoit);
		john.getFriends().add(andre);
		andre.getFriends().add(benoit);
		
		Resource resource = resourceSet.createResource(URI.createURI("graph://data/neo4j/socnet/"));
		resource.getContents().add(john);
		resource.getContents().add(paul);
		resource.getContents().add(andre);
		resource.getContents().add(benoit);
		
		resource.save(null);
		
		System.out.println("Created vertices:");
		for (Vertex v: graph.getVertices()){
			System.out.println("    "+v+": "+v.getProperty("name"));
		}
		System.out.println("and edges:");
		for (Edge e: graph.getEdges()) {
			System.out.println("    "+e);
		}
		
		System.out.println("Neo4J down !!");
		
		System.out.println("Unloading resource...");
		resource.unload();
		System.out.println("Reloading resource...");
		resource.load(null);
		
		resource.save(System.out, null);
		
		System.out.println("Shutdown Neo4J");
		graph.shutdown();
	}
	
}
