package epsi.archi.injection.sample;

import epsi.archi.injection.sample.animal.Animal;
import epsi.archi.injection.sample.food.Food;

public class Owner {

    private String name;
    private Animal animal;
    private Food food;

    public Owner(String aName, Animal aAnimal, Food aFood) {
        name = aName;
        animal = aAnimal;
        food = aFood;
    }

    public String wrapHello() {
        return name + " a un "+animal.type()+" qui mange "+food.type();
    }
}
