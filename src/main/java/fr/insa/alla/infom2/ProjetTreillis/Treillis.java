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

    public Treillis() {
        noeuds = new ArrayList<Noeud>();
        barres = new ArrayList<Barre>();
    }

    public Treillis(ArrayList<Noeud> noeuds, ArrayList<Barre> barres) {
        this.noeuds = new ArrayList<Noeud>();
        this.barres = new ArrayList<Barre>();
    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public ArrayList<Barre> getBarres() {
        return barres;
    }

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

    public Noeud choixNoeud() {
        System.out.println("Choisissez le noeud voulu");
        for (int i = 0; i < noeuds.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(noeuds.get(i));
        }
        return noeuds.get(Lire.i() - 1);
    }

    public Barre choixBarre() {
        System.out.println("Choisissez la barre voulue");
        for (int i = 0; i < barres.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(barres.get(i));
        }
        return barres.get(Lire.i() - 1);
    }

    public static void menuTexte() {
        Treillis treillis = new Treillis();

        while (true) {
            System.out.println("Que voulez-vous faire ?\r\n1) Afficher le treillis\r\n2) Créer un noeud\r\n3) Supprimer un noeud\r\n4) Créer une barre\r\n5) Supprimer une barre");
            int in = Lire.i();
            switch (in) {
                case 1:
                    System.out.println("Noeuds : ");
                    for (Noeud n : treillis.noeuds) {
                        System.out.print(n + " ");
                    }
                    System.out.println("\r\nBarres : ");
                    for (Barre b : treillis.barres) {
                        System.out.print(b + " ");
                    }
                    System.out.println("\r\n");
                    break;

                case 2:
                    treillis.noeuds.add(Noeud.entreeNoeud());
                    break;
                case 3:
                    treillis.noeuds.remove(treillis.choixNoeud());
                    break;
                case 4:
                    System.out.println("Noeud de départ :");
                    Noeud depart = treillis.choixNoeud();
                    System.out.println("Noeud d'arrivée :");
                    Noeud arrivee = treillis.choixNoeud();
                    System.out.println("Voulez-vous entrer des caractéristiques supplémentaires ? (o/n)");
                    if (Lire.S().toLowerCase().equals("o")) {
                        System.out.println("Entrez la traction maximale supportée");
                        double maxTrac = Lire.d();
                        System.out.println("Entrez la compression maximale supportée");
                        double maxComp = Lire.d();
                        System.out.println("Entrez le coût au mètre");
                        double cout = Lire.d();

                        treillis.barres.add(new Barre(depart, arrivee, maxTrac, maxComp, cout));
                    } else {
                        treillis.barres.add(new Barre(depart, arrivee));
                    }
                    break;
                case 5:
                    treillis.barres.remove(treillis.choixBarre());
                    break;
            }
        }
    }
}
