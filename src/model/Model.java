/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Maws
 */
public class Model extends Observable {
    
    private int modeUdpTcp;
    private int modeClientServeur;
    private ClasseServeurUDP serveurUDP;
    private ClasseServeurTCP serveurTCP;
    private ClasseClientUDP clientUDP;
    private ClasseClientTCP clientTCP;

    public Model() {
        initier();
    }

    private void initier() {
        this.serveurUDP = new ClasseServeurUDP("ServeurUDP");
        this.serveurTCP = new ClasseServeurTCP("ServeurTCP");
        this.clientUDP = new ClasseClientUDP("ClientUDP");
        this.clientTCP = new ClasseClientTCP("ClientTCP");
        
    }
    public ClasseServeurUDP getServeurUDP() {
        return serveurUDP;
    }

    public void setServeurUDP(ClasseServeurUDP serveurUDP) {
        this.serveurUDP = serveurUDP;
    }

    public ClasseServeurTCP getServeurTCP() {
        return serveurTCP;
    }

    public void setServeurTCP(ClasseServeurTCP serveurTCP) {
        this.serveurTCP = serveurTCP;
    }

    public ClasseClientUDP getClientUDP() {
        return clientUDP;
    }

    public void setClientUDP(ClasseClientUDP clientUDP) {
        this.clientUDP = clientUDP;
    }

    public ClasseClientTCP getClientTCP() {
        return clientTCP;
    }

    public void setClientTCP(ClasseClientTCP clientTCP) {
        this.clientTCP = clientTCP;
    }
    
    public void ajouterObserver(Observer o) {
        this.addObserver(o);
        this.clientTCP.addObserver(o);
        this.clientUDP.addObserver(o);
        this.serveurTCP.addObserver(o);
        this.serveurUDP.addObserver(o);
    }

    public void setMode(int selectedIndex, int selectedIndex0) {
        this.modeUdpTcp = selectedIndex;
        this.modeClientServeur = selectedIndex0;
    }
    
    public boolean isClientUDP(){
        return (this.modeUdpTcp == 1) && (this.modeClientServeur == 1);
    }

    public boolean isClientTCP(){
        return (this.modeUdpTcp == 0) && (this.modeClientServeur == 1);
    }
    
    public boolean isServerUDP(){
        return (this.modeUdpTcp == 1) && (this.modeClientServeur == 0);
    }
    
    public boolean isServerTCP(){
        return (this.modeUdpTcp == 0) && (this.modeClientServeur == 0);
    }
    
}
