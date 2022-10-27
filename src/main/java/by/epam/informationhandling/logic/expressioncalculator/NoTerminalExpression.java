package by.epam.informationhandling.logic.expressioncalculator;

public class NoTerminalExpression implements AbstractExpression{

    private int number;

    public NoTerminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.push(number);
    }
}
