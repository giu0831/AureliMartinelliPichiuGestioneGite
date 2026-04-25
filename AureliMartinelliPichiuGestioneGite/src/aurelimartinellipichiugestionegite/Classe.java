/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

import java.util.Objects;

/**
 * Classe che contiene i dettagli riguardo alla classe frequentata da uno studente
 * @author aureli.giulia
 */
public class Classe {
    private int anno;
    private String sezione;
    private String indirizzo;
    private int id;

    /**
     * Metodo costruttore
     * @param anno anno della classe
     * @param sezione sezione della classe
     * @param indirizzo indirizzo della classe
     */
    public Classe(int anno, String sezione, String indirizzo) {
        this.anno = anno;
        this.sezione = sezione;
        this.indirizzo = indirizzo;
        id = -1;
    }

    /**
     * Metodo costruttore
     * @param id id della classe
     * @param anno anno della classe
     * @param sezione sezione della classe
     * @param indirizzo indirizzo della classe
     */
    public Classe(int id, int anno, String sezione, String indirizzo) {
        this.anno = anno;
        this.sezione = sezione;
        this.indirizzo = indirizzo;
        this.id = id;
    }
    

    /**
     * Metodo get
     * @return anno
     */
    public int getAnno() {
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

    /**
     * Metodo get
     * @return id dell classe
     */
    public int getId() {
        return id;
    }
    
    

    /**
     * Metodo set
     * @param id id della classe
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Classe other = (Classe) obj;
        if (this.anno != other.anno) {
            return false;
        }
        if (!Objects.equals(this.sezione, other.sezione)) {
            return false;
        }
        return Objects.equals(this.indirizzo, other.indirizzo);
    }
    

    @Override
    public String toString() {
        return anno + sezione + " " + indirizzo;
    }
    
}
