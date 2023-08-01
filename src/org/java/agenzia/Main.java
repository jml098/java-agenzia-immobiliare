package org.java.agenzia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Prompt.setLocale(Locale.US);
        AgenziaImmobiliare agenzia = new AgenziaImmobiliare();


        new Menu("Benvenuto all'agenzia immobiliare GenerationItaly!",
                new MenuOption("Aggiungi Immobile") {
                    boolean task() {

                        new Menu("Scegli tipo di immobile",
                                new MenuOption("Box") {
                                    @Override
                                    boolean task() {
                                        try {
                                            agenzia.aggiungiImmobile(
                                                    new Box(
                                                            Prompt.ask("Inserisci codice: "),
                                                            Prompt.ask("Inserisci indirizzo: "),
                                                            Prompt.ask("Inserisci CAP: "),
                                                            Prompt.ask("Inserisci Città: "),
                                                            Prompt.askInt("Inserisci metri quadri: "),
                                                            Prompt.askInt("Inserisci posti auto: ")
                                                    ));
                                        } catch (ConflittoCodiceImmobileException | IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println("Immobile aggiunto");

                                        return true;
                                    }
                                },
                                new MenuOption("Abitazione") {
                                    @Override
                                    boolean task() {
                                        try {
                                            agenzia.aggiungiImmobile(
                                                    new Abitazione(
                                                            Prompt.ask("Inserisci codice: "),
                                                            Prompt.ask("Inserisci indirizzo: "),
                                                            Prompt.ask("Inserisci CAP: "),
                                                            Prompt.ask("Inserisci Città: "),
                                                            Prompt.askInt("Inserisci metri quadri: "),
                                                            Prompt.askInt("Inserisci numero vani: ")
                                                    ));
                                        } catch (ConflittoCodiceImmobileException | IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println("Immobile aggiunto");

                                        return true;
                                    }
                                },
                                new MenuOption("Villa") {
                                    @Override
                                    boolean task() {
                                        try {
                                            agenzia.aggiungiImmobile(
                                                    new Villa(
                                                            Prompt.ask("Inserisci codice: "),
                                                            Prompt.ask("Inserisci indirizzo: "),
                                                            Prompt.ask("Inserisci CAP: "),
                                                            Prompt.ask("Inserisci Città: "),
                                                            Prompt.askInt("Inserisci metri quadri: "),
                                                            Prompt.askInt("Inserisci numero vani: "),
                                                            Prompt.askInt("Inserisci metri quadri giardino: ")
                                                    ));

                                            System.out.println("Immobile aggiunto");
                                        } catch (ConflittoCodiceImmobileException | IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }

                                        return true;
                                    }
                                }

                        ).start();

                        return false;
                    }
                },
                new MenuOption("Cerca Immobile") {
                    boolean task() {
                        new Menu("Scegli tipologia di ricerca",
                                new MenuOption("Per codice") {
                                    @Override
                                    boolean task() {
                                        try {
                                            Immobile immobile = agenzia.getImmobilePerCodice(
                                                    Prompt.ask("Inserisci il codice: ")
                                            );
                                            System.out.println(immobile);

                                            if (Prompt.ask(
                                                    "Vuoi aggiungere interesse a questo immobile?")
                                                    .equalsIgnoreCase("si")
                                            ) immobile.aggiungiInteresse();

                                        } catch (ImmobileNonTrovatoException e) {
                                            System.out.println(e.getMessage());
                                        }


                                        return true;
                                    }
                                },
                                new MenuOption("Per città") {
                                    @Override
                                    boolean task() {
                                        try {
                                            List<Immobile> immobili = agenzia.getImmobiliPerCitta(
                                                    Prompt.ask("Inserisci città: ")
                                            );
                                            System.out.println(immobili);

                                        } catch (ImmobileNonTrovatoException e) {
                                            System.out.println(e.getMessage());
                                        }

                                        return true;
                                    }
                                }
                        ).start();

                        return false;
                    }
                },
                new MenuOption("Mostra Immobili più richiesti") {
                    boolean task() {
                        try {
                            System.out.println(agenzia.getImmobiliConPiuInteresse());
                        } catch (ListaImmobiliVuotaException e) {
                            System.out.println(e.getMessage());
                        }

                        return false;
                    }
                },
                new MenuOption("Mostra Immobili in ordine di interesse") {
                    boolean task() {
                        try {
                            System.out.println(agenzia.getImmobiliInOrdineDiInteresse());
                        } catch (ListaImmobiliVuotaException e) {
                            System.out.println(e.getMessage());
                        }

                        return false;
                    }
                }
        ).start();
    }

    public static class Menu {
        List<MenuOption> options = new ArrayList<>();
        private String message;

        public Menu(String mesasge, MenuOption... options) {
            this.options.add(new MenuOption("Exit") {
                @Override
                boolean task() {
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

    public static abstract class MenuOption {
        private String name;

        public MenuOption(String name) {
            this.name = name;
        }

        abstract boolean task();

        public String getName() {
            return name;
        }
    }
}
