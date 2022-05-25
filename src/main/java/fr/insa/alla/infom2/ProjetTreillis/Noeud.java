/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

/**
 *
 * @author Ilyas ALLA
 */
//TODO creer methodes pour ajouter et retirer des barres dans les listes
public abstract class Noeud {

    @Expose
    protected int id;
    @Expose
    protected double px, py;
    @Expose
    protected Vecteur2D f;
    protected ArrayList<Barre> barresDepart;
    protected ArrayList<Barre> barresArrivee;

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

    /**
     * @return the barresDepart
     */
    public ArrayList<Barre> getBarresDepart() {
        return barresDepart;
    }

    /**
     * @param barresDepart the barresDepart to set
     */
    public void setBarresDepart(ArrayList<Barre> barresDepart) {
        this.barresDepart = barresDepart;
    }

    /**
     * @return the barresArrivee
     */
    public ArrayList<Barre> getBarresArrivee() {
        return barresArrivee;
    }

    /**
     * @param barresArrivee the barresArrivee to set
     */
    public void setBarresArrivee(ArrayList<Barre> barresArrivee) {
        this.barresArrivee = barresArrivee;
    }

    public Noeud(int id, double px, double py, Vecteur2D f) {
        this.id = id;
        this.px = px;
        this.py = py;
        this.f = f;
        this.barresArrivee = new ArrayList<Barre>();
        this.barresDepart = new ArrayList<Barre>();
    }

    public Noeud(double px, double py, Vecteur2D f) {
        this.id = -1;
        this.px = px;
        this.py = py;
        this.f = f;
        this.barresArrivee = new ArrayList<Barre>();
        this.barresDepart = new ArrayList<Barre>();
    }

    public Noeud(double px, double py) {
        this.id = -1;
        this.px = px;
        this.py = py;
        this.f = new Vecteur2D();
        this.barresArrivee = new ArrayList<Barre>();
        this.barresDepart = new ArrayList<Barre>();
    }

    @Override
    public String toString() {
        String output = "( id=" + id + " ) (" + this.px + " ; " + this.py + ") ; F = " + this.f;
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
        String f = Lire.S().toLowerCase();
        double fx = 0, fy = 0;
        if (f.equals("o")) {
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
        return out;
    }

    public int nbrInconnues() {
        int nbr = 0;
        String type = this.getClass().toString();
        switch (type) {
            case "class fr.insa.alla.infom2.ProjetTreillis.NoeudSimple":
                return 0;
            case "class fr.insa.alla.infom2.ProjetTreillis.NoeudAppuiSimple":
                return 1;
            case "class fr.insa.alla.infom2.ProjetTreillis.NoeudAppuiDouble":
                return 2;
        }
        return -1;
    }

    public ArrayList<Barre> barresIncidentes() {
        ArrayList<Barre> list = new ArrayList();
        list.addAll(barresDepart);
        list.addAll(barresArrivee);
        return list;
    }

    public void ajouterBarreDepart(Barre b) {
        this.barresDepart.add(b);
    }

    public void ajouterBarreArrivee(Barre b) {
        this.barresDepart.add(b);
    }


}
