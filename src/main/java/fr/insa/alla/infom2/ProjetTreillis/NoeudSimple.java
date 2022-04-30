/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author Farouk
 */
public class NoeudSimple extends Noeud {

    public NoeudSimple(int id, double px, double py, Vecteur2D f) {
        super(id, px, py, f);
    }

    public NoeudSimple(double px, double py, Vecteur2D f) {
        super(px, py, f);
    }

    public NoeudSimple(double px, double py) {
        super(px, py);
    }

    @Override
    public String toString() {
        String output = super.toString();
        output = "Noeud Simple" + output;
        return output;
    }

    /**
     * @return the id
     */
    @Override
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
}
