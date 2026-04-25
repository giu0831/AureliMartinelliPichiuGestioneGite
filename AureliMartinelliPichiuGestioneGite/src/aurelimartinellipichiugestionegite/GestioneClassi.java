/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

import java.util.ArrayList;

/**
 *
 * @author aureli.giulia
 */
public class GestioneClassi {
    
    // Lista statica condivisa tra tutti i form
    private static ArrayList<Classe> listaClassi = new ArrayList<>();

    /**
     * Metodo per ottenere l'intera lista delle classi
     * @return lista di classi
     */
    public static ArrayList<Classe> getListaClassi() {
        return listaClassi;
    }

    /**
     * Aggiunge una nuova classe alla lista
     * @param c la classe da aggiungere
     */
    public static void aggiungiClasse(Classe c) {
        if (c != null) {
            listaClassi.add(c);
        }
    }

    /**
     * Rimuove una classe dalla lista a partire dal suo ID usando un for-each
     * @param id l'identificativo numerico della classe da rimuovere
     * @return true se la classe e' stata rimossa, false altrimenti
     */
    public static boolean rimuoviClasse(int id) {
        for (Classe c : listaClassi) {
            if (c.getId() == id) {
                listaClassi.remove(c);
                return true; 
            }
        }
        return false; // Classe non trovata
    }
    
    /**
     * Metodo per cercare una classe in base all'id
     * @param id id della classe
     * @return classe con l'id passato, null se non trovata
     */
    public static Classe cercaClassePerId(int id){
        for (Classe c : listaClassi) {
            if(c.getId() == id)return c;
        }
        return null;
    }
}