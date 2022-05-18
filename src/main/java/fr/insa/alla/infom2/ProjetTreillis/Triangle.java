/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author hazem
 */
public class Triangle {
    
    
    private int id;
    private Point p1;
    private Point p2;
    private Point p3;
    private Segment s1;
    private Segment s2;
    private Segment s3;
    
    public Triangle (int id, Point p1, Point p2, Point p3) {
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.s1 = new Segment(p1,p2);
        this.s1 = new Segment(p2,p3);
        this.s1 = new Segment(p3,p1);
    }
    
    public int getid() {
        return id;
    }
    
    public void setid(int id) {
        this.id = id;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }
    
    

    
}
