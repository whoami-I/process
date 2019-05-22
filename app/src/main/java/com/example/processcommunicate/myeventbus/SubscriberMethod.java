package com.example.processcommunicate.myeventbus;

import java.lang.reflect.Method;

public class SubscriberMethod {
    ThreadMode threadMode;
    boolean sticky;
    int priority;
    Class<?> eventType;
    Method method;

    public SubscriberMethod(Method method, ThreadMode threadMode, boolean sticky,
                            int priority, Class<?> eventType) {
        this.method = method;
        this.threadMode = threadMode;
        this.sticky = sticky;
        this.priority = priority;
        this.eventType = eventType;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("method :");
        sb.append(method.getName());
        sb.append(" sticky: ");
        sb.append(sticky);
        sb.append(" priority: ");
        sb.append(priority);
        sb.append(" eventType: ");
        sb.append(eventType.getSimpleName());
        return sb.toString();
    }
}
