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
public class FonctionMain extends Fonction{

    public FonctionMain() {
        super("public static", "void", "main", null, null);
        
        this.ajouterParametre(new Parametre("String[]", "args"));
    }
    
    
    
}
