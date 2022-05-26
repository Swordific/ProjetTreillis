/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Farouk
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, UnsupportedEncodingException, TransformerException, IOException {

//        Treillis.menuTexte();
//        Numeroteur num = new Numeroteur();
//        num.addAll(667, "dfnoifdn", 994.01);
//        System.out.println(num.getIndex(667));
        //Treillis.menuTexte();
        //Numeroteur num = new Numeroteur();
        //num.addAll(667, "dfnoifdn", 994.01);
        //System.out.println(num.getIndex(667));
//
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

//        Vecteur2D f = new Vecteur2D(0, 1000);
//        Noeud n1 = new NoeudSimple(1, 0, 0, f);
//        Noeud n2 = new NoeudAppuiSimple(2, 2, 0, f);
//        Noeud n3 = new NoeudAppuiDouble(3, 1, 1, f);
//
//        Barre b1 = new Barre(n1, n2);
//        Barre b2 = new Barre(n2, n3);
//        Barre b3 = new Barre(n3, n1);

        Numeroteur numNoeuds = new Numeroteur();
        Numeroteur numBarres = new Numeroteur();

//        System.out.println(gson.toJson(new Barre(n1, n2)));
//
//        Treillis t = new Treillis();
//        t.ajouteNoeud(n1);
//        t.ajouteNoeud(n2);
//        t.ajouteNoeud(n3);
//        t.ajouteBarre(b1);
//        t.ajouteBarre(b2);
//        t.ajouteBarre(b3);
//        b1.setType("acier");
//        b2.setType("bois");
//        b3.setType("aluminium");
        //menuTexte(t);
        //Fichier.exportTreillis(t, "C:\\Users\\Asus\\Desktop\\Cours\\S2\\Info\\Projet\\ProjetTreillis\\ProjetTreillis\\src\\main\\resources\\testWrite.json");
        //System.out.println(n.getF());
//        Treillis treillis = Fichier.importTreillis("C:\\Users\\Asus\\Desktop\\Cours\\S2\\Info\\Projet\\ProjetTreillis\\ProjetTreillis\\src\\main\\resources\\testWrite.json");
//        System.out.println("Noeuds : ");
//        for (Noeud n : treillis.getNoeuds()) {
//            System.out.print(n + " ");
//        }
//        System.out.println("\r\nBarres : ");
//        for (Barre b : treillis.getBarres()) {
//            System.out.print(b + " ");
//        }
//        System.out.println("\r\n");

    }
}
