package epsi.archi.injection.sample.animal.impl;

import epsi.archi.injection.sample.animal.Animal;

public class Dog implements Animal {
    @Override
    public String sayHello() {
        return "Waf";
    }

    @Override
    public String type() {
        return "chien";
    }
}
