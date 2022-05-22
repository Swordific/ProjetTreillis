/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Fichier {

    public static void exportTreillis(Treillis t, String filepath) throws IOException {

        Gson gson = new Gson();
        ArrayList<NoeudSimple> listNSimple = new ArrayList<>();
        ArrayList<NoeudAppuiSimple> listASimple = new ArrayList<>();
        ArrayList<NoeudAppuiDouble> listADouble = new ArrayList<>();
        for (Noeud n : t.getNoeuds()) {
            int type = n.nbrInconnues();
            switch (type) {
                case 0:
                    listNSimple.add((NoeudSimple) n);
                    break;
                case 1:
                    listASimple.add((NoeudAppuiSimple) n);
                    break;
                case 2:
                    listADouble.add((NoeudAppuiDouble) n);
                    break;
            }
        }
        //String barres = gson.toJson(t.getBarres());
        //String[] out = {noeuds, barres};
        Type typeNSimple = new TypeToken<ArrayList<NoeudSimple>>() {
        }.getType();
        Type typeASimple = new TypeToken<ArrayList<NoeudAppuiSimple>>() {
        }.getType();
        Type typeADouble = new TypeToken<ArrayList<NoeudAppuiDouble>>() {
        }.getType();
        String outNSimple = gson.toJson(listNSimple);
        String outASimple = gson.toJson(listASimple);
        String outADouble = gson.toJson(listADouble);
        try {
            File file = new File(filepath);
            PrintWriter pw = new PrintWriter(filepath, "UTF-8");
            pw.println(outNSimple);
            pw.println(outASimple);
            pw.println(outADouble);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
