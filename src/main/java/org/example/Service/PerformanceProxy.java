package org.example.Service;

import org.example.Annotations.Metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

public class PerformanceProxy implements InvocationHandler {

    private final Object target;

    public PerformanceProxy(Object clazz){
        this.target = clazz;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!method.isAnnotationPresent(Metric.class)){
            return method.invoke(target, args);
        }

        long start = System.nanoTime();
        Object res = method.invoke(target, args);
        long finish = System.nanoTime();
        System.out.println("Method work time = " + (finish - start));
        return res;
    }

    public static <T> T createProxy(T target, Class<T> interType){
        return (T) Proxy.newProxyInstance(interType.getClassLoader(),
                new Class<?>[]{interType},
                new PerformanceProxy(target));
    }

}
