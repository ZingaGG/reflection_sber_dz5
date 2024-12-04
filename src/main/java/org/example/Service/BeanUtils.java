package org.example.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Class<?> fromClass = from.getClass();
        Class<?> toClass = to.getClass();

        List<Method> getters = ReflectionUtils.getAllGetters(fromClass);
        List<Method> setters = ReflectionUtils.getAllSetters(toClass);

        Map<String, Method> setterMap = new HashMap<>();
        for (Method setter : setters) {
            String propertyName = setter.getName().substring(3).toLowerCase();
            setterMap.put(propertyName, setter);
        }

        for (Method getter : getters) {
            try {
                String propertyName = getter.getName().substring(3).toLowerCase();
                Method setter = setterMap.get(propertyName);

                if (setter != null && isCompatible(getter, setter)) {
                    Object value = getter.invoke(from);
                    setter.invoke(to, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to copy property: " + getter.getName(), e);
            }
        }
    }

    private static boolean isCompatible(Method getter, Method setter) {
        Class<?> getterType = getter.getReturnType();
        Class<?> setterType = setter.getParameterTypes()[0];
        return setterType.isAssignableFrom(getterType);
    }
}
