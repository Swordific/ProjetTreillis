/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2;

/**
 *
 * @author Ilyas ALLA
 */

public abstract class Noeud {
    protected int id;
    protected double px, py;
    protected Vecteur2D f;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public Vecteur2D getF() {
        return f;
    }

    public void setF(Vecteur2D f) {
        this.f = f;
    }

    public Noeud(int id, double px, double py, Vecteur2D f) {
        this.id = id;
        this.px = px;
        this.py = py;
        this.f = f;
    }

    public Noeud(double px, double py, Vecteur2D f) {
        this.id = -1;
        this.px = px;
        this.py = py;
        this.f = f;
    }

    public Noeud(double px, double py) {
        this.id = -1;
        this.px = px;
        this.py = py;
        this.f = new Vecteur2D();
    }

    //TODO
    @Override
    public String toString() {
        String output = new String();
        return output;
    }

    //TODO
    public void entreeNoeud() {
        System.out.println("Quelle est la position horizontale de votre noeud (px)?");
        double px = Lire.i();
        System.out.println("Quelle est la position verticale de votre noeud (py)?");
        double py = Lire.i();
        System.out.println("Quelle est la force horizontale sur votre noeud (vx)?");
        double vx = Lire.i();
        System.out.println("Quelle est la force verticale de votre noeud (vy)?");
        double vy = Lire.i();
        
        Vecteur2D Force = new Vecteur2D(vx,vy);
        NoeudSimple NoeudTest = new NoeudSimple(id, px, py, Force);
    }

}
