/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import java.io.OutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Asus
 */
public class Fichier {

    public static void exportTreillis(Treillis treillis, OutputStream out) throws XMLStreamException {

        //s'assurer que les objets dans les listes soient ceux references et crees autrepart

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");

        writer.writeStartElement("noeuds");

        for (Noeud noeud : treillis.getNoeuds()) {
            writer.writeStartElement("noeud");
            writer.writeAttribute("id", Integer.toString(noeud.getId()));
            writer.writeAttribute("px", Double.toString(noeud.getPx()));
            writer.writeAttribute("py", Double.toString(noeud.getPy()));

            writer.writeStartElement("f");
            writer.writeAttribute("vx", Double.toString(noeud.getF().getVx()));
            writer.writeAttribute("vy", Double.toString(noeud.getF().getVy()));
            writer.writeEndElement();

            writer.writeStartElement("barresDepart");
            for (Barre barre : noeud.getBarresDepart()) {
                writer.writeStartElement("barre");
                writer.writeAttribute("id", Integer.toString(barre.getId()));

                writer.writeStartElement("noeudDepart");
                writer.writeStartElement("noeud");
                writer.writeAttribute("id", Integer.toString(barre.getNoeudDepart().getId()));
                writer.writeAttribute("px", Double.toString(noeud.getPx()));
                writer.writeAttribute("py", Double.toString(noeud.getPy()));

                writer.writeStartElement("f");
                writer.writeAttribute("vx", Double.toString(noeud.getF().getVx()));
                writer.writeAttribute("vy", Double.toString(noeud.getF().getVy()));
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeAttribute("noeudArrivee", Double.toString(noeud.getPy()));
                writer.writeAttribute("maxTrac", Double.toString(noeud.getPx()));
                writer.writeAttribute("maxComp", Double.toString(noeud.getPy()));
                writer.writeAttribute("cout", Double.toString(noeud.getPx()));
            }
        }

        writer.writeStartElement("staff");
        writer.writeAttribute("id", "1001");

        writer.writeStartElement("name");
        writer.writeCharacters("mkyong");
        writer.writeEndElement();

        writer.writeStartElement("salary");
        writer.writeAttribute("currency", "USD");
        writer.writeCharacters("5000");
        writer.writeEndElement();

        writer.writeStartElement("bio");
        writer.writeCData("HTML tag <code>testing</code>");
        writer.writeEndElement();

        writer.writeEndElement();
        // </staff>

        // <staff>
        writer.writeStartElement("staff");
        writer.writeAttribute("id", "1002");

        writer.writeStartElement("name");
        writer.writeCharacters("yflow");
        writer.writeEndElement();

        writer.writeStartElement("salary");
        writer.writeAttribute("currency", "EUR");
        writer.writeCharacters("8000");
        writer.writeEndElement();

        writer.writeStartElement("bio");
        writer.writeCData("a & b");
        writer.writeEndElement();

        writer.writeEndElement();
        // </staff>

        writer.writeEndDocument();
        // </company>

        writer.flush();

        writer.close();

    }

    public static void importTreillis() {

    }

}
