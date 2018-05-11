package epsi.archi.injection.sample;

import epsi.archi.injection.engine.Engine;
import epsi.archi.injection.sample.animal.Animal;
import epsi.archi.injection.sample.animal.impl.Dog;
import epsi.archi.injection.sample.food.Food;
import epsi.archi.injection.sample.food.impl.Meat;

import static epsi.archi.injection.engine.Engine.DependencyMap;

public class Main {

    public static Owner buildOwner(DependencyMap dependencies) {
        return new Owner("Johnny", dependencies.get(Animal.class), dependencies.get(Food.class));
    }

    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.register(Animal.class, Dog::new);
        engine.register(Food.class, Meat::new);

        engine.register(Owner.class, Main::buildOwner, Animal.class, Food.class);

        Owner owner = engine.build(Owner.class);
        System.out.println(owner.wrapHello());
    }
}
