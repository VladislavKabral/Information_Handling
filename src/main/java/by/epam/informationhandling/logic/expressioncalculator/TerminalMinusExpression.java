package by.epam.informationhandling.logic.expressioncalculator;

public class TerminalMinusExpression implements AbstractExpression{

    @Override
    public void interpret(Context context) {
        Integer firstNumber = context.pop();
        Integer secondNumber = context.pop();
        Integer result = secondNumber - firstNumber;

        context.push(result);
    }
}
