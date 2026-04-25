package aurelimartinellipichiugestionegite;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author aureli.giulia
 */
public class GestioneDatabase {
   
    private static final String url = "jdbc:sqlite:gestione_gite.db?foreign_keys=on";

    /**
     * Metodo di inizializzazione
     */
    public static void inizializza() {
        // creazione delle tabelle
        creaTabellaClassi();
        creaTabellaGite();
        creaTabellaStudenti();
        creaTabellaPartecipazioni();
    }

    /**
     * Metodo per creare la tabella delle classi
     * @return true se e' stata creata la tabella, false se non e' riuscito a creare la tabella
     */
    public static boolean creaTabellaClassi() {
        String sql = "CREATE TABLE IF NOT EXISTS classi (\n"  
           + " cla_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"  
           + " cla_anno INTEGER NOT NULL,\n"             // Es: 3, 4
           + " cla_sezione TEXT NOT NULL,\n"             // Es: "A", "B"
           + " cla_indirizzo TEXT NOT NULL,\n"           // Es: "Informatica", "Linguistico"
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
    public static boolean creaTabellaGite() {
        String sql = "CREATE TABLE IF NOT EXISTS gite (\n"  
           + " git_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"  
           + " git_destinazione TEXT NOT NULL,\n"  
           + " git_durata INTEGER NOT NULL,\n"          
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
    public static boolean creaTabellaStudenti() {
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
     * Metodo per creare la tabella delle partecipazioni, lega gita a studente
     * @return true se e' stata creata la tabella, false se non e' riuscito a creare la tabella
     */
    public static boolean creaTabellaPartecipazioni() {
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
   
    /**
     * Inserisce una nuova classe nel database
     * @param anno anno della classe
     * @param sezione sezione della classe
     * @param indirizzo indirizzo della classe
     * @return L'ID generato per la classe, oppure -1 in caso di errore
     */
    public static int inserisciClasse(int anno, String sezione, String indirizzo) {
        String sql = "INSERT INTO classi (cla_anno, cla_sezione, cla_indirizzo) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, anno);
            pstmt.setString(2, sezione);
            pstmt.setString(3, indirizzo);

            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerato = rs.getInt(1);
                    System.out.println("Classe inserita con successo! Nuovo ID: " + idGenerato);
                    return idGenerato;
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore inserimento classe: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Inserisce una nuova gita
     * @param destinazione destinazione della gita
     * @param durata durata della gita
     * @param prezzo Prezzo della gita
     * @return L'ID generato per la gita, oppure -1 in caso di errore
     */
    public static int inserisciGita(String destinazione, int durata, int prezzo) { 
        String sql = "INSERT INTO gite (git_destinazione, git_durata, git_prezzo) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, destinazione);
            pstmt.setInt(2, durata); 
            pstmt.setInt(3, prezzo);

            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerato = rs.getInt(1);
                    System.out.println("Gita inserita con successo! Nuovo ID: " + idGenerato);
                    return idGenerato;
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore inserimento gita: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Inserisce uno studente associandolo a una classe esistente
     * @param nome Nome dello studente
     * @param cognome Cognome dello studente
     * @param idClasse L'ID (cla_id) della classe a cui appartiene
     * @return L'ID generato per lo studente, oppure -1 in caso di errore
     */
    public static int inserisciStudente(String nome, String cognome, int idClasse) {
        String sql = "INSERT INTO studenti (stu_nome, stu_cognome, stu_cla_id) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, cognome);
            pstmt.setInt(3, idClasse);

            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerato = rs.getInt(1);
                    System.out.println("Studente inserito con successo! Nuovo ID: " + idGenerato);
                    return idGenerato;
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore inserimento studente: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Registra la partecipazione di uno studente a una gita
     * @param idStudente L'ID (stu_id) dello studente
     * @param idGita L'ID (git_id) della gita
     * @return true se l'inserimento è riuscito
     */
    public static boolean inserisciPartecipazione(int idStudente, int idGita) {
        String sql = "INSERT INTO partecipazioni (par_stu_id, par_git_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idStudente);
            pstmt.setInt(2, idGita);

            pstmt.executeUpdate();
            System.out.println("Partecipazione registrata con successo!");
            return true;

        } catch (SQLException e) {
            System.out.println("Errore inserimento partecipazione: " + e.getMessage());
            return false;
        }
    }
   
    /**
     * Metodo per rimuovere uno studente
     * @param idStudente id dello studente da eliminare
     * @return true se e' stato rimosso, false altrimenti
     */
    public static boolean rimuoviStudente(int idStudente) {
        String sql = "DELETE FROM studenti WHERE stu_id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idStudente);
            int righeModificate = pstmt.executeUpdate();
            if (righeModificate > 0) {
                System.out.println("Studente rimosso con successo!");
                return true;
            } else {
                System.out.println("Nessuno studente trovato con ID: " + idStudente);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errore rimozione studente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo per rimuovere una gita
     * @param idGita id della gita da eliminare
     * @return true se e' stata eliminata, false altrimenti
     */
    public static boolean rimuoviGita(int idGita) {
        String sql = "DELETE FROM gite WHERE git_id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idGita);
            int righeModificate = pstmt.executeUpdate();
            if (righeModificate > 0) {
                System.out.println("Gita rimossa con successo!");
                return true;
            } else {
                System.out.println("Nessuna gita trovata con ID: " + idGita);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errore rimozione gita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo per rimuovere una classe
     * @param idClasse id della classe da eliminare
     * @return true se e' stata eliminata, false altrimenti
     */
    public static boolean rimuoviClasse(int idClasse) {
        String sql = "DELETE FROM classi WHERE cla_id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idClasse);
            int righeModificate = pstmt.executeUpdate();
            if (righeModificate > 0) {
                System.out.println("Classe rimossa con successo!");
                return true;
            } else {
                System.out.println("Nessuna classe trovata con ID: " + idClasse);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errore rimozione classe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo per rimuovere una partecipazione
     * @param idStudente id dello studente
     * @param idGita id della gita
     * @return true se e' staat eliminata, false altrimenti
     */
    public static boolean rimuoviPartecipazione(int idStudente, int idGita) {
        String sql = "DELETE FROM partecipazioni WHERE par_stu_id = ? AND par_git_id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idStudente);
            pstmt.setInt(2, idGita);
            int righeModificate = pstmt.executeUpdate();
            if (righeModificate > 0) {
                System.out.println("Partecipazione rimossa con successo!");
                return true;
            } else {
                System.out.println("Nessuna partecipazione trovata.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errore rimozione partecipazione: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Restituisce la lista di tutte le classi
     */
    public static ArrayList<Classe> getListaClassi() {
        ArrayList<Classe> lista = new ArrayList<>();
        String sql = "SELECT * FROM classi";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Classe c = new Classe(
                    rs.getInt("cla_id"),
                    rs.getInt("cla_anno"),
                    rs.getString("cla_sezione"),
                    rs.getString("cla_indirizzo")
                    
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Errore caricamento classi: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Restituisce la lista di tutti gli studenti
     */
    public static ArrayList<Studente> getListaStudenti() {
        ArrayList<Studente> lista = new ArrayList<>();
        String sql = "SELECT s.stu_id, s.stu_nome, s.stu_cognome, " +
                     "c.cla_id, c.cla_anno, c.cla_sezione, c.cla_indirizzo " + // Aggiunto c.cla_id alla query per la classe
                     "FROM studenti s " +
                     "JOIN classi c ON s.stu_cla_id = c.cla_id";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Creazione classe 
                Classe classeDelloStudente = new Classe(
                    rs.getInt("cla_id"),
                    rs.getInt("cla_anno"), 
                    rs.getString("cla_sezione"), 
                    rs.getString("cla_indirizzo")
                    
                );
                
                //creazione studente
                Studente s = new Studente(
                    rs.getInt("stu_id"),
                    rs.getString("stu_nome"),
                    rs.getString("stu_cognome"),
                    classeDelloStudente
                );
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Errore caricamento studenti: " + e.getMessage());
        }
        return lista;
    }
   
    /**
     * Restituisce la lista di tutte le gite disponibili
     */
    public static ArrayList<Gita> getListaGite() {
        ArrayList<Gita> lista = new ArrayList<>();
        String sql = "SELECT * FROM gite";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Creazione gita
                Gita g = new Gita(
                    rs.getInt("git_id"),
                    rs.getString("git_destinazione"),
                    rs.getInt("git_durata"), 
                    rs.getInt("git_prezzo")
                    
                );
                lista.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Errore caricamento gite: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Restituisce la lista di tutte le gite a cui è iscritto uno specifico studente
     * @param idStudente L'ID (stu_id) dello studente
     * @return ArrayList di oggetti Gita
     */
    public static ArrayList<Integer> getGiteStudente(int idStudente) {
        ArrayList<Integer> lista = new ArrayList<>();
        
        // La query unisce la tabella "partecipazioni" e "gite"
        // e filtra i risultati usando l'ID dello studente
        String sql = "SELECT g.git_id, g.git_destinazione, g.git_durata, g.git_prezzo " +
                     "FROM partecipazioni p " +
                     "JOIN gite g ON p.par_git_id = g.git_id " +
                     "WHERE p.par_stu_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idStudente);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("git_id"));
            }
        } catch (SQLException e) {
            System.out.println("Errore caricamento gite dello studente: " + e.getMessage());
        }
        return lista;
    }
    

    /**
     * Cambia la classe di uno studente
     * @param idStudente id dello studente
     * @param idNuovaClasse id della nuova classe
     * @return true se e' stata cambiata, false altrimenti
     */
    public static boolean aggiornaClasseStudente(int idStudente, int idNuovaClasse) {
        String sql = "UPDATE studenti SET stu_cla_id = ? WHERE stu_id = ?";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idNuovaClasse); // Il nuovo ID della classe
            pstmt.setInt(2, idStudente);    // L'ID dello studente da spostare

            int righeModificate = pstmt.executeUpdate();
            return righeModificate > 0; // Se è maggiore di 0, ha funzionato

        } catch (SQLException e) {
            System.out.println("Errore nell'aggiornamento dello studente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Metodo per rimuovere tutte le partecipazioni associate a uno specifico studente
     * @param idStudente L'ID (stu_id) dello studente
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean rimuoviPartecipazioniPerStudente(int idStudente) {
        String sql = "DELETE FROM partecipazioni WHERE par_stu_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idStudente);
            int righeModificate = pstmt.executeUpdate();
            
            System.out.println("Rimosse " + righeModificate + " partecipazioni per lo studente con ID: " + idStudente);
            return true; 
            
        } catch (SQLException e) {
            System.out.println("Errore rimozione partecipazioni dello studente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo per rimuovere tutte le partecipazioni associate a una specifica gita
     * @param idGita L'ID (git_id) della gita
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean rimuoviPartecipazioniPerGita(int idGita) {
        String sql = "DELETE FROM partecipazioni WHERE par_git_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idGita);
            int righeModificate = pstmt.executeUpdate();
            
            System.out.println("Rimosse " + righeModificate + " partecipazioni per la gita con ID: " + idGita);
            return true;
            
        } catch (SQLException e) {
            System.out.println("Errore rimozione partecipazioni della gita: " + e.getMessage());
            return false;
        }
    }

}