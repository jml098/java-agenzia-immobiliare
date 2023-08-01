package org.java.agenziaimmobiliare;

import org.java.agenziaimmobiliare.agenzia.AgenziaImmobiliare;
import org.java.agenziaimmobiliare.immobili.Abitazione;
import org.java.agenziaimmobiliare.immobili.Box;
import org.java.agenziaimmobiliare.immobili.Immobile;
import org.java.agenziaimmobiliare.immobili.Villa;
import org.java.agenziaimmobiliare.utilities.prompt.Prompt;
import org.java.agenziaimmobiliare.utilities.menu.Menu;
import org.java.agenziaimmobiliare.utilities.menu.MenuOption;


import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Prompt.setLocale(Locale.US);
        Prompt.setErrorMessage("Invalid value, ");
        AgenziaImmobiliare agenzia = new AgenziaImmobiliare();

        new Menu("Benvenuto all'agenzia immobiliare GenerationItaly!",
                new MenuOption("Aggiungi Immobile").setTask(() -> {

                    new Menu("Scegli tipo di immobile",
                            new MenuOption("Box").setTask(() -> {
                                agenzia.aggiungiImmobile(new Box(
                                        Prompt.ask("Inserisci codice alfanumerico: "),
                                        Prompt.ask("Inserisci indirizzo: "),
                                        Prompt.ask("Inserisci CAP: "),
                                        Prompt.ask("Inserisci città: "),
                                        Prompt.askInt("Inserisci metri quadri: "),
                                        Prompt.askInt("Inserisci posti auto: ")
                                ));
                            }),
                            new MenuOption("Abitazione").setTask(() -> {
                                agenzia.aggiungiImmobile(new Abitazione(
                                        Prompt.ask("Inserisci codice alfanumerico: "),
                                        Prompt.ask("Inserisci indirizzo: "),
                                        Prompt.ask("Inserisci CAP: "),
                                        Prompt.ask("Inserisci città: "),
                                        Prompt.askInt("Inserisci metri quadri"),
                                        Prompt.askInt("Inserisci vani: ")
                                ));
                            }),
                            new MenuOption("Villa").setTask(() -> {
                                agenzia.aggiungiImmobile(new Villa(
                                        Prompt.ask("Inserisci codice alfanumerico: "),
                                        Prompt.ask("Inserisci indirizzo: "),
                                        Prompt.ask("Inserisci CAP: "),
                                        Prompt.ask("Inserisci città: "),
                                        Prompt.askInt("Inserisci metri quadri: "),
                                        Prompt.askInt("Inserisci vani: "),
                                        Prompt.askInt("Inserisci metri quadri giardino: ")
                                ));
                            })
                    );

                }),
                new MenuOption("Cerca Immobile").setTask(() ->
                        new Menu("Scegli tipo di ricerca",
                                new MenuOption("Per codice").setTask(() -> {
                                    System.out.println(agenzia.getImmobilePerCodice(Prompt.ask("Inserisci il codice: ")));
                                }),
                                new MenuOption("Per città").setTask(() -> {
                                    Menu.fromList(
                                            agenzia.getImmobiliPerCitta(Prompt.ask("Inserisci città: ")),
                                            "Scegli immobile: ",
                                            (Immobile immobile) -> {
                                                return new MenuOption(
                                                        immobile.getClass().getSimpleName() + " " +
                                                                immobile.getCodice() + " " +
                                                                immobile.getInteressi() + " interessati").setTask(() -> {

                                                    if (Prompt.ask("Sei interessato a questo immobile?").equalsIgnoreCase("si"))
                                                        immobile.aggiungiInteresse();
                                                }, true);
                                            }
                                    );

                                })
                        )),
                new MenuOption("Mostra Immobili più richiesti").setTask(() -> {
                    Menu.fromList(
                            agenzia.getImmobiliConPiuInteresse(),
                            "Scegli immobile: ",
                            (Immobile immobile) -> {
                                return new MenuOption(
                                        immobile.getClass().getSimpleName() + " " +
                                                immobile.getCodice() + " " +
                                                immobile.getInteressi() + " interessati").setTask(() -> {

                                    if (Prompt.ask("Sei interessato a questo immobile?").equalsIgnoreCase("si"))
                                        immobile.aggiungiInteresse();
                                }, true);
                            }
                    );

                })
        );
    }


}
