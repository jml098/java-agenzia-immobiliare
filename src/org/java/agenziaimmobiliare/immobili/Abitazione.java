package org.java.agenziaimmobiliare.immobili;

public class Abitazione extends Immobile {
    private int numeroVani;

    public Abitazione(String codice,
                      String indirizzo,
                      String cap,
                      String citta,
                      int metriQuadri,
                      int numeroVani) throws IllegalArgumentException{

        super(codice, indirizzo, cap, citta, metriQuadri);

        if (numeroVani < 0 ) throw new IllegalArgumentException("Numeri dei vani non può essere minore di 0.");
        this.numeroVani = numeroVani;
    }

    public int getNumeroVani() {
        return numeroVani;
    }

    public void setNumeroVani(int numeroVani) throws IllegalArgumentException{
        if (numeroVani < 0 ) throw new IllegalArgumentException("Numeri dei vani non può essere minore di 0.");
        this.numeroVani = numeroVani;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        superString = superString
                .replace("Immobile{", "")
                .replace("}", "");

        return "Abitazione{" + superString + ", " +
                "numeroVani=" + numeroVani +
                '}';
    }
}
