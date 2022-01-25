package by.epam.informationhandling.component.parsing;

import by.epam.informationhandling.component.entity.Component;
import by.epam.informationhandling.component.entity.Composite;

public abstract class AbstractParser implements Parser{
    private final Parser successor;

    public AbstractParser(Parser successor) {
        this.successor = successor;
    }

    public AbstractParser() {
        successor = null;
    }

    public Parser getSuccessor() {
        return successor;
    }

    protected Composite templateParse(String text, String delimiter) {
        Composite composite = new Composite();
        String[] parts = text.split(delimiter);

        for (String part: parts) {
            Component component = getSuccessor().parse(part);
            composite.add(component);
        }

        return composite;
    }
}
