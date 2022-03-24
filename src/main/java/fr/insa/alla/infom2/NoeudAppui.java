package fr.insa.alla.infom2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hazem
 */
public class NoeudAppui extends Noeud {
    
    public NoeudAppui(int id, double px, double py, Vecteur2D f) {
        super(id, px, py, f);
    }

    public NoeudAppui(double px, double py, Vecteur2D f) {
        super(px, py, f);
    }

    public NoeudAppui(double px, double py) {
        super(px, py);
    }
    
}
