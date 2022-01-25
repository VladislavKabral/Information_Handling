package by.epam.informationhandling.component.parsing;

public class TextParserBuilder {

    public Parser build() {
        return new TextParser(new ParagraphParser(new SentenceParser()));
    }
}
