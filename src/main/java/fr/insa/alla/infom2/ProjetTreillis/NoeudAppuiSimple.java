/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author antoine
 */
public class NoeudAppuiSimple extends NoeudAppui {

    public NoeudAppuiSimple(int id, double px, double py, Vecteur2D f) {
        super(id, px, py, f);
    }

    public NoeudAppuiSimple(double px, double py, Vecteur2D f) {
        super(px, py, f);
    }

    public NoeudAppuiSimple(double px, double py) {
        super(px, py);
    }

    @Override
    public String toString() {
        String output = super.toString();
        output = "Noeud Appui Simple" + output;
        return output;
    }
}
