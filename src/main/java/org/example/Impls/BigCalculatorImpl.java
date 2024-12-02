package org.example.Impls;

import lombok.Getter;
import org.example.Interface.Calculator;

@Getter
public class BigCalculatorImpl implements Calculator{

    public static final String SUN = "SUN";

    public static final String MOON = "MOON";

    private int a;
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
