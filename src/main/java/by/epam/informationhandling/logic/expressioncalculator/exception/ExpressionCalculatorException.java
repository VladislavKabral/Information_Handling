package by.epam.informationhandling.logic.expressioncalculator.exception;

public class ExpressionCalculatorException extends Exception{

    public ExpressionCalculatorException(String message, Exception exception) {
        super(message, exception);
    }

    public ExpressionCalculatorException(String message) {
        super(message);
    }
}
