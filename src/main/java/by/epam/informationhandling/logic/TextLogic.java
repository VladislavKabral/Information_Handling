package by.epam.informationhandling.logic;

import by.epam.informationhandling.component.entity.Component;
import by.epam.informationhandling.component.entity.Composite;
import by.epam.informationhandling.component.entity.Lexeme;
import by.epam.informationhandling.component.entity.LexemeType;
import by.epam.informationhandling.component.parsing.Parser;
import by.epam.informationhandling.component.parsing.TextParserBuilder;
import by.epam.informationhandling.logic.comparator.SentenceCountComparator;
import by.epam.informationhandling.logic.expressioncalculator.ExpressionCalculator;
import by.epam.informationhandling.logic.expressioncalculator.exception.ExpressionCalculatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextLogic {

    private static final String PARAGRAPHS_DELIMITER = "\n";
    private static final String LEXEMES_AND_SENTENCES_DELIMITER = " ";

    private static final Logger LOGGER = LogManager.getLogger(TextLogic.class);

    private ExpressionCalculator expressionCalculator = new ExpressionCalculator();

    public TextLogic(){

    }

    public TextLogic(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

    public Composite parse(String text) {
        TextParserBuilder builder = new TextParserBuilder();
        Parser parser = builder.build();
        return parser.parse(text);
    }

    public Composite sortParagraphs(Composite text) {
        List<Composite> paragraphs = new ArrayList<>();
        for (Component paragraph: text.getChildren()) {
            paragraphs.add((Composite) paragraph);
        }
        paragraphs.sort(new SentenceCountComparator());
        return new Composite(paragraphs);
    }

    private Component calculateExpressionsInComponent(Component component, Map<Character, Integer> parameters) throws ExpressionCalculatorException {
        if (component.getClass() == Composite.class) {
            Composite composite = (Composite) component;
            List<Component> calculatedComponents = new ArrayList<>();
            List<Component> components = composite.getChildren();
            for (Component componentIterator : components) {
                Component calculatedComponentIterator = calculateExpressionsInComponent(componentIterator, parameters);
                calculatedComponents.add(calculatedComponentIterator);
            }
            return new Composite(calculatedComponents);
        }
        if (component.getClass() == Lexeme.class) {
            Lexeme lexeme = (Lexeme) component;
            String lexemeValue = lexeme.getValue();
            String calculatedValue;
            if (lexeme.getLexemeType() == LexemeType.EXPRESSION) {
                calculatedValue = Integer.toString(expressionCalculator.calculate(lexemeValue, parameters));
            } else {
                calculatedValue = lexemeValue;
            }
            return Lexeme.word(calculatedValue);
        }
        LOGGER.error("Unsupported Component");
        return null;
    }

    public Composite calculateExpressionsInText(Composite text, Map<Character, Integer> parameters) throws ExpressionCalculatorException {
        List<Component> calculatedTextComponents = new ArrayList<>();
        List<Component> textComponents = text.getChildren();
        for (Component component : textComponents) {
            Component calculatedComponent;
            try {
                calculatedComponent = calculateExpressionsInComponent(component, parameters);
            } catch (ExpressionCalculatorException e) {
                throw new ExpressionCalculatorException(e.getMessage(), e);
            }
            calculatedTextComponents.add(calculatedComponent);
        }
        return new Composite(calculatedTextComponents);
    }

    public String parsedTextToString(Composite text) {
        return createStringFromComponent(text, PARAGRAPHS_DELIMITER);
    }

    private String createStringFromComponent(Component text, String componentDelimiter) {
        StringBuilder resultString = new StringBuilder();
        if (text.getClass() == Composite.class) {
            Composite textAsComposite = (Composite) text;
            List<Component> textAsCompositeComponents = textAsComposite.getChildren();
            for (Component component : textAsCompositeComponents) {
                resultString.append(createStringFromComponent(component, LEXEMES_AND_SENTENCES_DELIMITER));
                if (textAsCompositeComponents.indexOf(component) != (textAsCompositeComponents.size() - 1)) {
                    resultString.append(componentDelimiter);
                }
            }
            return resultString.toString();
        }
        if (text.getClass() == Lexeme.class) {
            Lexeme textAsLexeme = (Lexeme) text;
            String lexemeText = textAsLexeme.getValue();
            resultString.append(lexemeText);
            return resultString.toString();
        }

        LOGGER.error("Unsupported Component");
        return null;
    }
}
