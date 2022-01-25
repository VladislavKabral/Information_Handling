package by.epam.informationhandling.logic.comparator;

import by.epam.informationhandling.component.entity.Lexeme;

import java.util.Comparator;

public class TargetCharacterCountComparator implements Comparator<Lexeme> {

    private final char character;

    public TargetCharacterCountComparator(char character) {
        this.character = character;
    }

    private int calculateCharacterCount(String lexeme) {
        int count = 0;
        for (int i = 0; i < lexeme.length(); i++) {
            if (lexeme.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public int compare(Lexeme firstLexeme, Lexeme secondLexeme) {
        int differenceOfCountCharacter = calculateCharacterCount(firstLexeme.getValue()) - calculateCharacterCount(secondLexeme.getValue());
        if (differenceOfCountCharacter != 0) {
            return differenceOfCountCharacter;
        }
        return firstLexeme.getValue().compareToIgnoreCase(secondLexeme.getValue());
    }
}
