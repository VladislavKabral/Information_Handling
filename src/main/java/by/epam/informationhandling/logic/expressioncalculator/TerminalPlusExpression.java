package by.epam.informationhandling.logic.expressioncalculator;

public class TerminalPlusExpression implements AbstractExpression{

    @Override
    public void interpret(Context context) {
        Integer firstNumber = context.pop();
        Integer secondNumber = context.pop();
        Integer result = firstNumber + secondNumber;

        context.push(result);
    }
}
