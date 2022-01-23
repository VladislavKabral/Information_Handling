package by.epam.informationhandling.component.parsing;

import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class ParagraphParserTest {

    private static final String FIRST_SENTENCE = "It is a good...";
    private static final String SECOND_SENTENCE = "Wonderful!";
    private static final String THIRD_SENTENCE = "Never give up.";
    private static final String FOURTH_SENTENCE = "I can do all things.";
    private static final String FIFTH_SENTENCE = "What`s up?";

    private static final String ORIGINAL_PARAGRAPH = FIRST_SENTENCE + " " + SECOND_SENTENCE + " " + THIRD_SENTENCE + " " +
            FOURTH_SENTENCE + " " + FIFTH_SENTENCE;

    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("is"),
            Lexeme.word("a"),
            Lexeme.word("good...")));
    private static final Composite SECOND_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Wonderful!")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Never"),
            Lexeme.word("give"),
            Lexeme.word("up.")));
    private static final Composite FOURTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I"),
            Lexeme.word("can"),
            Lexeme.word("do"),
            Lexeme.word("all"),
            Lexeme.word("things.")));
    private static final Composite FIFTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("What`s"),
            Lexeme.word("up?")));

    private static final Composite EXPECTED_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FIRST_SENTENCE_COMPOSITE,
            SECOND_SENTENCE_COMPOSITE,
            THIRD_SENTENCE_COMPOSITE,
            FOURTH_SENTENCE_COMPOSITE,
            FIFTH_SENTENCE_COMPOSITE));

    @Test
    public void testParseParagraphShouldParseParagraphAndReturnCompositeWithWordsAndExpressions() {
        //given
        SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);

        Mockito.when(sentenceParser.parse(FIRST_SENTENCE)).thenReturn(FIRST_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(SECOND_SENTENCE)).thenReturn(SECOND_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(THIRD_SENTENCE)).thenReturn(THIRD_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(FOURTH_SENTENCE)).thenReturn(FOURTH_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(FIFTH_SENTENCE)).thenReturn(FIFTH_SENTENCE_COMPOSITE);

        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

        //when
        Composite actualParagraphComposite = paragraphParser.parse(ORIGINAL_PARAGRAPH);

        //then
        Assert.assertEquals(EXPECTED_PARAGRAPH_COMPOSITE, actualParagraphComposite);
    }
}
