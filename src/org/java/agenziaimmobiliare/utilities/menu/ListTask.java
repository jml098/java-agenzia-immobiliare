package org.java.agenziaimmobiliare.utilities.menu;

@FunctionalInterface
public interface ListTask<T> {
    MenuOption run(T list);
}
