/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import static java.lang.Math.atan;

/**
 *
 * @author antoi
 */
public class Barre {
    
    private int id;
    private Noeud noeudDepart, noeudArrivee;
    private double maxTrac, maxComp, cout;
/*
Définir la méthode noeudOppose, qui pour une barre b donnée, et un nœud n donné qui est soit le
nœud de départ de la barre, soit son nœud d’arrivée, renvoie l’autre nœud de la barre.
Définir la méthode angle, qui connaissant un nœud n de la barre (nœud de départ ou nœud d’arrivée)
calcule l’angle entre le vecteur (n, noeudOppose(n)) et le vecteur Ox (axe horizontal).
*/   
    
    public Barre(int id, Noeud noeudDepart, Noeud noeudArrivee, double maxTrac, double maxComp, double cout ) {
        this.id = id;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee ;
        this.cout = cout ;
        this.maxComp = maxComp ;
        this.maxTrac = maxTrac ;
                 
    }
    
      public Barre(Noeud noeudDepart, Noeud noeudArrivee, double maxTrac, double maxComp, double cout ) {
        this.id = -1;
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee ;
        this.cout = cout ;
        this.maxComp = maxComp ;
        this.maxTrac = maxTrac ;
      }
        
    
    
    public Noeud noeudOppose(Noeud n) {
        if (n == noeudDepart) {
            return noeudArrivee ;
        }
        else if (n == noeudArrivee) {
            return noeudDepart ; 
    }
        return null ;
    }
    
    //TODO
    public double angle() {
    double pyNoeudArrivee = noeudArrivee.getPy();
    double pxNoeudArrivee = noeudArrivee.getPx(); 
    double pyNoeudDepart = noeudDepart.getPy(); 
    double pxNoeudDepart = noeudDepart.getPx(); 
    pyNoeudArrivee = pyNoeudArrivee - pyNoeudDepart ; 
    pxNoeudArrivee = pxNoeudArrivee - pxNoeudDepart;
    double angle = atan(pyNoeudArrivee/pxNoeudArrivee);
    return angle ; 
    }
    
    
    @Override
    public String toString() {
          
        String output = new String() ; 
        output =  "Noeud de depart " + this.noeudDepart.toString() + " ; Noeud d'arrivée " + this.noeudArrivee.toString() + "; La traction maximale " + this.maxTrac + "; La compression maximale " + this.maxComp + "; Le coût de la barre " + this.cout ; 
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
    
    
}
