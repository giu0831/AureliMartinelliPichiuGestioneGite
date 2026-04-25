/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiugestionegite;

import java.util.ArrayList;

/**
 * Classe studente
 * @author aureli.giulia
 */
public class Studente {
    private int matricola;
    private String nome;
    private String cognome;
    private Classe classe;
    private ArrayList<Integer> idGita;

    /**
     * Metodo costruttore
     * @param matricola matricola dello studente
     * @param nome nome dello studente
     * @param cognome cognome dello studente
     * @param classe classe dello studente
     */
    public Studente(int matricola, String nome, String cognome, Classe classe) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
        idGita = new ArrayList<>();
        
    }

    /**
     * Metodo costruttore
     * @param nome nome dello studente
     * @param cognome cognome dello studente
     * @param classe classe dello studente
     */
    public Studente(String nome, String cognome, Classe classe) {
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
        matricola = -1;
        idGita = new ArrayList<>();
    }
    
    /**
     * Metodo get
     * @return matricola dello studente
     */
    public int getMatricola() {
        return matricola;
    }

    /**
     * Metodo get
     * @return nome dello studente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo get
     * @return cognome dello studente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo get
     * @return classe dello studente
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * Metodo get
     * @return lista di gite a cui partecipa
     */
    public ArrayList<Integer> getIdGita() {
        return idGita;
    }
    
    /**
     * Metodo set
     * @param matricola matricola dello studente
     */
    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    /**
     * Metodo set
     * @param idGita 
     */
    public void setIdGita(ArrayList<Integer> idGita) {
        this.idGita = idGita;
    }

    /**
     * Metodo set
     * @param classe 
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    

    /**
     * Metodo che iscrive lo studente ad una gita
     * @param g gita
     * @return true se e' stato iscritto, false se non e' stato iscritto
     */
    public boolean iscrivi(Gita g){
        if(controlloIdGita(g.getId()))return false;
        idGita.add(g.getId());
        return true;
    }
    
    /**
     * Metodo che controlla se lo studente e' gia' iscritto ad una gita
     * @param idGita id della gita
     * @return true se e' gia' iscritto, false se non e' gia' iscritto
     */
    public boolean controlloIdGita(int idGita){
        for(int id : this.idGita){
            if(idGita == id)return true;
        }
        return false;
    }
    
    /**
     * Metodo per rimuovere una gita
     * @param idGita id della gita da rimuovere
     * @return true se e' stata riossa, false altrimenti
     */
    public boolean rimuoviGita(int idGita){
        if(!controlloIdGita(idGita))return false;
        this.idGita.remove(Integer.valueOf(idGita));
        return true;
    }
    
 
    
}
