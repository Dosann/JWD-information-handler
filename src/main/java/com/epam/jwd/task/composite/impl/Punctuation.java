package main.java.com.epam.jwd.task.composite.impl;

import main.java.com.epam.jwd.task.composite.Component;

import java.util.Collections;
import java.util.List;

public class Punctuation implements Component {

    private final char value;

    public Punctuation(char value) {
        this.value = value;
    }

    @Override
    public int getSizeOfComponents() {
        return 0;
    }

    @Override
    public List<Component> getComponents() {
        return Collections.emptyList();
    }

    @Override
    public boolean add(Component component) {
        return false;
    }

    @Override
    public boolean remove(Component component) {
        return false;
    }

    @Override
    public String backToText() {
        return String.valueOf(value);
    }
}
