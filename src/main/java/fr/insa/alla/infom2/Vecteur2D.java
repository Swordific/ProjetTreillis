/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2;

/**
 *
 * @author Ilyas ALLA
 */
public class Vecteur2D {

    private double vx, vy;

    public double getVx() {
        return this.vx;
    }

    public double getVy() {
        return this.vy;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public Vecteur2D(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public Vecteur2D() {
        this.vx = 0;
        this.vy = 0;
    }

    @Override
    public String toString() {
        String output = "(" + this.vx + " ; " + this.vy + ")";
        return output;
    }
    
}
