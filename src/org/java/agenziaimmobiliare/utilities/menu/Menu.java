package org.java.agenziaimmobiliare.utilities.menu;

import org.java.agenziaimmobiliare.utilities.prompt.Prompt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    List<MenuOption> options = new ArrayList<>();
    private final String message;

    public static <T> Menu fromList(List<T> list, String message, ListTask<T> listTask) {
        MenuOption[] options = new MenuOption[list.size()];
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            options[i] = listTask.run(item);
        }
        return new Menu(message, options);
    }

    public Menu(String message, MenuOption... options) {
        this.options.add(new MenuOption("Exit").setGoBack(true));
        this.options.addAll(Arrays.asList(options));
        this.message = message;

        this.start();
    }

    public void start() {
        boolean exit;
        do {
            System.out.println(message);
            for (int i = 1; i < options.size(); i++) {
                MenuOption option = options.get(i);

                System.out.println(i + " - " + option.getName());
            }
            System.out.println("0 - Exit");

            MenuOption option = options.get(Prompt.askInt("> "));

            option.getTask().run();
            exit = option.getGoBack();

        } while (!exit);
    }
}

