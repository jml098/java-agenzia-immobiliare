package org.java.agenziaimmobiliare.utilities.menu;

import org.java.agenziaimmobiliare.utilities.prompt.Prompt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    List<MenuOption> options = new ArrayList<>();
    private String message;

    public Menu(String mesasge, MenuOption... options) {
        this.options.add(new MenuOption("Exit") {
            @Override
            protected boolean task() {
                return true;
            }
        });
        this.options.addAll(Arrays.asList(options));
        this.message = mesasge;
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

            exit = options.get(Prompt.askInt("> ")).task();

        } while (!exit);
    }
}

