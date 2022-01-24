package by.epam.informationhandling.logic.expressioncalculator;

import by.epam.informationhandling.logic.expressioncalculator.exception.ExpressionCalculatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

    private static final String DELIMITER = " ";

    private static final Logger LOGGER = LogManager.getLogger(ExpressionCalculator.class);

    private List<AbstractExpression> expressionList = new ArrayList<>();

    public Integer calculate(String expression, Map<Character, Integer> parameters) throws ExpressionCalculatorException {
        LOGGER.info("Started calculating expression: " + expression + ", with parameters + " + parameters.toString());

        //expressionList.clear();
        expression = expression.replaceAll("[\\[\\]]", "");
        
        for (String lexeme: expression.split(DELIMITER)) {
            //if (lexeme.isEmpty()) {
              //  continue;
            //}
            switch (lexeme.charAt(0)) {
                case '/':
                    expressionList.add(new TerminalDivideExpression());
                    break;

                case '-':
                    if (lexeme.length() == 1) {
                        expressionList.add(new TerminalMinusExpression());
                    } else {
                        addNumberToExpression(lexeme, parameters);
                    }
                    break;

                case '*':
                    expressionList.add(new TerminalMultiplyExpression());
                    break;

                case '+':
                    if (lexeme.length() == 1) {
                        expressionList.add(new TerminalPlusExpression());
                    } else {
                        addNumberToExpression(lexeme, parameters);
                    }
                    break;

                default:
                    addNumberToExpression(lexeme, parameters);
            }
        }

        Integer result = finalizeCalculation();
        LOGGER.info("Result of calculation of expression: " + expression + "= " + result);
        return result;
    }

    private Integer finalizeCalculation() {
        Context context = new Context();

        for (AbstractExpression expression: expressionList) {
            expression.interpret(context);
        }

        return context.pop();
    }

    private void addNumberToExpression(String lexeme, Map<Character, Integer> parameters) throws ExpressionCalculatorException {
        Scanner scanner = new Scanner(lexeme);
        if (scanner.hasNextDouble()) {
            expressionList.add(new NoTerminalExpression(scanner.nextInt()));
        } else {
            Integer value = parameters.get(lexeme.charAt(0));
            if (value != null) {
                expressionList.add(new NoTerminalExpression(value));
            } else {
                LOGGER.error("Given parameter is unknown value");
            }
        }
    }
}
