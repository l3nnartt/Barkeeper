package com.github.l3nnartt;

public class CalculationHandler {

    public CalculationResult calculate(String string) {
        string = string.replaceAll("\\s","");;

        CalculationResult result = new CalculationResult();

        String[] operators = string.split("[0-9]+");
        String[] operands = string.split("[/*+-]");

        boolean valid = true;
        for (String operand : operands) {
            if (!onlyContainsNumbers(operand) && valid) {
                valid = false;
            }
        }

        if (valid) {
            int first = Integer.parseInt(operands[0]);
            for (int i = 0; i < operands.length; i++) {
                if (operators[i].equals("+")) {
                    first += Integer.parseInt(operands[i]);
                } else if (operators[i].equals("-")) {
                    first -= Integer.parseInt(operands[i]);
                } else if (operators[i].equals("*")) {
                    first *= Integer.parseInt(operands[i]);
                } else if (operators[i].equals("/")) {
                    first /= Integer.parseInt(operands[i]);
                }
            }
            result.setValue(first);
            return result;

        }
        result.setError(true);
        return result;
    }

    private boolean onlyContainsNumbers(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static class CalculationResult {
        private boolean error;
        private int value;

        public CalculationResult() {
            this.error = false;
            this.value = -1;
        }

        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }

        public boolean isError() {
            return error;
        }
        public void setError(boolean error) {
            this.error = error;
        }
    }

}