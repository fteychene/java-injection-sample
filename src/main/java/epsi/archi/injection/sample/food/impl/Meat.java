package epsi.archi.injection.sample.food.impl;

import epsi.archi.injection.sample.food.Food;

public class Meat implements Food {
    @Override
    public String type() {
        return "de la viande";
    }
}
