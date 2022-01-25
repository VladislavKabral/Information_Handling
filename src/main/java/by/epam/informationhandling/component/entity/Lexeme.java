package by.epam.informationhandling.component.entity;

import java.util.Objects;

public class Lexeme implements Component{

    private final String value;
    private final LexemeType lexemeType;

    public Lexeme(String value, LexemeType lexemeType) {
        this.value = value;
        this.lexemeType = lexemeType;
    }

    public static Lexeme word(String value) {
        return new Lexeme(value, LexemeType.WORD);
    }

    public static Lexeme expression(String value) {
        return new Lexeme(value, LexemeType.EXPRESSION);
    }

    public String getValue() {
        return value;
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Lexeme lexeme = (Lexeme) object;
        return Objects.equals(value, lexeme.value) && lexemeType == lexeme.lexemeType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode()) + ((lexemeType == null) ? 0 : lexemeType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Lexeme= {" +
                "value='" + value + '\'' +
                ", lexemeType=" + lexemeType +
                '}';
    }
}
