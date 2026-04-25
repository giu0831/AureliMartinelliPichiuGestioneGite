/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

/**
 * Classe gita
 * @author aureli.giulia
 */
public class Gita {
    private String destinazione;
    private int durata;
    private int prezzo;
    private int id;

    /**
     * Metodo costruttore
     * @param destinazione destinazione della gita
     * @param durata durata della gita
     * @param prezzo prezzo della gita
     */
    public Gita(String destinazione, int durata, int prezzo) {
        this.destinazione = destinazione;
        this.durata = durata;
        this.prezzo = prezzo;
    }

    /**
     * Metodo costruttore
     * @param id id della gita
     * @param destinazione destinazione della gita
     * @param durata durata della gita
     * @param prezzo prezzo della gita
     */
    public Gita(int id, String destinazione, int durata, int prezzo) {
        this.destinazione = destinazione;
        this.durata = durata;
        this.prezzo = prezzo;
        this.id = id;
    }

    /**
     * Metodo get
     * @return destinazione della gita
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Metodo get
     * @return durata della gita
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Metodo get
     * @return prezzo della gita
     */
    public int getPrezzo() {
        return prezzo;
    }

    /**
     * Metodo get
     * @return id della gita
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo set
     * @param id id della gita
     */
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return destinazione + ", " + durata + " giorni, " + prezzo + " euro, id:" + id ;
    }
    
    
    
    
}
