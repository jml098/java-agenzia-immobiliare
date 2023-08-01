package org.java.agenzia;

public class Immobile {
    private String codice;
    private String indirizzo;
    private String cap;
    private String citta;
    private int metriQuadri;
    private int interessi;

    public Immobile(String codice,
                    String indirizzo,
                    String cap,
                    String citta,
                    int metriQuadri) throws IllegalArgumentException{

        this.codice = codice;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.citta = citta;

        if (metriQuadri < 0) throw new IllegalArgumentException("Metri quadri non può essere minore di 0");
        this.metriQuadri = metriQuadri;
        this.interessi = 0;
    }

    public void aggiungiInteresse() {
        interessi++;
    }

    public void reimpostaSuperficie(int superficie) throws IllegalArgumentException{
        if (superficie < 0 ) throw new IllegalArgumentException("Superficie non può essere minore di 0.");

        this.metriQuadri = superficie;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getMetriQuadri() {
        return metriQuadri;
    }

    public void setMetriQuadri(int metriQuadri) throws IllegalArgumentException {
        if (metriQuadri < 0) throw new IllegalArgumentException("Metri quadri non può essere meno di 0");
        this.metriQuadri = metriQuadri;
    }

    public int getInteressi() {
        return interessi;
    }

    public void setInteressi(int interessi) throws IllegalArgumentException {
        if (interessi < 0) throw new IllegalArgumentException("Interessi non può essere meno di 0");
        this.interessi = interessi;
    }

    @Override
    public String toString() {
        return "Immobile{" +
                "codice='" + codice + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", cap='" + cap + '\'' +
                ", citta='" + citta + '\'' +
                ", metriQuadri=" + metriQuadri +
                ", interessi=" + interessi +
                '}';
    }
}
