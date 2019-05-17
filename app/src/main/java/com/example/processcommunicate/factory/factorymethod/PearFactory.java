package com.example.processcommunicate.factory.factorymethod;

public class PearFactory implements FruitFactory {
    @Override
    public Fruit create() {
        return new Pear();
    }
}
