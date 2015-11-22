/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package model.generateurclasse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maws
 */
public class Fonction {
    
    private String visibilite;
    private String type;
    private String nom;
    private List<Parametre> parametres;
    
    private List<Instruction> instructions;
    
    public Fonction(String visibilite, String type, String nom, List<Parametre> parametres, List<Instruction> instructions) {
        this.visibilite = visibilite;
        this.type = type;
        this.nom = nom;
        if(parametres != null) {
            this.parametres = parametres;
        } else {
            this.parametres = new ArrayList<Parametre>();
        }
        if(instructions != null) {
            this.instructions = instructions;
        } else {
            this.instructions = new ArrayList<Instruction>();
        }
    }
    
    public String getVisibilite() {
        return visibilite;
    }
    
    public void setVisibilite(String visibilite) {
        this.visibilite = visibilite;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public List<Parametre> getParametres() {
        return parametres;
    }
    
    public void setParametres(List<Parametre> parametres) {
        this.parametres = parametres;
    }
    
//    public Instruction getInstruction(String nom) {
//        if(instructions.containsKey(nom)) {
//            return instructions.get(nom);
//        }
//
//        return null;
//    }
    
    public void setContenu(Instruction i) {
        this.instructions.add(i);
    }
    
    public void ajouterParametre(Parametre p) {
        this.parametres.add(p);
    }
    
    public void ajouterParametre(String type, String nom) {
        this.parametres.add(new Parametre(type, nom));
    }
    
    public void retirerParametre(String nom) {
        for(Iterator<Parametre> it = parametres.iterator(); it.hasNext();) {
            Parametre p = it.next();
            if(p.getNom().equals(nom)) {
                retirerParametre(p);
            }
        }
    }
    
    public void retirerParametre(Parametre p) {
        this.parametres.remove(p);
    }
    
    @Override
    public String toString() {
        String s = "\n";
        
        s+= "\t" + visibilite + " " +  type +  " " + nom +"(";
        for(Iterator<Parametre> it = parametres.iterator(); it.hasNext();) {
            s+=it.next();
            if(it.hasNext()) {
                s+=", ";
            }
        }
        
        s+= ") {\n";
        
        for(Iterator<Instruction> it = instructions.iterator(); it.hasNext();) {
            s += "\t\t" + it.next() + "\n";
        }
        
        s += "\n\t}";
        
        return s;
    }
    
    public void ajouterInstruction(String nom, String contenu) {
        ajouterInstruction(new Instruction(nom, contenu));
    }
    
    public void retirerInstruction(String nom) {
        Instruction i = getInstructions(nom);
        if(i != null) {
            this.instructions.remove(i);
        }
    }
    
    public Instruction getInstructions(String nom) {
        for(Iterator<Instruction> it = instructions.iterator(); it.hasNext();) {
            Instruction i = it.next();
            if(i.getNom().equals(nom)) {
                return i;
            }
            
        }
        
        return null;
    }
    
    public void viderInstruction() {
        this.instructions.clear();
    }
    
    public void viderParametres() {
        this.parametres.clear();
    }
    
    public void vider() {
        viderInstruction();
        viderParametres();
    }
    
    void ajouterInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }
}
