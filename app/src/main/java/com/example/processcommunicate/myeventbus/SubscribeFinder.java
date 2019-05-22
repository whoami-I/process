package com.example.processcommunicate.myeventbus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SubscribeFinder {
    //这个Map是以注册类为key，@Subscribe注解方法list为value的map
    Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE = new ConcurrentHashMap<>();
    private static final int BRIDGE = 0x40;
    private static final int SYNTHETIC = 0x1000;
    private static final int MODIFIERS_IGNORE = Modifier.ABSTRACT | Modifier.STATIC | BRIDGE | SYNTHETIC;

    public List<SubscriberMethod> findSubscriberMethods(Class<?> clazz) {
        List<SubscriberMethod> subscriberMethods = METHOD_CACHE.get(clazz);
        if (subscriberMethods != null) {
            return subscriberMethods;
        }
        subscriberMethods = findMethod(clazz);
        if (subscriberMethods != null && !subscriberMethods.isEmpty()) {
            METHOD_CACHE.put(clazz, subscriberMethods);
            return subscriberMethods;
        }
        throw new RuntimeException(clazz.getName() + " have no method with annotation  @Subscribe");
    }

    private List<SubscriberMethod> findMethod(Class<?> clazz) {
        Class<?> c = clazz;
        List<SubscriberMethod> subscriberMethods = new ArrayList<SubscriberMethod>();
        while (c != null) {
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                Subscribe annotation = method.getAnnotation(Subscribe.class);
                if (annotation != null) {
                    //说明该方法已经被Subscribe注解修饰了
                    int modifiers = method.getModifiers();
                    if ((modifiers & Modifier.PUBLIC) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                        //该方法为public
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            //该方法被Subscribe注解，并且符合条件，解析
                            SubscriberMethod subscriberMethod = parseSubscribeMethod(method, annotation, parameterTypes[0]);
                            subscriberMethods.add(subscriberMethod);
                        } else {

                            throw new RuntimeException("@Subscribe method " + method.getName() + " must " +
                                    "have only one parameter,but has " + parameterTypes.length + " parameters");

                        }
                    } else {
                        throw new RuntimeException("@Subscribe method :" + method.getName() + " must be " +
                                "public and no-static no-abstract");
                    }
                }
            }
            c = c.getSuperclass();
        }
        return subscriberMethods;
    }

    private SubscriberMethod parseSubscribeMethod(Method method, Subscribe annotation, Class<?> parameterType) {
        // SubscriberMethod subscriberMethod = new SubscriberMethod();
        ThreadMode threadMode = annotation.threadMode();
        boolean sticky = annotation.sticky();
        int priority = annotation.priority();
        SubscriberMethod subscriberMethod = new SubscriberMethod(method, threadMode,
                sticky, priority, parameterType);
        return subscriberMethod;

    }
}
