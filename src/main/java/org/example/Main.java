package org.example;

import org.example.Impls.BigCalculatorImpl;
import org.example.Impls.CalculatorImpl;
import org.example.Interface.Calculator;
import org.example.Model.Admin;
import org.example.Model.User;
import org.example.Proxy.CachedInvocationHandler;
import org.example.Service.BeanUtils;
import org.example.Service.PerformanceProxy;
import org.example.Service.ReflectionUtils;

import java.lang.reflect.Proxy;
import java.sql.Ref;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Calculator delegate = new CalculatorImpl();
        Calculator bigCalculator = new BigCalculatorImpl();

        Calculator calculator = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
                new Class<?>[]{Calculator.class}, // I put the exact interface in this line, cuz "delegate" does not implement "Calculator" directly
                new CachedInvocationHandler(delegate));

        System.out.println("\nВывод всех методов класса: ");
        ReflectionUtils.outDeclaredMethods(delegate.getClass());

        System.out.println("\nВывод всех методов класса с наследованием: ");
        ReflectionUtils.outAllMethodsHierarchy(delegate.getClass());

        System.out.println("\nВывод всех геттеров класса: ");
        ReflectionUtils.getAllGetters(delegate.getClass());

        System.out.println("\nВывод всех геттеров класса (тест с Lombok): ");
        ReflectionUtils.getAllGetters(bigCalculator.getClass());

        System.out.println("\nПроверить что все String константы имеют значение = их имени: ");
        System.out.println(ReflectionUtils.stringFieldsCheck(bigCalculator.getClass()));

        System.out.println("\nПроверить что все String константы имеют значение = их имени: ");
        System.out.println(ReflectionUtils.stringFieldsCheck(delegate.getClass()));

        // Part of task using Proxy
        System.out.println("----------------------------------------");
        System.out.println("Starting part with Caching Proxy!");
        System.out.println("----------------------------------------");

        System.out.println(calculator.factorialCalc(1));
        System.out.println(calculator.factorialCalc(5));
        System.out.println(calculator.factorialCalc(6));
        System.out.println(calculator.factorialCalc(1));
        System.out.println(calculator.factorialCalc(1));
        System.out.println(calculator.factorialCalc(1));
        System.out.println(calculator.factorialCalc(1));
        System.out.println(calculator.factorialCalc(1));
        System.out.println(calculator.factorialCalc(1));

        // Performance Proxy

        System.out.println("----------------------------------------");
        System.out.println("Starting part with Performing Proxy!");
        System.out.println("----------------------------------------");

        Calculator perfCalculator = PerformanceProxy.createProxy(delegate, Calculator.class);
        System.out.println("\n" + perfCalculator.factorialCalc(3));

        System.out.println("----------------------------------------");
        System.out.println("Starting part with BeanUtils!");
        System.out.println("----------------------------------------");

        User user = new User(25, "Ivanov");
        Admin admin = new Admin(10, "Ivan");

        BeanUtils.assign(admin, user);

        System.out.println(admin); // Change the correspondent setter for field "age", from '10' -> '25'
    }
}