package com.example.compute;

public class CalculatorService {
    
    private Calculator calculator;
    
    public CalculatorService() {
        this.calculator = new Calculator();
    }
    
    public boolean isRunning() {
        return true;
    }
    
    public double compute(double numberA, double numberB) {
        return calculator.add(numberA, numberB);
    }
}