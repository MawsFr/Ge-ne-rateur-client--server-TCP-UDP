/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.generateurclasse;

/**
 *
 * @author Maws
 */
public class Instruction {
    
    protected String nom;
    protected String contenu;

    public Instruction(String nom, String contenu) {
        this.nom = nom;
        this.contenu = contenu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return contenu;
    }
    
    
    
}
