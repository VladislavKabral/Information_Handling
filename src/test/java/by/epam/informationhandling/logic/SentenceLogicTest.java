package by.epam.informationhandling.logic;

import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceLogicTest {

    private static final char CHARACTER = 'a';

    private static final Composite SOURCE_SENTENCE = new Composite(Arrays.asList(Lexeme.word("abacas"),
            Lexeme.word("calculator"),
            Lexeme.word("dog"),
            Lexeme.word("cat")));

    private static final Composite EXPECTED_SENTENCE = new Composite(Arrays.asList(Lexeme.word("dog"),
            Lexeme.word("cat"),
            Lexeme.word("calculator"),
            Lexeme.word("abacas")));

    private final SentenceLogic sentenceLogic = new SentenceLogic();

    @Test
    public void testSortWordsByTargetCharacterCountShouldSortWordsInSentencesAndReturnNewSentence() {
        //given

        //when
        Composite actualSentenceSortedByFirstCharacter = sentenceLogic.sortWordsByTargetCharacterCount(SOURCE_SENTENCE, CHARACTER);

        //then
        Assert.assertEquals(EXPECTED_SENTENCE, actualSentenceSortedByFirstCharacter);

    }
}
