package com.example.processcommunicate.factory.simplefactory;

public class FruitFactory {

    private static FruitFactory fruitFactory;

    enum Type {
        APPLE, PEAR;
    }

    static {
        fruitFactory = new FruitFactory();
    }

    private FruitFactory() {
    }

    public static FruitFactory getInstance() {
        return fruitFactory;
    }

    public Fruit create(Type type) {
        switch (type) {
            case APPLE:
                return new Apple();
            case PEAR:
                return new Pear();
            default:
                return null;
        }
    }
}
