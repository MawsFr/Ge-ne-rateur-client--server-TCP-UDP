/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package model;

import java.util.List;
import java.util.StringTokenizer;
import model.generateurclasse.Bloc;
import model.generateurclasse.Instruction;



/**
 *
 * @author Maws
 */


public class ClasseClientUDP extends ClasseUDP {
    
    private String ip;
    private boolean envoyerDesDonnees;
    private boolean donneesATaperAuClavier;
    private String motsDeFinDEnvoiEnBoucle;
    private boolean afficherReponse;
    
    
    
    
    
    public ClasseClientUDP(String nom) {
        super(nom) ;
        
        envoyerDesDonnees = false;
        donneesATaperAuClavier = true;
        tourneEnBoucle = false;
//        motsDeFinDEnvoiEnBoucle = new ArrayList<String>();
//        motsDeFinDEnvoiEnBoucle.add("fin");
//        motsDeFinDEnvoiEnBoucle.add("bye");
//        motsDeFinDEnvoiEnBoucle.add("aurevoir");
        
        
        
    }
    
    
    
    @Override
    protected void genererConstructeur() {
        
        
        if(portEnArgument) {
            constructeur.ajouterParametre("InetAddress", "ipServeurDest");
            constructeur.ajouterParametre("int", "portServeurDest");
            
            constructeur.ajouterInstruction("setIpServer", "this.ipDest = ipServeurDest;");
            constructeur.ajouterInstruction("setPortServer", "this.portDest = portServeurDest;");
            
        } else {
            constructeur.ajouterInstruction("setIpServer", "this.ipDest = " + ip + ";");
            constructeur.ajouterInstruction("setPortServer", "this.portDest = " + port + ";");
        }
        
        constructeur.ajouterInstruction("demarrageServeur", "socket = new DatagramSocket();");
        if(donneesATaperAuClavier) {
            constructeur.ajouterInstruction("initBufferredReader","stdIn = new BufferedReader(new InputStreamReader(System.in));");
        }
        
        
        if(envoyerDesDonnees && !donneesATaperAuClavier) {
            constructeur.ajouterParametre("String", "message");
            constructeur.ajouterInstruction("initSArg", "s=message;");
            
        } else {
            
            constructeur.ajouterInstruction("initS", "s = \"\";");
        }
    }
    
    @Override
    protected void genererAttributs() {
        super.genererAttributs();
        
        if(donneesATaperAuClavier) {
            ajouterAttribut("BufferedReader", "stdIn");
        }
        
    }
    
    @Override
    protected void genererMethodes() {
        super.genererMethodes();
        
        
        String s = "new " + nom + "(";
        if(possedeMain) {
            if(portEnArgument) {
                s+="InetAddress.getByName(args[0]), Integer.parseInt(args[1])";
                if(envoyerDesDonnees && !donneesATaperAuClavier) {
                    s+= ", args[2]";
                }
            } else if(envoyerDesDonnees && !donneesATaperAuClavier) {
                s+="args[0]";
            }
            
            s+= ").lancer();";
            getMethodes("main").ajouterInstruction("creationServeur", s);
            
        }
        
        
        
        
        
        ajouterFonction("lancer");
        
        
        Instruction lireEntree = null;
        
        if(donneesATaperAuClavier) {
            lireEntree = new Instruction("lireEntree", "s = stdIn.readLine();");
        }
        
        Instruction envoieRequete = new Instruction("envoieRequete" , "envoyerRequete();");
        Instruction attenteReponse = new Instruction("attendreReponse" , "attendreReponse();");
        Instruction affichageReponse = null;
        if(afficherReponse) {
            affichageReponse = new Instruction("affichage" , "affichageReponse();");
            
        }
        
        
        
        if(isTourneEnBoucle()) {
            
            
            String whileC = "";
            if(motsDeFinDEnvoiEnBoucle.equals("")) {
                whileC = "while(true)";
            } else {
                whileC = "while(";
                StringTokenizer tokenizer = new StringTokenizer(motsDeFinDEnvoiEnBoucle, " ");
                while(tokenizer.hasMoreTokens()) {
                    whileC += "!s.equals(\"" + tokenizer.nextToken()+"\")";
                    if(tokenizer.hasMoreTokens()) {
                        whileC += " || ";
                    }
                
                }
                
                whileC += ")";
            }
            
            
            this.ajouterUnBlockAFonction("lancer", "blocWhileTrue", whileC);
            Bloc blocWhile = (Bloc) getMethodes("lancer").getInstructions("blocWhileTrue");
            
            blocWhile.ajouterInstruction(lireEntree);
            blocWhile.ajouterInstruction(envoieRequete);
            blocWhile.ajouterInstruction(attenteReponse);
            if(afficherReponse) {
                blocWhile.ajouterInstruction(affichageReponse);
            }
            
        } else {
            
            if(donneesATaperAuClavier)
                ajouterInstructionAFonction("lancer", lireEntree.getNom(), lireEntree.getContenu());
            ajouterInstructionAFonction("lancer",envoieRequete.getNom() , envoieRequete.getContenu());
            ajouterInstructionAFonction("lancer",attenteReponse.getNom() , attenteReponse.getContenu());
            if(afficherReponse) {
                ajouterInstructionAFonction("lancer",affichageReponse.getNom() , affichageReponse.getContenu());
            }
            
            
            ajouterInstructionAFonction("lancer","close", "close();");
            
        }
        
        
        ajouterFonction("envoyerRequete");
        
        ajouterInstructionAFonction("envoyerRequete", "setBuffer" ,"buffer = s.getBytes();");
        ajouterInstructionAFonction("envoyerRequete", "setDataEnvoye", "messageEnvoye = new DatagramPacket(buffer, buffer.length, ipDest, portDest);");
        ajouterInstructionAFonction("envoyerRequete", "envoieRequete", "socket.send(messageEnvoye);");
        
        
        ajouterFonction("attendreReponse");
        
        ajouterInstructionAFonction("attendreReponse", "setBuffer", "this.buffer = new byte[taille];");
        ajouterInstructionAFonction("attendreReponse", "setDataRecu", "messageRecu = new DatagramPacket(buffer, buffer.length);");
        ajouterInstructionAFonction("attendreReponse", "reception", "socket.receive(messageRecu);");
        
        if(afficherReponse) {
            ajouterFonction("affichageReponse");
            
            ajouterInstructionAFonction("affichageReponse", "initS", "s = new String(messageRecu.getData(), 0, messageRecu.getLength());");
            ajouterInstructionAFonction("affichageReponse", "affcihageS", "System.out.println(s);");
        }
        
        
        if(!tourneEnBoucle) {
            ajouterFonction("close");
            
            
            
            ajouterInstructionAFonction("close", new Instruction("closeSocket", "socket.close();"));
            if(donneesATaperAuClavier) {
                ajouterInstructionAFonction("close", "closeBufferedReader", "stdIn.close();");
            }
        }
        
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public boolean isEnvoyerDesDonnees() {
        return envoyerDesDonnees;
    }
    
    public void setEnvoyerDesDonnees(boolean envoyerDesDonnees) {
        this.envoyerDesDonnees = envoyerDesDonnees;
    }
    
    public boolean isDonneesATaperAuClavier() {
        return donneesATaperAuClavier;
    }
    
    public void setDonneesATaperAuClavier(boolean donneesATaperAuClavier) {
        this.donneesATaperAuClavier = donneesATaperAuClavier;
    }
    
    public String getMotsDeFinDEnvoiEnBoucle() {
        return motsDeFinDEnvoiEnBoucle;
    }
    
    public void setMotsDeFinDEnvoiEnBoucle(String motsDeFinDEnvoiEnBoucle) {
        this.motsDeFinDEnvoiEnBoucle = motsDeFinDEnvoiEnBoucle;
    }
    
    public boolean isAfficherReponse() {
        return afficherReponse;
    }
    
    public void setAfficherReponse(boolean afficherReponse) {
        this.afficherReponse = afficherReponse;
    }
    
    @Override
    public String toString() {
        String s = super.toString();
        if(isDonneesATaperAuClavier())
            s = "import java.io.BufferedReader;\nimport java.io.InputStreamReader;\n" + s;
        return s;
    }
    
    
    
    
    
    
    
}
