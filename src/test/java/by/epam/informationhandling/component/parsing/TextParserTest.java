package by.epam.informationhandling.component.parsing;

import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class TextParserTest {

    private static final String SOURCE_FIRST_PARAGRAPH = "Dad has come! You have come very fast.";
    private static final String SOURCE_SECOND_PARAGRAPH = "My car won`t start...";
    private static final String SOURCE_THIRD_PARAGRAPH = "I read the letter. Stood up. Sat down. Pondered for a minute." +
            " Then reread the letter again.";
    private static final String SOURCE_FOURTH_PARAGRAPH = "It is only with the heart that one can see rightly.";

    private static final String SOURCE_TEXT = SOURCE_FIRST_PARAGRAPH + "\n" + SOURCE_SECOND_PARAGRAPH + "\n" +
            SOURCE_THIRD_PARAGRAPH + "\n" + SOURCE_FOURTH_PARAGRAPH;

    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Dad"),
            Lexeme.word("has"),
            Lexeme.word("come!")));
    private static final Composite SECOND_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("You"),
            Lexeme.word("have"),
            Lexeme.word("come"),
            Lexeme.word("very"),
            Lexeme.word("fast.")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("My"),
            Lexeme.word("car"),
            Lexeme.word("won`t"),
            Lexeme.word("start...")));
    private static final Composite FOURTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I"),
            Lexeme.word("read"),
            Lexeme.word("the"),
            Lexeme.word("letter.")));
    private static final Composite FIFTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Stood"),
            Lexeme.word("up.")));
    private static final Composite SIXTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Sat"),
            Lexeme.word("down.")));
    private static final Composite SEVENTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Pondered"),
            Lexeme.word("for"),
            Lexeme.word("a"),
            Lexeme.word("minute.")));
    private static final Composite EIGHTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Then"),
            Lexeme.word("reread"),
            Lexeme.word("the"),
            Lexeme.word("letter"),
            Lexeme.word("again.")));
    private static final Composite NINTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("is"),
            Lexeme.word("only"),
            Lexeme.word("with"),
            Lexeme.word("the"),
            Lexeme.word("heart"),
            Lexeme.word("that"),
            Lexeme.word("one"),
            Lexeme.word("can"),
            Lexeme.word("see"),
            Lexeme.word("rightly.")));

    private static final Composite FIRST_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FIRST_SENTENCE_COMPOSITE,
            SECOND_SENTENCE_COMPOSITE));
    private static final Composite SECOND_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FOURTH_SENTENCE_COMPOSITE,
            FIFTH_SENTENCE_COMPOSITE, SIXTH_SENTENCE_COMPOSITE, SEVENTH_SENTENCE_COMPOSITE, EIGHTH_SENTENCE_COMPOSITE));
    private static final Composite FOURTH_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(NINTH_SENTENCE_COMPOSITE));

    private static final Composite EXPECTED_TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE,
            SECOND_PARAGRAPH_COMPOSITE, THIRD_PARAGRAPH_COMPOSITE, FOURTH_PARAGRAPH_COMPOSITE));

    @Test
    public void testParseTextShouldParseTextAndReturnCompositeWithWordsAndExpressions() {
        //given
        ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
        Mockito.when(paragraphParser.parse(SOURCE_FIRST_PARAGRAPH)).thenReturn(FIRST_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(SOURCE_SECOND_PARAGRAPH)).thenReturn(SECOND_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(SOURCE_THIRD_PARAGRAPH)).thenReturn(THIRD_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(SOURCE_FOURTH_PARAGRAPH)).thenReturn(FOURTH_PARAGRAPH_COMPOSITE);
        TextParser textParser = new TextParser(paragraphParser);

        //when
        Composite actualTextComposite = textParser.parse(SOURCE_TEXT);

        //then
        Assert.assertEquals(EXPECTED_TEXT_COMPOSITE, actualTextComposite);

    }
}
