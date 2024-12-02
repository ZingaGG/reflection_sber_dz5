package org.example;

import org.example.Impls.BigCalculatorImpl;
import org.example.Impls.CalculatorImpl;
import org.example.Interface.Calculator;
import org.example.Service.ReflectionUtils;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Calculator calculator = new CalculatorImpl();
        Calculator bigCalculator = new BigCalculatorImpl();


        System.out.println("\nВывод всех методов класса: ");
        ReflectionUtils.outDeclaredMethods(calculator.getClass());

        System.out.println("\nВывод всех методов класса с наследованием: ");
        ReflectionUtils.outAllMethodsHierarchy(calculator.getClass());

        System.out.println("\nВывод всех геттеров класса: ");
        ReflectionUtils.getAllGetters(calculator.getClass());

        System.out.println("\nВывод всех геттеров класса (тест с Lombok): ");
        ReflectionUtils.getAllGetters(bigCalculator.getClass());

        System.out.println("\nПроверить что все String константы имеют значение = их имени: ");
        System.out.println(ReflectionUtils.stringFieldsCheck(bigCalculator.getClass()));

        System.out.println("\nПроверить что все String константы имеют значение = их имени: ");
        System.out.println(ReflectionUtils.stringFieldsCheck(calculator.getClass()));
    }
}