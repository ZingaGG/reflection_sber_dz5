package org.example.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

    public static void getAllGetters(Class<?> clazz){
        for (Method method : clazz.getDeclaredMethods()) {
            if(isGetter(method, clazz.getDeclaredFields())){
                System.out.println(method);
            }
        }
    }

    // Я понимаю, что метод(геттер) могут назвать по другому, я надеюсь, что класс откуда мы достаем геттеры,
    // написан по правилам программирования и оформления кода
    private static boolean isGetter(Method method, Field[] fields){
        for (Field field : fields) {
            if(method.getName().startsWith("get") &&
                    method.getName().toLowerCase().contains(field.getName().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    // Проверить что все String константы имеют значение = их имени
    public static boolean stringFieldsCheck(Class<?> clazz) throws IllegalAccessException {
        for (Field field : clazz.getFields()) {
            if(!stringFieldCheck(field)) {
                return false;
            }
        }
        return true;
    }

    private static boolean stringFieldCheck(Field field) throws IllegalAccessException {
        return Modifier.isFinal(field.getModifiers()) &&
                Modifier.isStatic(field.getModifiers()) &&
                field.getName().equalsIgnoreCase(field.get(String.class).toString());
    }

}
