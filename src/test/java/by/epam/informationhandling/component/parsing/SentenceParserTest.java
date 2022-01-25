package by.epam.informationhandling.component.parsing;

import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceParserTest {

    private static final String SOURCE_SENTENCE = "It has survived - not only (five) centuries, but also the leap" +
            " into [13 2 +] electronic typesetting, remaining [3  5 +] essentially [15  3 /] unchanged.";

    private static final Composite EXPECTED_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("has"),
            Lexeme.word("survived"),
            Lexeme.word("-"),
            Lexeme.word("not"),
            Lexeme.word("only"),
            Lexeme.word("(five)"),
            Lexeme.word("centuries,"),
            Lexeme.word("but"),
            Lexeme.word("also"),
            Lexeme.word("the"),
            Lexeme.word("leap"),
            Lexeme.word("into"),
            Lexeme.expression("[13 2 +]"),
            Lexeme.word("electronic"),
            Lexeme.word("typesetting,"),
            Lexeme.word("remaining"),
            Lexeme.expression("[3  5 +]"),
            Lexeme.word("essentially"),
            Lexeme.expression("[15  3 /]"),
            Lexeme.word("unchanged.")));

    private final SentenceParser sentenceParser = new SentenceParser();

    @Test
    public void testParseSentenceShouldParseSentenceWhenSentenceContainExpressionsAndWords() {
        //given

        //when
        Composite actualComposite = sentenceParser.parse(SOURCE_SENTENCE);

        //then
        Assert.assertEquals(EXPECTED_COMPOSITE, actualComposite);
    }
}
