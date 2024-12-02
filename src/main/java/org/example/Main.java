package org.example;

import org.example.Impls.CalculatorImpl;
import org.example.Interface.Calculator;
import org.example.Service.ReflectionUtils;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();

        System.out.println("Вывод всех методов класса: ");
        ReflectionUtils.outDeclaredMethods(calculator.getClass());

        System.out.println("Вывод всех методов класса с наследованием: ");
        ReflectionUtils.outAllMethodsHierarchy(calculator.getClass());
    }
}