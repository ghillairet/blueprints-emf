Blueprints EMF
===

Uses [Blueprints](https://github.com/tinkerpop/blueprints) to store [EMF](http://www.eclipse.org/emf) models
in Graph databases such as Neo4J, OrientDB, Titan, etc... .

Current version is 0.3.0.

### Dependencies

* Blueprints 2.6.0 or later

### Installation

Will be available on maven central.

For now do

```
git clone https://github.com/ghillairet/blueprints-emf.git

cd blueprints-emf

mvn clean install
```

And install the dependency in your maven project

```xml
<dependency>
    <groupId>org.eclipselabs</groupId>
    <artifactId>blueprints-emf</artifactId>
    <version>0.3.0</version>
</dependency>
```

### Usage

```java
TinkerGraph graph = new TinkerGraph();
ResourceSet resourceSet = new ResourceSetImpl();

resourceSet
    .getResourceFactoryRegistry()
    .getExtensionToFactoryMap()
    .put("*", new BlueprintsResourceFactory(graph));

resourceSet
    .getURIConverter()
    .getURIHandlers()
    .add(0, new GraphHandler());

User user = ModelFactory.eINSTANCE.createUser();
user.setUserId("1");
user.setName("John");

Resource resource = resourceSet.createResource(URI.createURI("graph:/my/graph/users"));
resource.getContents().add(user);
resource.save(null);
```
