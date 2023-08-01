package org.java.agenzia;

import java.util.ArrayList;
import java.util.List;

public class AgenziaImmobiliare {
    private final List<Immobile> immobili = new ArrayList<>();


    public void aggiungiImmobile(Immobile immobile) throws ConflittoCodiceImmobileException {
        if (controllaDuplicati(immobile.getCodice())) {
            throw new ConflittoCodiceImmobileException(
                    "Impossibile aggiungere l'immobile. \n" +
                    "Esiste già un immobile con codice: " + immobile.getCodice()
            );
        }

        immobili.add(immobile);
    }

    public Immobile getImmobilePerCodice(String codice) throws ImmobileNonTrovatoException {
        for (Immobile immobile :
                immobili) {

            if (immobile.getCodice().equals(codice)) {
                immobile.aggiungiInteresse();
                return immobile;
            }
        }

        throw new ImmobileNonTrovatoException(
                "Non esiste nessun immobile con codice: " + codice
        );
    }

    public List<Immobile> getImmobiliConPiuInteresse() throws ListaImmobiliVuotaException {
        if (immobili.size() == 0) throw new ListaImmobiliVuotaException(
                "La lista di immobili è vuota."
        );

        int max = Integer.MIN_VALUE;
        List<Immobile> immobiliConPiuInteresse = new ArrayList<>();

        for (Immobile immobile :
                immobili) {


            if (immobile.getInteressi() > max) {
                max = immobile.getInteressi();
                immobiliConPiuInteresse = new ArrayList<>();
                immobiliConPiuInteresse.add(immobile);
            } else if (immobile.getInteressi() == max) {
                immobiliConPiuInteresse.add(immobile);
            }
        }

        return immobiliConPiuInteresse;
    }

    private boolean controllaDuplicati(String codice) {
        for (Immobile immobile :
                immobili) {
            if (immobile.getCodice().equals(codice)) return true;
        }
        return false;
    }

}
