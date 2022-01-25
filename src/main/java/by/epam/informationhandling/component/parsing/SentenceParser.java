package by.epam.informationhandling.component.parsing;

import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;

public class SentenceParser extends AbstractParser{

    private static final String LEXEME_DELIMITER = "(?:\\s(?=\\[)|(?<=\\])\\s)";
    private static final String WORD_DELIMITER = "\\s";

    public SentenceParser() {
        super();
    }

    @Override
    public Composite parse(String text) {
        Composite composite = new Composite();
        String[] lexemes = text.split(LEXEME_DELIMITER);

        for (String lexeme: lexemes) {
            if (lexeme.contains("[")) {
                composite.add(Lexeme.expression(lexeme));
            } else {
                wordsParser(lexeme, composite);
            }
        }
        return composite;
    }

    private void wordsParser(String words, Composite composite) {
        String[] splitWords = words.split(WORD_DELIMITER);
        for (String splitWord: splitWords) {
            composite.add(Lexeme.word(splitWord));
        }
    }
}
