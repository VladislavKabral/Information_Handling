package by.epam.informationhandling.logic;

import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import by.epam.informationhandling.logic.expressioncalculator.ExpressionCalculator;
import by.epam.informationhandling.logic.expressioncalculator.exception.ExpressionCalculatorException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TextLogicTest {

    private static final String FIRST_EXPRESSION = "[ 9 1 + x / 2 + ]";
    private static final Integer FIRST_EXPRESSION_RESULT = 12;
    private static final String FIRST_EXPRESSION_RESULT_STRING = Integer.toString(FIRST_EXPRESSION_RESULT);
    private static final String SECOND_EXPRESSION = "[ 8 2 + y * ]";
    private static final Integer SECOND_EXPRESSION_RESULT = 20;
    private static final String SECOND_EXPRESSION_RESULT_STRING = Integer.toString(SECOND_EXPRESSION_RESULT);
    private static final Map<Character, Integer> PARAMETERS = new HashMap<>();

    static {
        PARAMETERS.put('x', 1);
        PARAMETERS.put('y', 2);
    }

    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("is"),
            Lexeme.word("so"),
            Lexeme.word("good.")));
    private static final Composite SECOND_SENTENCE_WITH_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.expression(FIRST_EXPRESSION),
            Lexeme.word("Wonderful!")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Never"),
            Lexeme.word("give"),
            Lexeme.word("up...")));
    private static final Composite FOURTH_SENTENCE_WITH_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("What`s"),
            Lexeme.expression(SECOND_EXPRESSION),
            Lexeme.word("up?")));
    private static final Composite FIRST_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(FIRST_SENTENCE_COMPOSITE));
    private static final Composite SECOND_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE = new Composite(Arrays.asList(SECOND_SENTENCE_WITH_EXPRESSION_COMPOSITE,
            THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE = new Composite(Collections.singletonList(FOURTH_SENTENCE_WITH_EXPRESSION_COMPOSITE));
    private static final Composite TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE,
            SECOND_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE,
            THIRD_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE));
    private static final String EXPECTED_TEXT_OF_COMPOSITE = "It is so good.\n[ 9 1 + x / 2 + ] Wonderful! Never give up...\nWhat`s [ 8 2 + y * ] up?";
    private static final Composite EXPECTED_TEXT_COMPOSITE_WITH_SORTED_PARAGRAPHS = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE,
            THIRD_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE,
            SECOND_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE));

    private static final Composite SECOND_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.word(FIRST_EXPRESSION_RESULT_STRING),
            Lexeme.word("Wonderful!")));
    private static final Composite FOURTH_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("What`s"),
            Lexeme.word(SECOND_EXPRESSION_RESULT_STRING),
            Lexeme.word("up?")));
    private static final Composite SECOND_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE = new Composite(Arrays.asList(SECOND_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE, THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE = new Composite(Collections.singletonList(FOURTH_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE));
    private static final Composite EXPECTED_CALCULATED_TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE, THIRD_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE));

    private final TextLogic textLogic = new TextLogic();

    @Test
    public void testParsedTextToStringShouldReturnAStringValueEqualToTheTextBeforeParsing() {
        //given

        //when
        String actualTextOfComposite = textLogic.parsedTextToString(TEXT_COMPOSITE);

        //then
        Assert.assertEquals(EXPECTED_TEXT_OF_COMPOSITE, actualTextOfComposite);

    }

    @Test
    public void testSortParagraphsBySentenceNumberShouldProperlySortMultipleParagraphs() {
        //given

        //when
        Composite actualTextWithSortedParagraphs = textLogic.sortParagraphs(TEXT_COMPOSITE);

        //then
        Assert.assertEquals(EXPECTED_TEXT_COMPOSITE_WITH_SORTED_PARAGRAPHS, actualTextWithSortedParagraphs);

    }

    @Test
    public void testCalculateExpressionsInTextShouldCalculateIfThereAreMultipleExpressionsInDifferentParagraphs() throws ExpressionCalculatorException {
        //given
        ExpressionCalculator expressionCalculatorMock = Mockito.mock(ExpressionCalculator.class);
        Mockito.when(expressionCalculatorMock.calculate(FIRST_EXPRESSION, PARAMETERS)).thenReturn(FIRST_EXPRESSION_RESULT);
        Mockito.when(expressionCalculatorMock.calculate(SECOND_EXPRESSION, PARAMETERS)).thenReturn(SECOND_EXPRESSION_RESULT);
        TextLogic localTextLogic = new TextLogic(expressionCalculatorMock);

        //when
        Composite actualCalculatedTextComposite = localTextLogic.calculateExpressionsInText(TEXT_COMPOSITE, PARAMETERS);

        //then
        Assert.assertEquals(EXPECTED_CALCULATED_TEXT_COMPOSITE, actualCalculatedTextComposite);

    }

}
