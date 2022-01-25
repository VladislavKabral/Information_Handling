package by.epam.informationhandling.logic.expressioncalculator;

import java.util.ArrayDeque;

public class Context {

    private final ArrayDeque<Integer> stack = new ArrayDeque<>();

    public void push(Integer value) {
        stack.push(value);
    }

    public Integer pop() {
        return stack.pop();
    }
}
