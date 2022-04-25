/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author antoine
 */
public class NoeudAppuiDouble extends NoeudAppui {
        
    public NoeudAppuiDouble(int id, double px, double py, Vecteur2D f) {
        super(id, px, py, f);
    }

    public NoeudAppuiDouble(double px, double py, Vecteur2D f) {
        super(px, py, f);
    }

    public NoeudAppuiDouble(double px, double py) {
        super(px, py);
    }
    
    @Override
    public String toString() {
        String output = new String();
        return output;
    }
}