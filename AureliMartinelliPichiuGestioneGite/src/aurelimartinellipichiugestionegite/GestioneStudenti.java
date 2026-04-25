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
public class GestioneStudenti {
    

    private static ArrayList<Studente> listaStudenti = new ArrayList<>();

    /**
     * Metodo get per leggere la lista e mostrarla nei vari form
     * @return lista di studenti
     */
    public static ArrayList<Studente> getListaStudenti() {
        return listaStudenti;
    }

    /**
     * Aggiunge uno studente alla lista 
     * @param s lo studente da aggiungere
     */
    public static void aggiungiStudente(Studente s) {
        if (s != null) {
            listaStudenti.add(s);
        }
    }

    /**
     * Rimuove uno studente dalla lista 
     * @param matricola la matricola dello studente da rimuovere
     * @return true se lo studente e' stato rimosso, false altrimenti
     */
    public static boolean rimuoviStudente(int matricola) {
        for (Studente s : listaStudenti) {
            if (s.getMatricola() == matricola) {
                listaStudenti.remove(s);
                return true; 
            }
        }
        return false;
    }
    
    /**
     * Metodo per cercare uno studente in base alla matricola
     * @param matricola matricola da cercare
     * @return studente con la matricola passata o null se non e' stato trivato
     */
    public static Studente cercaPerMatricola(int matricola){
        for(Studente s : listaStudenti){
            if(s.getMatricola() == matricola)return s;
        }
        return null;
    }
    
    /**
     * Metodo per cercare studenti appartenenti ad una classe specifica
     * @param c classe
     * @return lista di studenti
     */
    public static ArrayList<Studente> cercaStudentiPerClasse(Classe c){
        ArrayList<Studente> studenti = new ArrayList<>();
        for(Studente s : listaStudenti){
            if(s.getClasse().equals(c)) studenti.add(s);
        }
        return studenti;
    }
}