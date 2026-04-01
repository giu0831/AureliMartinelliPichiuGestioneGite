/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

/**
 *
 * @author aureli.giulia
 */
public class GestioneDatabase {
    
    
    public void inserisciClasse(int anno, String sezione, String indirizzo) {
    String sql = "INSERT INTO classi (cla_anno, cla_sezione, cla_indirizzo) VALUES (?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, anno);
        pstmt.setString(2, sezione);
        pstmt.setString(3, indirizzo);
        
        pstmt.executeUpdate(); // Si usa executeUpdate per INSERT, UPDATE, DELETE
        System.out.println("Classe inserita!");
        
    } catch (SQLException e) {
        System.err.println("Errore inserimento classe: " + e.getMessage());
    }
}
    
}
