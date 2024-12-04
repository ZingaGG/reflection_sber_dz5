package org.example.Interface;

import org.example.Annotations.Metric;

public interface Calculator {
    /**
     * Расчет факториала числа.
     * @param number
     */
    @Metric
    int factorialCalc(int number);
}
