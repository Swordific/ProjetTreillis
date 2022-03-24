/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2;

/**
 *
 * @author antoi
 */
public class NoeudAppuiSimple extends NoeudAppui {
    
    private int id;
    private double px, py;
    private Vecteur2D f;
    
    
    
@Override   //Voir si enlever

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the px
     */
    @Override
    public double getPx() {
        return px;
    }

    /**
     * @param px the px to set
     */
    @Override
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    @Override
    public double getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    @Override
    public void setPy(double py) {
        this.py = py;
    }

    /**
     * @return the f
     */
    @Override
    public Vecteur2D getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    @Override
    public void setF(Vecteur2D f) {
        this.f = f;
    }
    
     @Override
    public String toString() {
        String output = new String();
        return output;
    }
}