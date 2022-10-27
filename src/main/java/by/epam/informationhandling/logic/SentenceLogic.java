package by.epam.informationhandling.logic;

import by.epam.informationhandling.component.entity.Component;
import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import by.epam.informationhandling.logic.comparator.TargetCharacterCountComparator;

import java.util.ArrayList;
import java.util.List;

public class SentenceLogic {

    public Composite sortWordsByTargetCharacterCount(Composite sentence, char character) {
        List<Lexeme> lexemes = new ArrayList<>();
        for (Component component: sentence.getChildren()) {
            lexemes.add((Lexeme) component);
        }
        lexemes.sort(new TargetCharacterCountComparator(character));
        return new Composite(lexemes);
    }
}
