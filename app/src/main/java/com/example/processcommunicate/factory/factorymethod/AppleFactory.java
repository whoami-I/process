package com.example.processcommunicate.factory.factorymethod;

public class AppleFactory implements FruitFactory {
    @Override
    public Fruit create() {
        return new Apple();
    }
}
