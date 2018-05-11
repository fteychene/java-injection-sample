package epsi.archi.injection.sample.animal.impl;

import epsi.archi.injection.sample.animal.Animal;

public class Cat implements Animal {

    @Override
    public String sayHello() {
        return "Miaou";
    }

    @Override
    public String type() {
        return "chat";
    }
}
