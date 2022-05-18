/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author Farouk
 */
public class Segment {
    
    private Point debut;
    private Point fin;

    public Segment(Point debut, Point fin) {
        this.debut = debut;
        this.fin = fin;
    }
        
    public Point getDebut() {
        return debut;
    }

    public Point getFin() {
        return fin;
    }

    public void setDebut(Point debut) {
        this.debut = debut;
    }

    public void setFin(Point fin) {
        this.fin = fin;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.debut + "," + this.fin + ']';
    }

     public static Segment demandeSegment() {
        System.out.println("point début : ");
        Point deb = Point.demandePoint();
        System.out.println("point fin : ");
        Point fin = Point.demandePoint();
        return new Segment(deb, fin);
    }
    
}
