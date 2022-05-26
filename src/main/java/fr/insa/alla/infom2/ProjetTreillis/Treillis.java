/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import Jama.Matrix;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

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
        //n.setId(this.maxIdNoeud());
        noeuds.add(n);
        return null;
    }

    public String ajouteNoeuds(Noeud... ns) {
        for (Noeud n : ns) {
            if (noeuds.contains(n)) {
                return "Noeud déjà dans la liste";
            }
            //n.setId(this.maxIdNoeud());
            noeuds.add(n);
        }
        return null;
    }

    public String ajouteNoeuds(Collection<Noeud> ns) {
        for (Noeud n : ns) {
            if (noeuds.contains(n)) {
                return "Noeud déjà dans la liste";
            }
            //n.setId(this.maxIdNoeud());
            noeuds.add(n);
        }
        return null;
    }


    public String ajouteBarre(Barre b) {
        if (barres.contains(b)) {
            return "Barre déjà dans la liste";
        }
        this.ajouteNoeud(b.getNoeudDepart());
        this.ajouteNoeud(b.getNoeudArrivee());
        //b.setId(this.maxIdBarre());
        barres.add(b);
        return null;
    }

    public String ajouteBarres(Barre... bs) {
        for (Barre b : bs) {
            if (barres.contains(b)) {
                return "Barre déjà dans la liste";
            }
            this.ajouteNoeud(b.getNoeudDepart());
            this.ajouteNoeud(b.getNoeudArrivee());
            //b.setId(this.maxIdBarre());
            barres.add(b);
        }
        return null;
    }

    public String ajouteBarres(Collection<Barre> bs) {
        for (Barre b : bs) {
            if (barres.contains(b)) {
                return "Barre déjà dans la liste";
            }
            this.ajouteNoeud(b.getNoeudDepart());
            this.ajouteNoeud(b.getNoeudArrivee());
            //b.setId(this.maxIdBarre());
            barres.add(b);
        }
        return null;
    }

    public Noeud choixNoeudTexte() {
        System.out.println("Choisissez le noeud voulu");
        for (int i = 0; i < noeuds.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(noeuds.get(i));
        }
        return noeuds.get(Lire.i() - 1);
    }

    public Noeud choixNoeudStr(String n, Numeroteur ns, Numeroteur as, Numeroteur ad) {
        String[] nArr = n.split(" ");
        for (String s : nArr) {
            if (s.charAt(0) == 'i' && s.charAt(1) == 'd') {
                String[] idStr = s.split("=");
                int id = Integer.parseInt(idStr[1]);
                if (ns.getObject(id) != null) {
                    return (Noeud) ns.getObject(id);
                } else if (as.getObject(id) != null) {
                    return (Noeud) as.getObject(id);
                } else if (ad.getObject(id) != null) {
                    return (Noeud) ad.getObject(id);
                }
            }
        }
        return null;
    }

    public Barre choixBarre() {
        System.out.println("Choisissez la barre voulue");
        for (int i = 0; i < barres.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(barres.get(i));
        }
        return barres.get(Lire.i() - 1);
    }

    public Matrix calculeTraction() {
        int nbrNoeudSimple = 0;
        int nbrNoeudAppuiSimple = 0;
        int nbrNoeudAppuiDouble = 0;
        for (Noeud n : this.noeuds) {
            int type = n.nbrInconnues();
            switch (type) {
                case 0:
                    nbrNoeudSimple++;
                    break;

                case 1:
                    nbrNoeudAppuiSimple++;
                    break;

                case 2:
                    nbrNoeudAppuiDouble++;
                    break;

            }

        }

        Matrix equa = new Matrix(2 * noeuds.size(), nbrNoeudAppuiSimple + nbrNoeudAppuiDouble + barres.size());
        Matrix secondMembre = new Matrix(2 * noeuds.size(), 1);

        ArrayList<Barre> listBarres = new ArrayList();
        int listR = 0;
        int i = 0;
        int k = 0;

        while (i <= 2 * noeuds.size() - 2) {
            for (Noeud n : this.noeuds) {

                for (Barre b : n.barresIncidentes()) {
                    int j = 0;
                    DecimalFormat angleF = new DecimalFormat("#.#########");
                    angleF.format(StrictMath.sin(b.angle(n)));
                    double angleSin = Double.parseDouble(angleF.format(StrictMath.sin(b.angle(n))));
                    double angleCos = Double.parseDouble(angleF.format(StrictMath.cos(b.angle(n))));
                    if (!listBarres.contains(b)) {

                        equa.set(i, listBarres.size(), angleCos);
                        equa.set(i + 1, listBarres.size(), angleSin);

                        listBarres.add(b);

                    } else if (listBarres.contains(b)) {
                        equa.set(i, listBarres.indexOf(b), angleCos);
                        equa.set(i + 1, listBarres.indexOf(b), angleSin);
                    }

                }

                secondMembre.set(i, 0, n.getF().getVx());
                secondMembre.set(i + 1, 0, n.getF().getVy());

                i += 2;
            }
        }
        i = 0;
        while (i <= 2 * noeuds.size() - 2) {
            for (Noeud n : this.noeuds) {
                int type = n.nbrInconnues();

                switch (type) {
                    case 1:

                        equa.set(i + 1, listBarres.size() + listR, 1.0);
                        listR = listR + 1;
                        break;
                    case 2:

                        equa.set(i + 1, listBarres.size() + listR, 1);
                        listR = listR + 1;
                        break;
                }
                i += 2;
            }
        }

        Matrix solution = equa.solve(secondMembre);

        int x = 0;
        for (Barre b : listBarres) {
            b.setTrac(solution.get(x, 0));
            x = x + 1;

        }

        //Matrix[] m = {equa, secondMembre};
        return solution;

    }

    public static void menuTexte(Treillis treillis) throws FileNotFoundException, XMLStreamException, UnsupportedEncodingException, TransformerException, IOException {

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
                    treillis.noeuds.remove(treillis.choixNoeudTexte());
                    break;
                case 4:
                    System.out.println("Noeud de départ :");
                    Noeud depart = treillis.choixNoeudTexte();
                    System.out.println("Noeud d'arrivée :");
                    Noeud arrivee = treillis.choixNoeudTexte();
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
                case 6:
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    // write XML to ByteArrayOutputStream
//                    Fichier.exportTreillis(treillis, out);
//
//                    // Java 10
//                    //String xml = out.toString(StandardCharsets.UTF_8);
//                    // standard way to convert byte array to String
//                    String xml = new String(out.toByteArray(), StandardCharsets.UTF_8);
//
//                    // System.out.println(formatXML(xml));
//                    String prettyPrintXML = Fichier.formatXML(xml);
//
//                    // print to console
//                    // System.out.println(prettyPrintXML);
//                    // Java 11 - write to file
//                    Files.write(Paths.get("C:/Users/ialla01/Desktop/zbeub.xml"), prettyPrintXML.getBytes(StandardCharsets.UTF_8));
//                    //Fichier.exportTreillis(treillis, new FileOutputStream("C:/Users/ialla01/Desktop/zbeub.xml"));
                    break;
                case 7:
                    System.out.println(Arrays.deepToString(treillis.calculeTraction().getArray()));
                    break;
            }
        }
    }
}
