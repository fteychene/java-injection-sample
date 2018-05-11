# Java Injection Engine

A small dependency injection engine in Java 8.  
This project is a sample of a engine for teaching purpose.

# Usage

```java
Engine engine = new Engine();
engine.register(Animal.class, Dog::new);
engine.register(Food.class, Meat::new);

engine.register(Owner.class, 
    dependencies -> new Owner("Johnny", dependencies.get(Animal.class), dependencies.get(Food.class)), 
    Animal.class, Food.class);

Owner owner = engine.build(Owner.class);
```

