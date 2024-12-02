package org.example.Impls;

import org.example.Interface.Calculator;

public class BigCalculatorImpl implements Calculator{
    @Override
    public int factorialCalc(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = result * i;
        }
        return result;
    }

    private void hiddenMethod(){
    }
}
