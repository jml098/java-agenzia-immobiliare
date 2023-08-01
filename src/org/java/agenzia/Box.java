package org.java.agenzia;

public class Box extends Immobile {
    private int numeroPostiAuto;

    public Box(String codice,
               String indirizzo,
               String cap,
               String citta,
               int metriQuadri,
               int numeroPostiAuto) throws IllegalArgumentException{

        super(codice, indirizzo, cap, citta, metriQuadri);

        if (numeroPostiAuto < 0 ) throw new IllegalArgumentException("Numero posti auto non può essere minore di 0.");
        this.numeroPostiAuto = numeroPostiAuto;
    }

    public int getNumeroPostiAuto() {
        return numeroPostiAuto;
    }

    public void setNumeroPostiAuto(int numeroPostiAuto)throws IllegalArgumentException {
        if (numeroPostiAuto < 0 ) throw new IllegalArgumentException("Numero posti auto non può essere minore di 0.");
        this.numeroPostiAuto = numeroPostiAuto;
    }


    @Override
    public String toString() {
        String superString = super.toString();
        superString = superString
                .replace("Immobile{", "")
                .replace("}", "");

        return "Box{" + superString + ", " +
                "numeroPostiAuto=" + numeroPostiAuto +
                '}';
    }
}
