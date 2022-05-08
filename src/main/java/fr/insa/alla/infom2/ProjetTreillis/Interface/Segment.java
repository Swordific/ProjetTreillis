/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;

import fr.insa.alla.infom2.ProjetTreillis.Barre;
import fr.insa.alla.infom2.ProjetTreillis.Noeud;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Asus
 */
public class Segment {

    public static void tracerSegment(Barre b) {
        Noeud dep, arr;
        dep = b.getNoeudDepart();
        arr = b.getNoeudArrivee();

        Line l = new Line(dep.getPx(), dep.getPy(), arr.getPx(), arr.getPy());
        l.setStrokeWidth(3);
        l.setFill(Color.RED);

    }
}
