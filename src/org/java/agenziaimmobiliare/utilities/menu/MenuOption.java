package org.java.agenziaimmobiliare.utilities.menu;

public abstract class MenuOption {
    private String name;

    public MenuOption(String name) {
        this.name = name;
    }

    protected abstract boolean task();

    public String getName() {
        return name;
    }
}