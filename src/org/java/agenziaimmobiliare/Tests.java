package org.java.agenziaimmobiliare;

import org.java.agenziaimmobiliare.agenzia.AgenziaImmobiliare;
import org.java.agenziaimmobiliare.exceptions.ConflittoCodiceImmobileException;
import org.java.agenziaimmobiliare.exceptions.ImmobileNonTrovatoException;
import org.java.agenziaimmobiliare.immobili.Abitazione;
import org.java.agenziaimmobiliare.immobili.Box;
import org.java.agenziaimmobiliare.immobili.Villa;

public class Tests {
    public static void main(String[] args) {
        AgenziaImmobiliare agenzia = new AgenziaImmobiliare();

        //Aggiunta Box
        Box box = new Box(
                "abc",
                "indirizzo1",
                "1",
                "Milano",
                100,
                2);

        // Reimpostazione superficie Box
        box.reimpostaSuperficie(110);
        agenzia.aggiungiImmobile(box);

        //Aggiunta Abitazione
        Abitazione abitazione = new Abitazione(
                "def",
                "indirizzo2",
                "2",
                "Torino",
                120,
                3);

        // Reimpostazione superficie abitazione
        abitazione.reimpostaSuperficie(150);
        agenzia.aggiungiImmobile(abitazione);


        // Aggiunta di un immobile con codice già presente
        try {
            agenzia.aggiungiImmobile(new Villa(
                    "def",
                    "indirizzo2",
                    "2",
                    "Torino",
                    100,
                    3,
                    60)
            );

        } catch (ConflittoCodiceImmobileException e) {
            System.out.println(e.getMessage());
        }

        // Aggiunta di un immobile con metri quadri non validi
        try {
            agenzia.aggiungiImmobile(new Villa(
                    "def",
                    "indirizzo2",
                    "2",
                    "Torino",
                    -12,
                    3,
                    60)
            );

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //Aggiunta Villa
        Villa villa = new Villa(
                "ghi",
                "indirizzo3",
                "3",
                "Venezia",
                180,
                3,
                50);

        // Reimpostazione superficie villa
        villa.reimpostaSuperficie(120, 30);
        agenzia.aggiungiImmobile(villa);


        // Ricerca per codice
        System.out.println(agenzia.getImmobilePerCodice("abc"));
        System.out.println(agenzia.getImmobilePerCodice("abc"));
        System.out.println(agenzia.getImmobilePerCodice("abc"));
        System.out.println(agenzia.getImmobilePerCodice("abc"));

        // Ricerca di un codice non esistente
        try {
            System.out.println(agenzia.getImmobilePerCodice("3412"));
        } catch (ImmobileNonTrovatoException e) {
            System.out.println(e.getMessage());
        }

        // Ricerca per codice
        System.out.println(agenzia.getImmobilePerCodice("def"));
        System.out.println(agenzia.getImmobilePerCodice("def"));
        System.out.println(agenzia.getImmobilePerCodice("def"));
        System.out.println(agenzia.getImmobilePerCodice("def"));
        System.out.println(agenzia.getImmobilePerCodice("ghi"));
        System.out.println(agenzia.getImmobilePerCodice("ghi"));

        // Immobili con più interesse
        System.out.println(
                "Immobili con più interesse: \n"
                        + agenzia.getImmobiliConPiuInteresse());


        // Immobili in ordine d'interesse
        System.out.println(
                "Immobili in ordine d'interesse: \n"
                        + agenzia.getImmobiliInOrdineDiInteresse());
    }
}
