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

    public Gita(String destinazione, int durata, int prezzo) {
        this.destinazione = destinazione;
        this.durata = durata;
        this.prezzo = prezzo;
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
}
