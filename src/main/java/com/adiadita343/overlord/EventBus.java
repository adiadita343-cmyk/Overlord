package com.adiadita343.overlord.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventBus {
    public static final EventBus INSTANCE = new EventBus();
    private final List<Object> subscribers = new ArrayList<>();

    // Înregistrează modulul când îi dai ENABLE ca să primească tick-uri active
    public void subscribe(Object object) {
        if (!subscribers.contains(object)) {
            subscribers.add(object);
        }
    }

    // Scoate modulul când îi dai DISABLE ca să nu mai ruleze codul în fundal
    public void unsubscribe(Object object) {
        subscribers.remove(object);
    }

    // Trimite evenimentul curent către toate modulele pornite
    public void post(Object event) {
        List<Object> targets = new ArrayList<>(subscribers);
        for (Object target : targets) {
            for (Method method : target.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(EventHandler.class)) {
                    try {
                        if (method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                            method.setAccessible(true);
                            method.invoke(target, event);
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
    }
}
