Blueprints EMF, EMF Models in Graph DBs.

Current version is 0.1.0.

### Dependencies

* Blueprints 1.1 or later

### Installation

see [wiki](https://github.com/ghillairet/blueprints-emf/wiki/installation)

### Usage

		IndexableGraph graph = new TinkerGraph();
		ResourceSet resourceSet = new ResourceSetImpl();
		
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		uriHandlers.add(0, new GraphURIHandlerImpl(graph));
		
		User user = ModelFactory.eINSTANCE.createUser();
		user.setUserId("1");
		user.setName("John");
		
		Resource resource = resourceSet.createResource(URI.createURI("graph:/my/graph/users"));
		
		resource.getContents().add(user);		
		resource.save(null);

