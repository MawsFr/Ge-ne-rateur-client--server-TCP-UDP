/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package model.generateurclasse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 *
 * @author Maws
 */
public class Classe extends Observable {
    
    protected String nom;
    protected boolean possedeMain;
    protected HashMap<String, Attribut> attributs;
    
    protected Fonction constructeur;
    protected Map<String, Fonction> methodes;
    
    public Classe(String nom) {
        
        this.nom = nom;
        
        attributs = new HashMap<String, Attribut>();
        methodes = new HashMap<String, Fonction>();
        constructeur = new Fonction("public", "", nom, null, null);
        
        possedeMain = true;
        
    }
    
    public void ajouterAttribut(String type, String nom) {
        ajouterAttribut("private", type, nom, "");
    }
    
    public void ajouterAttribut(String visibilite, String type, String nom, String valeur) {
        this.attributs.put(nom, new Attribut(visibilite, type, nom, valeur));
        
    }
    
    public void retirerAttribut(String nom) {
        if(attributs.containsKey(nom)) {
            attributs.remove(nom);
        }
    }
    
    public void retirerFonction(String nom) {
        if(methodes.containsKey(nom)) {
            methodes.remove(nom);
        }
    }
    
    
    public void ajouterFonction(Fonction f) {
        if(!this.methodes.containsKey(f.getNom())) {
            this.methodes.put(f.getNom(), f);
        }
        
    }
    public void ajouterFonction(String visibilite, String type, String nom, List<Parametre> parametres, List<Instruction> instructions) {
        ajouterFonction(new Fonction(visibilite, type, nom, parametres, instructions));
    }
    
    public void ajouterFonction(String visibilite, String type, String nom, List<Instruction> instructions) {
        ajouterFonction(new Fonction(visibilite, type, nom, null, instructions));
    }
    
    public void ajouterFonction(String type, String nom, List<Instruction> instructions) {
        ajouterFonction(new Fonction("public", type, nom, null, instructions));
    }
    
    public void ajouterFonction(String nom, List<Instruction> instructions) {
        ajouterFonction(new Fonction("public", "void", nom, null, instructions));
    }
    
    public void ajouterFonction(String visibilite, String type, String nom) {
        ajouterFonction(new Fonction(visibilite, type, nom, null, null));
    }
    
    public void ajouterFonction(String type, String nom) {
        ajouterFonction(new Fonction("public", type, nom, null, null));
    }
    
    public void ajouterFonction(String nom) {
        ajouterFonction(new Fonction("public", "void", nom, null, null));
    }
    
    public void viderAttributs() {
        this.attributs.clear();
    }
    
    public void viderFonctions() {
        for(Map.Entry<String, Fonction> entry : methodes.entrySet()) {
            entry.getValue().vider();
        }
        this.methodes.clear();
    }
    
    public void vider() {
        viderAttributs();
        viderFonctions();
        constructeur = new Fonction("public", "", nom, null, null);
    }

    public String constructeurToString() {
        
        return "\n" + constructeur.toString();
    }
    
    public String attributsToString() {
        String s = "\n";
        
        for(Map.Entry<String, Attribut> entry : attributs.entrySet()) {
            s += entry.getValue() + ";\n";
        }
        
        return s;
        
    }
    
    public String methodesToString() {
        String s = "\n";
        
        for(Map.Entry<String, Fonction> entry : methodes.entrySet()) {
            s += entry.getValue() + "\n";
        }
        s += "\n";
        
        
        return s;
                
    }
    @Override
    public String toString() {
        String s = "";
        
        s+="public class " + nom + " {\n";
        
        s+=attributsToString();
        s+=constructeurToString();
        s+=methodesToString();
        
        s+="}";
        return s;
    }

    public void ajouterParametreAFonction(String nomFonction, String typeParam, String nomParam) {
        if(this.methodes.containsKey(nomFonction)) {
            getMethodes(nomFonction).ajouterParametre(typeParam, nomParam);
            
        }
        
    }
    
    public void ajouterInstructionAFonction(String nomFonction, String nomInstruc, String contenuInstruc) {
        if(this.methodes.containsKey(nomFonction)) {
            getMethodes(nomFonction).ajouterInstruction(nomInstruc, contenuInstruc);
            
        }
        
        
    }
    
    public void ajouterInstructionAFonction(String nomFonction, Instruction i) {
        if(this.methodes.containsKey(nomFonction)) {
            getMethodes(nomFonction).ajouterInstruction(i);
            
        }
        
        
    }
    
    public void ajouterUnBlockAFonction(String nomFonction, String nomBloc, String titre) {
        ajouterInstructionAFonction(nomFonction, new Bloc(nomBloc, titre));
        
        
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashMap<String, Attribut> getAttributs() {
        return attributs;
    }

    public void setAttributs(HashMap<String, Attribut> attributs) {
        this.attributs = attributs;
    }

    public Fonction getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(Fonction constructeur) {
        this.constructeur = constructeur;
    }

    public Fonction getMethodes(String nom) {
        if(methodes.containsKey(nom)) {
            return methodes.get(nom);
        }
        return null;
    }

    public void setMethodes(Map<String, Fonction> methodes) {
        this.methodes = methodes;
    }

    public boolean isPossedeMain() {
        return possedeMain;
    }

    public void setPossedeMain(boolean possedeMain) {
        this.possedeMain = possedeMain;
    }
    
    
    
    protected void notifier() {
        setChanged();
        notifyObservers();
    }
    
    
    
}
