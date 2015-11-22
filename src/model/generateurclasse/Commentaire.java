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
public class Commentaire extends Instruction{

    public Commentaire(String nom, String contenu) {
        super(nom, contenu);
    }
    
    @Override
    public String toString() {
        return "//" + contenu;
    }
    
    
    
}
