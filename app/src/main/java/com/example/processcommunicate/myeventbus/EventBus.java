package com.example.processcommunicate.myeventbus;


import com.example.processcommunicate.log.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private static final String TAG = "EventBus";

    private SubscribeFinder subscribeFinder;
    //根据事件的类型区分subscription,CopyOnWriteArrayList可以在循环的时候，删除其中的元素
    private Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionByEventType = new HashMap<>();
    private Map<Object, List<Class<?>>> typesBySubscriber = new HashMap<>();

    private EventBus() {
        subscribeFinder = new SubscribeFinder();
    }

    private static EventBus defaultInstance;

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    void register(Object o) {
        Class<?> clazz = o.getClass();
        //根据当前类，找出所有subscribe注解的方法，然后根据不同
        //参数类型，放到不同的位置
        List<SubscriberMethod> subscriberMethods = subscribeFinder.findSubscriberMethods(clazz);
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            Log.i(TAG, subscriberMethod.toString());
            Class<?> eventType = subscriberMethod.eventType;
            Subscription subscription = new Subscription(o, subscriberMethod);
            CopyOnWriteArrayList<Subscription> subscriptions = subscriptionByEventType.get(eventType);
            if (subscriptions == null) {
                subscriptions = new CopyOnWriteArrayList<>();
                subscriptionByEventType.put(eventType, subscriptions);
            }

            subscriptions.add(subscription);

            List<Class<?>> eventTypes = typesBySubscriber.get(o);
            if (eventTypes == null) {
                eventTypes = new ArrayList<>();
                typesBySubscriber.put(o, eventTypes);

            }
            if (!eventTypes.contains(eventType)) {
                eventTypes.add(eventType);
            }

        }
    }

    void unRegister(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        List<Class<?>> eventTypes = typesBySubscriber.get(subscriber);
        for (Class<?> eventType : eventTypes) {
            unSubscribe(subscriber, eventType);
        }
    }

    private void unSubscribe(Object subscriber, Class<?> eventType) {
        List<Subscription> subscriptions = subscriptionByEventType.get(eventType);
        if (subscriptions != null) {

            for (Subscription subscription : subscriptions) {
                if (subscription.subscriber == subscriber) {
                    //移除这个subscription
                    subscriptions.remove(subscription);
                }
            }
        }
    }


    public void post(Object event) {
        Class<?> clazz = event.getClass();
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionByEventType.get(clazz);
        for (Subscription subscription : subscriptions) {
            subscription.invoke(event);
        }

    }
}
