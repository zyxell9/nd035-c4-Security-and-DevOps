package com.example.demo;

import java.lang.reflect.Field;

public class Utils {
    public static void injectObjects(Object target, String fieldName, Object toInject) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            boolean wasAccessible = field.canAccess(target);
            field.setAccessible(true);
            field.set(target, toInject);
            field.setAccessible(wasAccessible);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}