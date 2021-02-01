package main.java.com.epam.jwd.task.composite;

import java.util.List;

public interface Component {

    List<Component> getComponents();

    int getSizeOfComponents();

    boolean add(Component component);

    boolean remove(Component component);

    String backToText();

}
