/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

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

    public static Noeud entreeNoeud() {
        System.out.println("Quel type de noeud voulez-vous créer ? (simple(0), appui simple(1), appui double(2))");
        int type = Lire.i();
        System.out.println("Quel est l'id du noeud ? (-1 s'il n'en a pas)");
        int id = Lire.i();
        System.out.println("Quelle est l'abscisse de votre noeud (px) ?");
        double x = Lire.d();
        System.out.println("Quelle est l'ordonnée de votre noeud (py) ?");
        double y = Lire.d();
        System.out.println("Y a-t-il une force appliquée à votre noeud ? (o/n)");
        String f = Lire.S();
        double fx = 0, fy = 0;
        if (f.equals("o") || f.equals("O")) {
            System.out.println("Quelle est la force horizontale sur votre noeud (fx) ?");
            fx = Lire.d();
            System.out.println("Quelle est la force verticale de votre noeud (fy) ?");
            fy = Lire.d();
        }
        Vecteur2D F = new Vecteur2D(fx, fy);
        Noeud out = null;
        switch (type) {
            case 0:
                out = new NoeudSimple(id, x, y, F);
                break;
            case 1:
                out = new NoeudAppuiSimple(id, x, y, F);
                break;
            case 2:
                out = new NoeudAppuiDouble(id, x, y, F);
                break;

        }
        if (out != null) {
            return out;
        } else {
            return null;
        }
    }

}
