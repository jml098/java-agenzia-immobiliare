package org.java.agenzia;

public class Box extends Immobile {
    private int numeroPostiAuto;

    public Box(String codice,
               String indirizzo,
               String cap,
               String citta,
               int metriQuadri,
               int numeroPostiAuto) {

        super(codice, indirizzo, cap, citta, metriQuadri);
        this.numeroPostiAuto = numeroPostiAuto;
    }

    public int getNumeroPostiAuto() {
        return numeroPostiAuto;
    }

    public void setNumeroPostiAuto(int numeroPostiAuto) {
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
