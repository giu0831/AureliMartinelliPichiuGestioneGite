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
        GestioneDatabase.creaTabellaClassi();
        GestioneDatabase.creaTabellaGite();
        GestioneDatabase.creaTabellaStudenti();
        GestioneDatabase.creaTabellaPartecipazioni();
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
        }
        java.awt.EventQueue.invokeLater(() -> new FrmGestioneGite().setVisible(true));
    }
    
}
