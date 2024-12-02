package org.example.Service;

import java.lang.reflect.Method;

public class ReflectionUtils {

    public static void outDeclaredMethods(Class<?> clazz){
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
    }

    public static void outAllMethodsHierarchy(Class<?> clazz) {
        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(method);
            }
            clazz = clazz.getSuperclass();
        }
    }

}
