/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;
import  fr.insa.alla.infom2.ProjetTreillis.Treillis.*;
import static fr.insa.alla.infom2.ProjetTreillis.Treillis.menuTexte;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.lang.Math.*;

import static fr.insa.alla.infom2.ProjetTreillis.Treillis.menuTexte;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.lang.Math.*;
import static java.lang.Math.*;

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
        Vecteur2D f = new Vecteur2D(0.0, 1000);
        Vecteur2D f0 = new Vecteur2D(0,0);
        Noeud n1 = new NoeudAppuiSimple(1, 0, 0,f0);
        Noeud n2 = new NoeudAppuiDouble(2, 2, 0,f0);
        Noeud n3 = new NoeudSimple(3, 1, 1, f);
                
        Barre b1 = new Barre(n1, n2);
        Barre b2 = new Barre(n2, n3);
        Barre b3 = new Barre(n3, n1);
//      
        //System.out.println(b1.angle(n1));

        //System.out.println(cos(0));
        //System.out.println(sin(Math.toRadians(0)));
        Treillis t = new Treillis();
        t.ajouteNoeud(n2);
        t.ajouteNoeud(n1);
        t.ajouteNoeud(n3);
        t.ajouteBarre(b1);
        t.ajouteBarre(b2);
        t.ajouteBarre(b3);
        menuTexte(t);
    


      
                
                
        
        
    }
}
