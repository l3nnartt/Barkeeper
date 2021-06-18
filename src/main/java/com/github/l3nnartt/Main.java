package com.github.l3nnartt;

public class Main {
    public static void main(String[] args) {
        CalculationHandler calculationHandler = new CalculationHandler();
        CalculationHandler.CalculationResult result = calculationHandler.calculate("100_+50-10+100*2/2");
        if (result.isError()) {
            System.out.println("Fehler");
        } else {
            System.out.println(result.getValue());
        }
    }
}