/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

import java.sql.*;

/**
 *
 * @author aureli.giulia
 */
public class GestioneDatabase {
   
    private final String url = "jdbc:sqlite:gestione_gite.db?foreign_keys=on";

    /**
     * Metodo costruttore
     */
    public GestioneDatabase() {
        //creazione delle tabelle
        creaTabellaClassi();
        creaTabellaGite();
        creaTabellaStudenti();
        creaTabellaPartecipazioni();
    }

    /**
     * Metodo per creare la tabella delle classi
     * @return true se e' stata creata la tabella, false se non e' riuscito a creare la tabella
     */
    public boolean creaTabellaClassi() {
        String sql = "CREATE TABLE IF NOT EXISTS classi (\n"  
           + " cla_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"  
           + " cla_anno INTEGER NOT NULL,\n"           // Es: 3, 4
           + " cla_sezione TEXT NOT NULL,\n"           // Es: "A", "B"
           + " cla_indirizzo TEXT NOT NULL,\n"         // Es: "Informatica", "Linguistico"
           + " UNIQUE(cla_anno, cla_sezione, cla_indirizzo)\n" // Evita duplicati come due "3 A Informatica"
           + ");";
        try (Connection conn = DriverManager.getConnection(url);  
             Statement stmt = conn.createStatement()) {  
             
            stmt.execute(sql);  
            System.out.println("Tabella 'classi' creata con successo!");
            return true;
           
        } catch (SQLException e) {  
            System.out.println("Errore in creaTabellaClassi: " + e.getMessage());  
            return false;
        }
    }

    /**
     * Metodo per creare la tabella delle gite
     * @return true se e' stata creata la tabella, false se non e' riuscito a creare la tabella
     */
    public boolean creaTabellaGite() {
        String sql = "CREATE TABLE IF NOT EXISTS gite (\n"  
           + " git_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"  
           + " git_destinazione TEXT NOT NULL,\n"  
           + " git_durata TEXT NOT NULL,\n"           
           + " git_prezzo INTEGER NOT NULL\n"       
           + ");";

        try (Connection conn = DriverManager.getConnection(url);  
             Statement stmt = conn.createStatement()) {  
             
            stmt.execute(sql);  
            System.out.println("Tabella 'gite' creata con successo!");
            return true;
           
        } catch (SQLException e) {  
            System.out.println("Errore in creaTabellaGite: " + e.getMessage());  
            return false;
        }
    }

    /**
     * Metodo per creare la tabella degli studenti
     * @return true se e' stata creata la tabella, false se non e' riuscito a creare la tabella
     */
    public boolean creaTabellaStudenti() {
        String sql = "CREATE TABLE IF NOT EXISTS studenti (\n"  
           + " stu_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"  
           + " stu_nome TEXT NOT NULL,\n"  
           + " stu_cognome TEXT NOT NULL,\n"
           + " stu_cla_id INTEGER NOT NULL,\n" 
           + " FOREIGN KEY (stu_cla_id) REFERENCES classi(cla_id)\n" 
           + ");";

        try (Connection conn = DriverManager.getConnection(url);  
             Statement stmt = conn.createStatement()) {  
             
            stmt.execute(sql);  
            System.out.println("Tabella 'studenti' creata con successo!");
            return true;
           
        } catch (SQLException e) {  
            System.out.println("Errore in creaTabellaStudenti: " + e.getMessage());  
            return false;
        }
    }

    /**
     * Metodo per creare la tabella delle partecipazioni
     * @return true se e' stata creata la tabella, false se non e' riuscito a creare la tabella
     */
    public boolean creaTabellaPartecipazioni() {
        // "tabella ponte" o di congiunzione, perché serve solo a collegare gli studenti alle gite in una relazione "molti-a-molti"
        String sql = "CREATE TABLE IF NOT EXISTS partecipazioni (\n"  
           + " par_stu_id INTEGER NOT NULL,\n"  
           + " par_git_id INTEGER NOT NULL,\n"  
           + " PRIMARY KEY (par_stu_id, par_git_id),\n" // Uno studente non può iscriversi due volte alla stessa gita
           + " FOREIGN KEY (par_stu_id) REFERENCES studenti(stu_id),\n" // Collega allo studente
           + " FOREIGN KEY (par_git_id) REFERENCES gite(git_id)\n" // Collega alla gita
           + ");";

        try (Connection conn = DriverManager.getConnection(url);  
             Statement stmt = conn.createStatement()) {  
             
            stmt.execute(sql);  
            System.out.println("Tabella 'partecipazioni' creata con successo!");
            return true;
           
        } catch (SQLException e) {  
            System.out.println("Errore in creaTabellaPartecipazioni: " + e.getMessage());  
            return false;
        }
    }
}
