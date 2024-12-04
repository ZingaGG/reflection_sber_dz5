package org.example.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {
    private final Map<Object, Object> resByArgs = new HashMap<>();
    private final Object delegate;

    public CachedInvocationHandler(Object o){
        this.delegate = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!resByArgs.containsKey(key(method, args))){
            System.out.println("Delegation of " + method.getName());
            Object invoke = invoke(method, args);
            resByArgs.put(key(method, args), invoke);
        } else {
            System.out.println("Method result has been found in cache");
        }
        return resByArgs.get(key(method, args));

    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try{
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }
}
