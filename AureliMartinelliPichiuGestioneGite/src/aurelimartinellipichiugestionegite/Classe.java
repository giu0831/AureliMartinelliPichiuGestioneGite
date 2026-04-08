/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

/**
 * Classe che contiene i dettagli riguardo alla classe frequentata da uno studente
 * @author aureli.giulia
 */
public class Classe {
    private String anno;
    private String sezione;
    private String indirizzo;

    /**
     * Metodo costruttore
     * @param anno anno della classe
     * @param sezione sezione della classe
     * @param indirizzo indirizzo della classe
     */
    public Classe(String anno, String sezione, String indirizzo) {
        this.anno = anno;
        this.sezione = sezione;
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo get
     * @return anno
     */
    public String getAnno() {
        return anno;
    }

    /**
     * Metodo get
     * @return sezione
     */
    public String getSezione() {
        return sezione;
    }

    /**
     * Metodo get
     * @return indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public String toString() {
        return anno + sezione + " " + indirizzo;
    }
    
}
