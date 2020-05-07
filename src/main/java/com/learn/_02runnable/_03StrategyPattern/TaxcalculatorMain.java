package com.learn._02runnable._03StrategyPattern;

public class TaxcalculatorMain {

    public static void main(String[] args) {
        /*TaxCalculator calculator = new TaxCalculator(10000d,2000d){
            @Override
            public double calcTax(){
                return getSalary()*0.1 + getBonus()*0.15;
            }
        };
        double tax = calculator.calculate();
        System.out.println(tax);*/

        TaxCalculator calculator = new TaxCalculator(10000d, 2000d);
        CalculatorStrategy strategy = new SimpleCalculatorStrategy();
        calculator.setCalculatorStrategy(strategy);
        System.out.println(calculator.calculate());

        TaxCalculator calculator1 = new TaxCalculator(10000d, 2000d, (s, b) -> s * 0.2 + b * 0.15);
        System.out.println(calculator1.calculate());
    }
}
