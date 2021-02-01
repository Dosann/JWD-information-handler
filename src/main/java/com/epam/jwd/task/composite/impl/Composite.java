package main.java.com.epam.jwd.task.composite.impl;

import main.java.com.epam.jwd.task.composite.Component;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private final List<Component> components = new ArrayList<>();

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public int getSizeOfComponents() {
        return components.size();
    }

    @Override
    public boolean add(Component component) {
        components.add(component);
        return true;
    }

    @Override
    public boolean remove(Component component) {
        components.remove(component);
        return true;
    }

    //todo: implement backToText in general for components
    @Override
    public String backToText() {
        return null;
    }
}
