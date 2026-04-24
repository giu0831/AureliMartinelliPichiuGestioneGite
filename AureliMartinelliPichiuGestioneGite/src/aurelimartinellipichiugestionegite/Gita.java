/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

/**
 *
 * @author aureli.giulia
 */
public class Gita {
    private String destinazione;
    private int durata;
    private int prezzo;
    private int id;

    public Gita(String destinazione, int durata, int prezzo) {
        this.destinazione = destinazione;
        this.durata = durata;
        this.prezzo = prezzo;
    }

    public Gita(int id, String destinazione, int durata, int prezzo) {
        this.destinazione = destinazione;
        this.durata = durata;
        this.prezzo = prezzo;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDestinazione() {
        return destinazione;
    }

    public int getDurata() {
        return durata;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return destinazione + ", " + durata + " giorni, " + prezzo + " euro, id:" + id ;
    }
    
    
    
    
}
