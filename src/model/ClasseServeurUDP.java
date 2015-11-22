/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.generateurclasse.Bloc;
import model.generateurclasse.Instruction;

/**
 *
 * @author Maws
 */
public class ClasseServeurUDP extends ClasseUDP {

    
    public ClasseServeurUDP(String nom) {
        super(nom);
        
        //        portASpecifier = true;
        
    }

    @Override
    protected void genererConstructeur() {
        
        
        
        if(portASpecifier) {
            if(portEnArgument) {
                constructeur.ajouterParametre("int", "portDemarrage");
                constructeur.ajouterInstruction("setPortUDP", "this.portDemarrage = portDemarrage;");
            } else {
                constructeur.ajouterInstruction("setPortUDP", "this.portDemarrage = " + port + ";");
            }
            
            if(isSysoutAvancement()) {
                constructeur.ajouterInstruction("sysoutDemarrage", "System.out.println(\"Demarrage du serveur ...\");");
            }
            constructeur.ajouterInstruction("demarrageServeur", "socket = new DatagramSocket(portDemarrage);");
            
        } else {
            if(sysoutAvancement) {
                constructeur.ajouterInstruction("sysoutDemarrage", "System.out.println(\"Demarrage du serveur ...\");");
            }
                constructeur.ajouterInstruction("demarrageServeur", "socket = new DatagramSocket();");
        }
        
        if(sysoutAvancement) {
                constructeur.ajouterInstruction("sysoutDemarre", "System.out.println(\"Serveur demarre ...\");");
            }
        constructeur.ajouterInstruction("initS", "s = \"\";");
//        constructeur.ajouterInstruction("fonctionStart", "lancer();");
        
        
        
    }

    @Override
    protected void genererAttributs() {
        super.genererAttributs();
        if(portASpecifier) {
            ajouterAttribut("int", "portDemarrage");
        }
        
    }

    @Override
    protected void genererMethodes() {
        super.genererMethodes();
        if(possedeMain && portASpecifier && portEnArgument) {
            getMethodes("main").ajouterInstruction("creationServeur", "new " + nom + "(Integer.parseInt(args[0])).lancer();");
        } else if(possedeMain) {
            getMethodes("main").ajouterInstruction("creationServeur", "new " + nom + "().lancer();");
        }
        
        ajouterFonction("lancer");
        
        Instruction attendreRequete = new Instruction("attendreRequete" , "attendreRequete();");
        Instruction traitement = new Instruction("traitement" , "traitementRequete();");
        Instruction reponse = new Instruction("reponse" , "envoyerReponse();");
        
        if(isTourneEnBoucle()) {
            
            this.ajouterUnBlockAFonction("lancer", "blocWhileTrue", "while(true)");
            Bloc blocWhile = (Bloc) getMethodes("lancer").getInstructions("blocWhileTrue");
            
            blocWhile.ajouterInstruction(attendreRequete);
            blocWhile.ajouterInstruction(traitement);
            blocWhile.ajouterInstruction(reponse);
            
        } else {
            ajouterInstructionAFonction("lancer",attendreRequete.getNom() , attendreRequete.getContenu());
            ajouterInstructionAFonction("lancer",traitement.getNom() , traitement.getContenu());
            ajouterInstructionAFonction("lancer",reponse.getNom() , reponse.getContenu());
            
            ajouterInstructionAFonction("lancer","close", "close();");
            
        }
        
        //attendreRequete
        
        ajouterFonction("attendreRequete");
        
        ajouterInstructionAFonction("attendreRequete", new Instruction("initBuffer" , "buffer = new byte[taille];"));
        ajouterInstructionAFonction("attendreRequete", new Instruction("initMessageRecu" , "messageRecu = new DatagramPacket(buffer, buffer.length);"));
        if(sysoutAvancement) {
                ajouterInstructionAFonction("attendreRequete", "sysoutAttenteRequete", "System.out.println(\"En attente de requete ...\");");
        }
        ajouterInstructionAFonction("attendreRequete", new Instruction("recevoirData" , "socket.receive(messageRecu);"));
        if(sysoutAvancement) {
                ajouterInstructionAFonction("attendreRequete", "sysoutRequeteRecue", "System.out.println(\"Requete recue ...\");");
        }
        ajouterInstructionAFonction("attendreRequete", new Instruction("recupIp" , "this.ipDest = messageRecu.getAddress();"));
        ajouterInstructionAFonction("attendreRequete", new Instruction("recupPort" , "this.portDest = messageRecu.getPort();"));
        ajouterInstructionAFonction("attendreRequete", new Instruction("recupDonnees" , "s = new String(messageRecu.getData(), 0, messageRecu.getLength());"));
        
        
        //traitement
        //A CODER SOIT MEME !!
        
        ajouterFonction("traitementRequete");
        
        if(sysoutAvancement) {
                ajouterInstructionAFonction("traitementRequete", "sysoutTraitementRequete", "System.out.println(\"Traitement de la requete ...\");");
        }
        
        //reponse
        
        ajouterFonction("envoyerReponse");
       
        ajouterInstructionAFonction("envoyerReponse", new Instruction("recupDonnees" , "buffer = s.getBytes();"));
        ajouterInstructionAFonction("envoyerReponse", new Instruction("initMessageEnvoye" , "messageEnvoye = new DatagramPacket(buffer, buffer.length, ipDest, portDest);"));
        if(sysoutAvancement) {
                ajouterInstructionAFonction("envoyerReponse", "sysoutEnvoieReponse", "System.out.println(\"Envoi de la reponse ...\");");
        }
        ajouterInstructionAFonction("envoyerReponse", new Instruction("envoieData" , "socket.send(messageEnvoye);"));
        if(sysoutAvancement) {
                ajouterInstructionAFonction("envoyerReponse", "sysoutReponseEnvoye", "System.out.println(\"Reponse envoyee ...\");");
        }
        
        //close ?
        
        if(!tourneEnBoucle) {
            ajouterFonction("close");
            
            
            if(sysoutAvancement) {
                ajouterInstructionAFonction("close", "sysoutFermeture", "System.out.println(\"Fermeture de la connexion ...\");");
            }
            ajouterInstructionAFonction("close", new Instruction("closeSocket", "socket.close();"));
            if(sysoutAvancement) {
                ajouterInstructionAFonction("close", "sysoutConnexionFermee", "System.out.println(\"Connexion fermee ...\");");
            }
        }
        
        
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
    
}
