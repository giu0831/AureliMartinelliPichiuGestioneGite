/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aurelimartinellipichiugestionegite;

/**
 *
 * @author aureli.giulia
 */
public class AureliMartinelliPichiuGestioneGite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //creazione database
        GestioneDatabase.inizializza();
        //carica tutte le classi dal database
        for (Classe c : GestioneDatabase.getListaClassi()) {
            GestioneClassi.aggiungiClasse(c);
        }
        // Carica tutte le gite dal database 
        for (Gita g : GestioneDatabase.getListaGite()) {
            GestioneGite.aggiungiGita(g);
        }
        // Carica tutti gli studenti dal database 
        for (Studente s : GestioneDatabase.getListaStudenti()) {
            GestioneStudenti.aggiungiStudente(s);
            s.setIdGita(GestioneDatabase.getGiteStudente(s.getMatricola()));
        }
        //apertura form
        java.awt.EventQueue.invokeLater(() -> new FrmGestioneGite().setVisible(true));
    }
    
}
