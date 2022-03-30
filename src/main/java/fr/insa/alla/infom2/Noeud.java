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
    
    public static int nbrInconnues() {
        
        int nbrInconnues = 0;
        
        System.out.println("Quel type de noeud voulez vous creer ? (noeud simple : 0, appui simple : 1, appui double : 2)");
        int type = Lire.i();
        if (type == 0) {
            nbrInconnues = 0;
        }
        if (type == 1) {
            nbrInconnues = 1;
        }
        if (type == 2) {
            nbrInconnues = 2;
        }
        return nbrInconnues;
    }

    //TODO
    @Override
    public String toString() {
        String output = new String();
        return output;
    }

    //TODO
    public static Noeud entreeNoeud() {
        System.out.println("Quel type de noeud voulez-vous créer ? (simple(s), appui simple(as), appui double(ad))");
        String type = Lire.S();
        System.out.println("Quelle est l'abscisse de votre noeud (px) ?");
        double x = Lire.i();
        System.out.println("Quelle est l'ordonnée de votre noeud (py) ?");
        double y = Lire.i();
        System.out.println("Quelle est la force horizontale sur votre noeud (fx) ?");
        double fx = Lire.i();
        System.out.println("Quelle est la force verticale de votre noeud (fy) ?");
        double fy = Lire.i();
        
        return
    }

}
