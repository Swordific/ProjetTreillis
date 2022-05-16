/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author Asus
 */
public class Terrain {

    private Vecteur2D depart, arrivee;

    @Override
    public String toString() {
        String output = new String();
        output = "Départ : " + this.getDepart() + " ; Arrivée : " + this.getArrivee();
        return output;
    }

    public Terrain(double xD, double yD, double xA, double yA) {
        depart = new Vecteur2D(xD, yD);
        arrivee = new Vecteur2D(xA, yA);
    }

    public Terrain() {
        depart = new Vecteur2D(0, 0);
        arrivee = new Vecteur2D(100, 0);
    }

    public Vecteur2D getDepart() {
        return depart;
    }

    public void setDepart(Vecteur2D depart) {
        this.depart = depart;
    }

    public Vecteur2D getArrivee() {
        return arrivee;
    }

    public void setArrivee(Vecteur2D arrivee) {
        this.arrivee = arrivee;
    }

}
