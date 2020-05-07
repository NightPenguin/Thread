package com.learn._02runnable._03StrategyPattern;

public class SimpleCalculatorStrategy implements CalculatorStrategy {

    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bouns) {
        return salary * SALARY_RATE + bouns * BONUS_RATE;
    }
}
