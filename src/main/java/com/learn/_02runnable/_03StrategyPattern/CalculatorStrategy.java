package com.learn._02runnable._03StrategyPattern;

@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary,double bonus);
}
