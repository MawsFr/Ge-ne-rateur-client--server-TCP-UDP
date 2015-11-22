/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.generateurclasse.Classe;
import model.generateurclasse.FonctionMain;

/**
 *
 * @author Maws
 */
public class ClasseUDP extends Classe {

    protected boolean portASpecifier;
    
    protected String port;
    protected String taille;
    protected boolean portEnArgument;
    protected boolean tourneEnBoucle;
    protected boolean sysoutAvancement;
    protected boolean verificationNull;
    
    public ClasseUDP(String nom) {
        super(nom);
        
        port = "9876";
        taille = "1024";


        
    }

    protected void genererConstructeur() {
        
    }
    
    protected void genererAttributs() {
        this.ajouterAttribut("InetAddress", "ipDest");
        this.ajouterAttribut("int", "portDest");
        this.ajouterAttribut("DatagramSocket", "socket");
        this.ajouterAttribut("DatagramPacket", "messageRecu");
        this.ajouterAttribut("DatagramPacket", "messageEnvoye");
        this.ajouterAttribut("private", "final int", "taille", taille);
        this.ajouterAttribut("byte[]", "buffer");
        this.ajouterAttribut("String", "s");
    }
    
    protected void genererMethodes() {
        
        genererMain();
        
        
        
    }
    
    protected void genererMain() {
        if(possedeMain) {
            ajouterFonction(new FonctionMain());
        } else {
            retirerFonction("main");
        }
        
        
    }
   
    public void generer() {
        vider();
        genererConstructeur();
        genererAttributs();
        genererMethodes();
        
        notifier();
    }
    
    
    public boolean isPortASpecifier() {
        return portASpecifier;
    }

    public void setPortASpecifier(boolean portASpecifier) {
        this.portASpecifier = portASpecifier;
        notifier();
    }

    public boolean isPortEnArgument() {
        return portEnArgument;
    }

    public void setPortEnArgument(boolean portEnArgument) {
        this.portEnArgument = portEnArgument;
        notifier();
    }

    public boolean isTourneEnBoucle() {
        return tourneEnBoucle;
    }

    public void setTourneEnBoucle(boolean tourneEnBoucle) {
        this.tourneEnBoucle = tourneEnBoucle;
        notifier();
    }

    public boolean isSysoutAvancement() {
        return sysoutAvancement;
    }

    public void setSysoutAvancement(boolean sysoutAvancement) {
        this.sysoutAvancement = sysoutAvancement;
        notifier();
    }

    public boolean isVerificationNull() {
        return verificationNull;
    }

    public void setVerificationNull(boolean verificationNull) {
        this.verificationNull = verificationNull;
        notifier();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        String s = "import java.net.DatagramPacket;\n" +
                    "import java.net.DatagramSocket;\n" +
                    "import java.net.InetAddress;\n\n" + super.toString();
        return s;
    }
    
    

    
    
    
    
}
