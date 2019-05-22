package com.example.processcommunicate.myeventbus;

import java.lang.reflect.InvocationTargetException;

public class Subscription {
    Object subscriber;
    SubscriberMethod subscriberMethod;

    public Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
    }

    public void invoke(Object event) {
        subscriberMethod.method.setAccessible(true);
        try {
            subscriberMethod.method.invoke(subscriber, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
