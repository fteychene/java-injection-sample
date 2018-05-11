package epsi.archi.injection.sample.food.impl;

import epsi.archi.injection.sample.food.Food;

public class Croquette implements Food {
    @Override
    public String type() {
        return "des croquettes";
    }
}
