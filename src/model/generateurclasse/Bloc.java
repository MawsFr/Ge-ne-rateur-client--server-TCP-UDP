/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.generateurclasse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Maws
 */
public class Bloc extends Instruction {

    private List<Instruction> instructions;
    
    public Bloc(String nom, String titre) {
        super(nom, titre);
        this.instructions = new ArrayList<Instruction>();
        
        
    }
    
    public void ajouterInstruction(String nom, String contenu) {
        this.instructions.add(new Instruction(nom, contenu));
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
    
    
    public void vider() {
        this.instructions.clear();
    }

    public void ajouterInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    @Override
    public String toString() {
        String s = "\n";
        
        s+= "\t\t" + contenu + " {\n";
        
        for(Iterator<Instruction> it = instructions.iterator(); it.hasNext();) {
            s += "\t\t\t" + it.next() + "\n";
        }
        
        s += "\n\t\t}";
        
        return s;
    }
    
    
    
    
    
}
