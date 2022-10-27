package by.epam.informationhandling.logic.expressioncalculator;

import by.epam.informationhandling.logic.expressioncalculator.exception.ExpressionCalculatorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExpressionCalculatorTest {

    private static final String PLUS_EXPRESSION = "[27 9 +]";
    private static final Integer EXPECTED_PLUS_EXPRESSION_RESULT = 36;

    private static final String MINUS_EXPRESSION = "[27 9 -]";
    private static final Integer EXPECTED_MINUS_EXPRESSION_RESULT = 18;

    private static final String DIVIDE_EXPRESSION = "[27 9 /]";
    private static final Integer EXPECTED_DIVIDE_EXPRESSION_RESULT = 3;

    private static final String MULTIPLY_EXPRESSION = "[27 9 *]";
    private static final Integer EXPECTED_MULTIPLY_EXPRESSION_RESULT = 243;

    private static final String EXPRESSION_WITH_PARAMETERS = "[x y +]";
    private static final Map<Character, Integer> PARAMETERS = new HashMap<>();
    static {
        PARAMETERS.put('x', 9);
        PARAMETERS.put('y', 1);
    }
    private static final Integer EXPECTED_EXPRESSION_WITH_PARAMETERS_RESULT = 10;

    private static final Map<Character, Integer> EMPTY_PARAMETERS = new HashMap<>();

    private final ExpressionCalculator calculator = new ExpressionCalculator();

    @Test
    public void testCalculateExpressionShouldCalculatePlusExpression() throws ExpressionCalculatorException {
        //given

        //when
        Integer actualPlusExpressionResult = calculator.calculate(PLUS_EXPRESSION, EMPTY_PARAMETERS);

        //then
        Assert.assertEquals(EXPECTED_PLUS_EXPRESSION_RESULT, actualPlusExpressionResult);

    }

    @Test
    public void testCalculateExpressionShouldCalculateMinusExpression() throws ExpressionCalculatorException {
        //given

        //when
        Integer actualMinusExpressionResult = calculator.calculate(MINUS_EXPRESSION, EMPTY_PARAMETERS);

        //then
        Assert.assertEquals(EXPECTED_MINUS_EXPRESSION_RESULT, actualMinusExpressionResult);

    }

    @Test
    public void testCalculateExpressionShouldCalculateMultiplyExpression() throws ExpressionCalculatorException {
        //given

        //when
        Integer actualMultiplyExpressionResult = calculator.calculate(MULTIPLY_EXPRESSION, EMPTY_PARAMETERS);

        //then
        Assert.assertEquals(EXPECTED_MULTIPLY_EXPRESSION_RESULT, actualMultiplyExpressionResult);

    }

    @Test
    public void testCalculateExpressionShouldCalculateDivideExpression() throws ExpressionCalculatorException {
        //given

        //when
        Integer actualDivideExpressionResult = calculator.calculate(DIVIDE_EXPRESSION, EMPTY_PARAMETERS);

        //then
        Assert.assertEquals(EXPECTED_DIVIDE_EXPRESSION_RESULT, actualDivideExpressionResult);

    }

    @Test
    public void testCalculateExpressionShouldCalculateExpressionWithParameters() throws ExpressionCalculatorException {
        //given

        //when
        Integer actualExpressionWithParametersResult = calculator.calculate(EXPRESSION_WITH_PARAMETERS, PARAMETERS);

        //then
        Assert.assertEquals(EXPECTED_EXPRESSION_WITH_PARAMETERS_RESULT, actualExpressionWithParametersResult);

    }
}
