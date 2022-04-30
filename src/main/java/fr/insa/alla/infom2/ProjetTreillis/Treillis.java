/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Treillis {
    private ArrayList<Noeud> noeuds;
    private ArrayList<Barre> barres;

    public int maxIdNoeud() {
        int maxid = 0;
        for (Noeud n : noeuds) {
            if (n.getId() > maxid) {
                maxid = n.getId();
            }
        }
        return maxid;
    }

    public int maxIdBarre() {
        int maxid = 0;
        for (Barre b : barres) {
            if (b.getId() > maxid) {
                maxid = b.getId();
            }
        }
        return maxid;
    }

    public String ajouteNoeud(Noeud n) {
        if (noeuds.contains(n)) {
            return "Noeud déjà dans la liste";
        }
        n.setId(this.maxIdNoeud());
        noeuds.add(n);
        return null;
    }


    public String ajouteBarre(Barre b) {
        if (barres.contains(b)) {
            return "Barre déjà dans la liste";
        }
        this.ajouteNoeud(b.getNoeudDepart());
        this.ajouteNoeud(b.getNoeudArrivee());
        b.setId(this.maxIdBarre());
        barres.add(b);
        return null;
    }
}
