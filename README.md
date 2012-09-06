Blueprints EMF, [EMF](http://www.eclipse.org/emf) Models in Graph DBs.

Current version is 0.2.0.

### Dependencies

* Blueprints 2.1.0 or later

### Installation

See [wiki](https://github.com/ghillairet/blueprints-emf/wiki/installation)

### Example

See [example](https://github.com/ghillairet/blueprints-emf/tree/master/examples/blueprints-emf.neo4j.example)

### Usage

		TinkerGraph graph = new TinkerGraph();
		ResourceSet resourceSet = new ResourceSetImpl();

		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl(graph));

		User user = ModelFactory.eINSTANCE.createUser();
		user.setUserId("1");
		user.setName("John");

        GraphURIHandler.Registry.INSTANCE.put("graph://my/graph/users", graph);
		Resource resource = resourceSet.createResource(URI.createURI("graph:/my/graph/users"));

		resource.getContents().add(user);
		resource.save(null);

