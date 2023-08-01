package org.java.agenziaimmobiliare.utilities.menu;

public class MenuOption {
    private final String name;
    protected Task task = () -> {};
    protected boolean goBack = false;


    public MenuOption setGoBack(boolean goBack) {
        this.goBack = goBack;
        return this;
    }

    public boolean getGoBack() {
        return goBack;
    }

    public MenuOption(String name) {
        this.name = name;
    }

    public Task getTask() {
        return task;
    }

    public MenuOption setTask(Task task) {
        this.task = task;
        return this;
    }

    public MenuOption setTask(Task task, boolean goBack) {
        this.task = task;
        this.goBack = goBack;
        return this;
    }

    public String getName() {
        return name;
    }
}