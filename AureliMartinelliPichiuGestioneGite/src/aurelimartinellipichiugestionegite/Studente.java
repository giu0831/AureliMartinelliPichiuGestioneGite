/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

/**
 *
 * @author aureli.giulia
 */
public class Studente {
    private int matricola;
    private String nome;
    private String cognome;
    private Classe classe;

    public Studente(int matricola, String nome, String cognome, Classe classe) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
    }

    public Studente(String nome, String cognome, Classe classe) {
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
        matricola = -1;
    }
    

    public int getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Classe getClasse() {
        return classe;
    }

    /**
     * Metodo set
     * @param matricola 
     */
    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    
 
    
}
