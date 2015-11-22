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
public class Attribut extends Parametre{
    
    private String visibilite;
    
    private String valeur;

    public Attribut(String visibilite, String type, String nom, String valeur) {
        super(type, nom);
        this.visibilite = visibilite;
        this.valeur = valeur;
    }

    public String getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(String visibilite) {
        this.visibilite = visibilite;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        String s = "\t" + visibilite + " " + type + " " + nom;
        if(!this.valeur.isEmpty()) {
            s += " = " + valeur;
        }
        return s;
        
    }
    
    
    
    
    
}
