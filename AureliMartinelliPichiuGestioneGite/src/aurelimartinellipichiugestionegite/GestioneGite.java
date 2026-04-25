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
public class GestioneGite {
    
    private static ArrayList<Gita> listaGite = new ArrayList<>();

    /**
     * Metodo per ottenere l'intera lista delle gite
     * @return lista di gite
     */
    public static ArrayList<Gita> getListaGite() {
        return listaGite;
    }

    /**
     * Aggiunge una gita alla lista
     * @param g la gita da aggiungere
     */
    public static void aggiungiGita(Gita g) {
        if (g != null) {
            listaGite.add(g);
        }
    }

    /**
     * Rimuove una gita dalla lista 
     * @param id l'id della gita da rimuovere
     * @return true se la gita e' stata rimossa, false altrimenti
     */
    public static boolean rimuoviGita(int id) {
        for (Gita g : listaGite) {
            if (g.getId() == id) {
                listaGite.remove(g);
                return true; 
            }
        }
        return false; // Gita non trovata
    }
    
    /**
     * Metod per cercare una gita in base al'id
     * @param idGita id da cercare
     * @return gita con l'id passato, o null se non e' stata trovata
     */
    public static Gita cercaPerId(int idGita){
        for(Gita g : listaGite){
            if(idGita == g.getId()) return g;
        }
        return null;
    }
}