/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

/**
 *
 * @author Farouk
 */
public class Test {

    public static void main(String[] args) {
        //System.out.println(Noeud.entreeNoeud().toString());

        //Vecteur2D Force = new Vecteur2D(5, 7) ; 
        //System.out.println(Force.toString());
        Noeud a = new NoeudSimple(0, 0);
        Noeud b = new NoeudSimple(1, 1);
        Barre g = new Barre(a, b, 0, 0, 0);

        System.out.println(g.noeudOppose(a).toString());

    }

}
