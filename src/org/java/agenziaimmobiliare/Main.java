package org.java.agenziaimmobiliare;

import org.java.agenziaimmobiliare.agenzia.AgenziaImmobiliare;
import org.java.agenziaimmobiliare.exceptions.ConflittoCodiceImmobileException;
import org.java.agenziaimmobiliare.exceptions.ImmobileNonTrovatoException;
import org.java.agenziaimmobiliare.exceptions.ListaImmobiliVuotaException;
import org.java.agenziaimmobiliare.immobili.Abitazione;
import org.java.agenziaimmobiliare.immobili.Box;
import org.java.agenziaimmobiliare.immobili.Immobile;
import org.java.agenziaimmobiliare.immobili.Villa;
import org.java.agenziaimmobiliare.utilities.prompt.Prompt;
import org.java.agenziaimmobiliare.utilities.menu.Menu;
import org.java.agenziaimmobiliare.utilities.menu.MenuOption;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Prompt.setLocale(Locale.US);
        AgenziaImmobiliare agenzia = new AgenziaImmobiliare();


        new Menu("Benvenuto all'agenzia immobiliare GenerationItaly!",
                new MenuOption("Aggiungi Immobile") {
                    protected boolean task() {

                        new Menu("Scegli tipo di immobile",
                                new MenuOption("Box") {
                                    @Override
                                    protected boolean task() {
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
                                    protected boolean task() {
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
                                    protected boolean task() {
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
                    protected boolean task() {
                        new Menu("Scegli tipologia di ricerca",
                                new MenuOption("Per codice") {
                                    @Override
                                    protected boolean task() {
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
                                    protected boolean task() {
                                        try {
                                            List<Immobile> immobili = agenzia.getImmobiliPerCitta(
                                                    Prompt.ask("Inserisci città: ")
                                            );
                                            System.out.println(immobili);

                                            MenuOption[] immobiliOptions = new MenuOption[immobili.size()];

                                            for (int i = 0; i < immobili.size(); i++) {
                                                Immobile immobile = immobili.get(i);
                                                immobiliOptions[i] = new MenuOption(
                                                        immobile.getClass().getSimpleName() + " " +
                                                                immobile.getCodice()) {

                                                    @Override
                                                    protected boolean task() {
                                                        System.out.println(immobile);

                                                        if (Prompt.ask(
                                                                        "Vuoi aggiungere interesse a questo immobile?")
                                                                .equalsIgnoreCase("si")
                                                        ) immobile.aggiungiInteresse();


                                                        return false;
                                                    }
                                                };
                                            }

                                            new Menu("Scegli immobile", immobiliOptions).start();

                                        } catch (ImmobileNonTrovatoException e) {
                                            System.out.println(e.getMessage());
                                        }

                                        return false;
                                    }
                                }
                        ).start();

                        return false;
                    }
                },
                new MenuOption("Mostra Immobili più richiesti") {
                    protected boolean task() {
                        try {
                            List<Immobile> immobili = agenzia.getImmobiliConPiuInteresse();

                            MenuOption[] immobiliOptions = new MenuOption[immobili.size()];

                            for (int i = 0; i < immobili.size(); i++) {
                                Immobile immobile = immobili.get(i);
                                immobiliOptions[i] = new MenuOption(
                                        immobile.getClass().getSimpleName() + " " +
                                                immobile.getCodice()) {

                                    @Override
                                    protected boolean task() {
                                        System.out.println(immobile);

                                        if (Prompt.ask(
                                                        "Vuoi aggiungere interesse a questo immobile?")
                                                .equalsIgnoreCase("si")
                                        ) immobile.aggiungiInteresse();


                                        return false;
                                    }
                                };
                            }

                            new Menu("Scegli immobile", immobiliOptions).start();
                        } catch (ListaImmobiliVuotaException e) {
                            System.out.println(e.getMessage());
                        }

                        return false;
                    }
                },
                new MenuOption("Mostra Immobili in ordine di interesse") {
                    protected boolean task() {
                        try {

                            List<Immobile> immobili = agenzia.getImmobiliInOrdineDiInteresse();

                            MenuOption[] immobiliOptions = new MenuOption[immobili.size()];

                            for (int i = 0; i < immobili.size(); i++) {
                                Immobile immobile = immobili.get(i);
                                immobiliOptions[i] = new MenuOption(
                                        immobile.getClass().getSimpleName() + " " +
                                                immobile.getCodice()) {

                                    @Override
                                    protected boolean task() {
                                        System.out.println(immobile);

                                        if (Prompt.ask(
                                                        "Vuoi aggiungere interesse a questo immobile?")
                                                .equalsIgnoreCase("si")
                                        ) immobile.aggiungiInteresse();


                                        return false;
                                    }
                                };
                            }

                            new Menu("Scegli immobile", immobiliOptions).start();
                        } catch (ListaImmobiliVuotaException e) {
                            System.out.println(e.getMessage());
                        }

                        return false;
                    }
                }
        ).start();
    }


}
