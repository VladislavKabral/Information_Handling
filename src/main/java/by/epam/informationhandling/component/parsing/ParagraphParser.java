package by.epam.informationhandling.component.parsing;

import by.epam.informationhandling.component.entity.Composite;

public class ParagraphParser extends AbstractParser{

    private static final String SENTENCE_DELIMITER = "(?<=[\\.\\?\\![\\.]{3}])\\s";
    
    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Composite parse(String text) {
        return templateParse(text, SENTENCE_DELIMITER);
    }
}
