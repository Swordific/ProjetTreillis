/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

/**
 *
 * @author antoi
 */
public class Barre {
    @Expose
    private int id;
    @Expose
    private Noeud noeudDepart, noeudArrivee;
    @Expose
    private double maxTrac, maxComp, cout;
    @Expose
    private Vecteur2D f;
    @Expose
    private double trac;
    @Expose
    private String type;

    public Barre(int id, Noeud noeudDepart, Noeud noeudArrivee, double trac, double maxTrac, double maxComp, double cout) {
        this.id = id;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee;
        this.cout = cout;
        this.trac = trac;
        this.maxComp = maxComp;
        this.maxTrac = maxTrac;

        ArrayList<Barre> listeDepart = noeudDepart.getBarresDepart();
        listeDepart.add(this);
        ArrayList<Barre> listeArrivee = noeudArrivee.getBarresArrivee();
        listeArrivee.add(this);

        noeudDepart.setBarresDepart(listeDepart);
        noeudArrivee.setBarresArrivee(listeArrivee);
    }

    public Barre(int id, Noeud noeudDepart, Noeud noeudArrivee, double maxTrac, double maxComp, double cout) {
        this.id = id;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee;
        this.cout = cout;
        this.trac = 0;
        this.maxComp = maxComp;
        this.maxTrac = maxTrac;

        ArrayList<Barre> listeDepart = noeudDepart.getBarresDepart();
        listeDepart.add(this);
        ArrayList<Barre> listeArrivee = noeudArrivee.getBarresArrivee();
        listeArrivee.add(this);

        noeudDepart.setBarresDepart(listeDepart);
        noeudArrivee.setBarresArrivee(listeArrivee);
    }

    public Barre(Noeud noeudDepart, Noeud noeudArrivee, double maxTrac, double maxComp, double cout) {
        this.id = -1;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee;
        this.cout = cout;
        this.maxComp = maxComp;
        this.maxTrac = maxTrac;

        ArrayList<Barre> listeDepart = noeudDepart.getBarresDepart();
        listeDepart.add(this);
        ArrayList<Barre> listeArrivee = noeudArrivee.getBarresArrivee();
        listeArrivee.add(this);

        noeudDepart.setBarresDepart(listeDepart);
        noeudArrivee.setBarresArrivee(listeArrivee);
    }

    public Barre(int id, Noeud noeudDepart, Noeud noeudArrivee) {
        this.id = id;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee;

        ArrayList<Barre> listeDepart = noeudDepart.getBarresDepart();
        listeDepart.add(this);
        ArrayList<Barre> listeArrivee = noeudArrivee.getBarresArrivee();
        listeArrivee.add(this);

        noeudDepart.setBarresDepart(listeDepart);
        noeudArrivee.setBarresArrivee(listeArrivee);
    }

    public Barre(Noeud noeudDepart, Noeud noeudArrivee) {
        this.id = -1;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee;

        ArrayList<Barre> listeDepart = noeudDepart.getBarresDepart();
        listeDepart.add(this);
        ArrayList<Barre> listeArrivee = noeudArrivee.getBarresArrivee();
        listeArrivee.add(this);

        noeudDepart.setBarresDepart(listeDepart);
        noeudArrivee.setBarresArrivee(listeArrivee);
    }

    public Barre(int id, Noeud noeudDepart, Noeud noeudArrivee, String type) {
        if (type == "acier") {
            this.id = id;
            this.noeudDepart = noeudDepart;
            this.noeudArrivee = noeudArrivee;
            this.cout = 100;
            this.trac = 0;
            this.maxComp = -13000;
            this.maxTrac = 18000;

        }
        else if (type == "bois") {
            this.id = id;
            this.noeudDepart = noeudDepart;
            this.noeudArrivee = noeudArrivee;
            this.cout = 20;
            this.trac = 0;
            this.maxComp = -2000;
            this.maxTrac = 4500;
        }
        else if (type == "aluminium") {
            this.id = id;
            this.noeudDepart = noeudDepart;
            this.noeudArrivee = noeudArrivee;
            this.cout = 450;
            this.trac = 0;
            this.maxComp = -10000;
            this.maxTrac = 14000;
        }
        else {
            System.out.println("Le type de barre entrée n'est pas reconnu");
        }
    }

    public Noeud noeudOppose(Noeud n) {
        if (n == noeudDepart) {
            return noeudArrivee;
        } else if (n == noeudArrivee) {
            return noeudDepart;
        }
        return null;
    }

    public static double angleHoriz(double x_n, double y_n, double x_m, double y_m) {
        double x_nm = x_m - x_n;
        double y_nm = y_m - y_n;
        return StrictMath.atan2(y_nm, x_nm);
    }

    //Méthode pour donner l'angle entre une barre et le vecteur horizontal
    public double angle(Noeud n) {
        Noeud m = this.noeudOppose(n);

        double angle = (angleHoriz(n.getPx(), n.getPy(), m.getPx(), m.getPy()));
        //System.out.println("Il y a un angle de "+angle+"° entre la barre et l'axe horizontal.");
        return angle;
    }

    @Override
    public String toString() {
        String output = new String();
        output = "Départ : " + this.noeudDepart + " ; Arrivée : " + this.noeudArrivee + " ; Traction actuelle : " + this.trac + " ; Traction maximale : " + this.maxTrac + " ; Compression maximale : " + this.maxComp + " ; Coût au mètre : " + this.cout;
        return output;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the noeudDepart
     */
    public Noeud getNoeudDepart() {
        return noeudDepart;
    }

    /**
     * @param noeudDepart the noeudDepart to set
     */
    public void setNoeudDepart(Noeud noeudDepart) {
        this.noeudDepart = noeudDepart;
    }

    /**
     * @return the noeudArrivee
     */
    public Noeud getNoeudArrivee() {
        return noeudArrivee;
    }

    /**
     * @param noeudArrivee the noeudArrivee to set
     */
    public void setNoeudArrivee(Noeud noeudArrivee) {
        this.noeudArrivee = noeudArrivee;
    }

    /**
     * @return the maxTrac
     */
    public double getMaxTrac() {
        return maxTrac;
    }

    /**
     * @param maxTrac the maxTrac to set
     */
    public void setMaxTrac(double maxTrac) {
        this.maxTrac = maxTrac;
    }

    /**
     * @return the maxComp
     */
    public double getMaxComp() {
        return maxComp;
    }

    /**
     * @param maxComp the maxComp to set
     */
    public void setMaxComp(double maxComp) {
        this.maxComp = maxComp;
    }

    /**
     * @return the cout
     */
    public double getCout() {
        return cout;
    }

    /**
     * @param cout the cout to set
     */
    public void setCout(double cout) {
        this.cout = cout;
    }

    public Vecteur2D getF() {
        return f;
    }

    public void setF(Vecteur2D f) {
        this.f = f;
    }

    /**
     * @return the trac
     */
    public double getTrac() {
        return trac;
    }

    /**
     * @param trac the trac to set
     */
    public void setTrac(double trac) {
        this.trac = trac;
    }

}
