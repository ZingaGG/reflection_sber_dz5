package org.example.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Method> getAllGetters(Class<?> clazz){
        List<Method> res = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if(isGetter(method, clazz.getDeclaredFields())){
                System.out.println(method);
                res.add(method);
            }
        }

        return res;
    }

    public static List<Method> getAllSetters(Class<?> clazz){
        List<Method> res = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if(isSetter(method, clazz.getDeclaredFields())){
                System.out.println(method);
                res.add(method);
            }
        }

        return res;
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

    // Я понимаю, что метод(сеттер) могут назвать по другому, я надеюсь, что класс откуда мы достаем геттеры,
    // написан по правилам программирования и оформления кода
    private static boolean isSetter(Method method, Field[] fields){
        for (Field field : fields) {
            if(method.getName().startsWith("set") &&
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
