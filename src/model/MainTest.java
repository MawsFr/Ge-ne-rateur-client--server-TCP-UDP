/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.generateurclasse.Classe;

/**
 *
 * @author Maws
 */
public class MainTest {
    
    public static void main(String[] args) {
/*        Classe pokemon = new Classe("Pokemon");
        
        
        pokemon.ajouterAttribut("int", "pv");
        pokemon.ajouterAttribut("double", "mp");
        pokemon.ajouterAttribut("String", "nom");
        pokemon.ajouterAttribut("List<String>", "listeAttaques");
        
        pokemon.ajouterFonction("attaquer");
        pokemon.ajouterParametreAFonction("attaquer", "String", "nomPokemon");
        pokemon.ajouterParametreAFonction("attaquer", "int", "degats");
        
        pokemon.ajouterInstructionAFonction("attaquer", "enleverDegats", "pokemon.getPv() -= degats");
        
        
        
        System.out.println(pokemon);
        */
        
        ClasseUDP serveur = new ClasseServeurUDP("MawsUDP");
        serveur.setTourneEnBoucle(true);
        serveur.setPossedeMain(true);
        serveur.setPortASpecifier(true);
        serveur.setPortEnArgument(true);
        serveur.generer();
        System.out.println(serveur);
        serveur.setTourneEnBoucle(false);
        serveur.setPortEnArgument(false);
        serveur.generer();
        System.out.println(serveur);
        
        serveur.setPortASpecifier(false);
        serveur.generer();
        System.out.println(serveur);
        
    }
    
}
