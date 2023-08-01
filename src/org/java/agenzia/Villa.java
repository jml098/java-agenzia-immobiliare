package org.java.agenzia;

public class Villa extends Abitazione{
    private int metriQuadriGiardino;

    public Villa(String codice,
                 String indirizzo,
                 String cap,
                 String citta,
                 int metriQuadri,
                 int numeroVani,
                 int metriQuadriGiardino) {

        super(codice, indirizzo, cap, citta, metriQuadri, numeroVani);
        this.metriQuadriGiardino = metriQuadriGiardino;
    }


    public void reimpostaSuperficie(int superficie, int superficieGiardino) throws IllegalArgumentException{
        setMetriQuadri(superficie);
        setMetriQuadriGiardino(superficieGiardino);
    }

    public int getMetriQuadriGiardino() {
        return metriQuadriGiardino;
    }

    public void setMetriQuadriGiardino(int metriQuadriGiardino) throws IllegalArgumentException {
        if (metriQuadriGiardino < 0) throw new IllegalArgumentException("Metri quadri non può essere meno di 0");
        this.metriQuadriGiardino = metriQuadriGiardino;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        superString = superString
                .replace("Abitazione{", "")
                .replace("}", "");

        return "Villa{" + superString + ", " +
                "metriQuadriGiardino=" + metriQuadriGiardino +
                '}';
    }
}
