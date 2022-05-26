/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Fichier {

//    public static void main(String[] args) {
//        JsonSerializer<Barre> barreSerializer = new JsonSerializer<Barre>() {
//            @Override
//            public JsonElement serialize(Barre src, Type typeOfSrc, JsonSerializationContext context) {
//                JsonObject jsonBarre = new JsonObject();
//                Gson gson = new Gson();
////                Noeud nd = src.getNoeudDepart();
////                Noeud na = src.getNoeudArrivee();
////
////                int typeNa = na.nbrInconnues();
////                int typeNd = nd.nbrInconnues();
////
////                jsonBarre.addProperty("Id", src.getId());
////
////                switch (typeNa) {
////                    case 0:
////                        jsonBarre.addProperty("NoeudDepart", gson.toJson((NoeudSimple) src.getNoeudDepart()));
////                        break;
////                    case 1:
////                        jsonBarre.addProperty("NoeudDepart", gson.toJson((NoeudAppuiSimple) src.getNoeudDepart()));
////                        break;
////                    case 2:
////                        jsonBarre.addProperty("NoeudDepart", gson.toJson((NoeudAppuiDouble) src.getNoeudDepart()));
////                        break;
////                }
////                switch (typeNd) {
////                    case 0:
////                        jsonBarre.addProperty("NoeudArrivee", gson.toJson((NoeudSimple) src.getNoeudDepart()));
////                        break;
////                    case 1:
////                        jsonBarre.addProperty("NoeudArrivee", gson.toJson((NoeudAppuiSimple) src.getNoeudDepart()));
////                        break;
////                    case 2:
////                        jsonBarre.addProperty("NoeudArrivee", gson.toJson((NoeudAppuiDouble) src.getNoeudDepart()));
////                        break;
////                }
//
//                jsonBarre.addProperty("MaxTrac", src.getMaxTrac());
//                jsonBarre.addProperty("MaxComp", src.getMaxComp());
//                jsonBarre.addProperty("Cout", src.getCout());
//                jsonBarre.addProperty("F", gson.toJson(src.getF()));
//                jsonBarre.addProperty("Trac", src.getTrac());
//
//                return jsonBarre;
//            }
//        };
//
////        JsonDeserializer<Barre> barreDeserializer = new JsonDeserializer<Barre>() {
////            @Override
////            public Barre deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
////                JsonObject jsonObject = json.getAsJsonObject();
////                Gson gson = new Gson();
////
////                Barre barre = new Barre(
////                        jsonObject.get("Id").getAsInt(),
////                        gson.fromJson(jsonObject.get("NoeudDepart"), Noeud.class),
////                        gson.fromJson(jsonObject.get("NoeudArrivee"), Noeud.class),
////                        jsonObject.get("Trac").getAsDouble(),
////                        jsonObject.get("MaxTrac").getAsDouble(),
////                        jsonObject.get("MaxComp").getAsDouble(),
////                        jsonObject.get("Cout").getAsDouble()
////                );
////
////                return barre;
////            }
////        };
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Barre.class, barreSerializer);
//        //gsonBuilder.registerTypeAdapter(Barre.class, barreDeserializer);
//        Gson gson = gsonBuilder.create();
//        //System.out.println(gson.toJson(new Barre(new NoeudSimple(0, 0), new NoeudSimple(1, 1))));
//        System.out.println(gson.toJson(new NoeudSimple(0, 0)));
//    }
    public static void exportTreillis(Treillis t, String filepath) throws IOException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        ArrayList<NoeudSimple> listNSimple = new ArrayList<>();
        ArrayList<NoeudAppuiSimple> listASimple = new ArrayList<>();
        ArrayList<NoeudAppuiDouble> listADouble = new ArrayList<>();
        ArrayList<Barre> listBarre = new ArrayList<>(t.getBarres());
        //ArrayList<Barre> listBarre = new ArrayList<>();
        //listBarre.add(new Barre(null, null));

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

        String outNSimple = gson.toJson(listNSimple);
        String outASimple = gson.toJson(listASimple);
        String outADouble = gson.toJson(listADouble);

        try {
            PrintWriter pw = new PrintWriter(filepath, "UTF-8");
            pw.println(outNSimple);
            pw.println(outASimple);
            pw.println(outADouble);
            pw.println("Barres");
            for (Barre b : listBarre) {
                pw.println(gson.toJson(b));
            }
            pw.println("/Barres");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Treillis importTreillis(String filepath) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Treillis treillis = new Treillis();

        Type typeNSimple = new TypeToken<ArrayList<NoeudSimple>>() {
        }.getType();
        Type typeASimple = new TypeToken<ArrayList<NoeudAppuiSimple>>() {
        }.getType();
        Type typeADouble = new TypeToken<ArrayList<NoeudAppuiDouble>>() {
        }.getType();
        Type typeBarre = new TypeToken<ArrayList<Barre>>() {
        }.getType();

        ArrayList<NoeudSimple> listNSimple;
        ArrayList<NoeudAppuiSimple> listASimple;
        ArrayList<NoeudAppuiDouble> listADouble;
        ArrayList<Barre> listBarre = new ArrayList<Barre>();
        Numeroteur numNoeuds = new Numeroteur();
        Numeroteur numBarresJson = new Numeroteur();
        Numeroteur numBarres = new Numeroteur();

        try {
            File file = new File(filepath);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String line;
            ArrayList<String> linesList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }

            listNSimple = gson.fromJson(linesList.get(0), typeNSimple);
            listASimple = gson.fromJson(linesList.get(1), typeASimple);
            listADouble = gson.fromJson(linesList.get(2), typeADouble);

            ArrayList<Noeud> noeuds = new ArrayList<>(listNSimple);
            noeuds.addAll(listASimple);
            noeuds.addAll(listADouble);

            for (Noeud n : noeuds) {
                n.setId(numNoeuds.add(n));
            }

            int i = 4;
            while (!"/Barres".equals(linesList.get(i))) {
                JsonParser parser = new JsonParser();
                JsonObject readLine = parser.parseString(linesList.get(i)).getAsJsonObject();
                //numBarresJson.add(readLine);
                double xD = readLine.get("noeudDepart").getAsJsonObject().get("px").getAsDouble();
                double yD = readLine.get("noeudDepart").getAsJsonObject().get("py").getAsDouble();
                double xA = readLine.get("noeudArrivee").getAsJsonObject().get("px").getAsDouble();
                double yA = readLine.get("noeudArrivee").getAsJsonObject().get("py").getAsDouble();
                Noeud nD = null;
                Noeud nA = null;
                for (Noeud n : noeuds) {
                    if (n.getPx() == xD && n.getPy() == yD) {
                        nD = n;
                    }
                    if (n.getPx() == xA && n.getPy() == yA) {
                        nA = n;
                    }
                }
                try {
                    if (nA != null && nD != null) {
                        Barre b = new Barre(nD, nA, readLine.get("maxTrac").getAsDouble(), readLine.get("maxComp").getAsDouble(), readLine.get("cout").getAsDouble(), readLine.get("type").getAsString());
                        b.setId(numBarres.add(b));
                        listBarre.add(b);
                    }
                } catch (Exception e) {
                    System.out.println("\r\n" + nD + "\r\n" + nA);
                    e.printStackTrace();
                }
                i++;
            }
            for (Noeud n : noeuds) {
                n.setBarresDepart(new ArrayList<Barre>());
                n.setBarresArrivee(new ArrayList<Barre>());
            }
            for (Barre b : listBarre) {
                Noeud nd = b.getNoeudDepart();
                Noeud na = b.getNoeudArrivee();
                nd.getBarresDepart().add(b);
                na.getBarresArrivee().add(b);
            }
            treillis.ajouteNoeuds(noeuds);
            treillis.ajouteBarres(listBarre);

            return treillis;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
