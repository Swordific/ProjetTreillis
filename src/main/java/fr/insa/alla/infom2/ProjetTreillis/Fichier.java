/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Asus
 */
public class Fichier {

    public static String formatXML(String xml) throws TransformerException {

        // write data to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();

        // alternative
        //Transformer transformer = SAXTransformerFactory.newInstance().newTransformer();
        // pretty print by indention
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // add standalone="yes", add line break before the root element
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        /*transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
              "-//W3C//DTD XHTML 1.0 Transitional//EN");

      transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
              "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");*/
        // ignore <?xml version="1.0" encoding="UTF-8"?>
        //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));

        return output.toString();

    }

    public static void exportTreillis(Treillis treillis, OutputStream out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(out, "UTF-8");

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
                writer.writeAttribute("px", Double.toString(barre.getNoeudDepart().getPx()));
                writer.writeAttribute("py", Double.toString(barre.getNoeudDepart().getPy()));

                writer.writeStartElement("f");
                writer.writeAttribute("vx", Double.toString(barre.getNoeudDepart().getF().getVx()));
                writer.writeAttribute("vy", Double.toString(barre.getNoeudDepart().getF().getVy()));
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("noeudArrivee");
                writer.writeStartElement("noeud");
                writer.writeAttribute("id", Integer.toString(barre.getNoeudArrivee().getId()));
                writer.writeAttribute("px", Double.toString(barre.getNoeudArrivee().getPx()));
                writer.writeAttribute("py", Double.toString(barre.getNoeudArrivee().getPy()));

                writer.writeStartElement("f");
                writer.writeAttribute("vx", Double.toString(barre.getNoeudArrivee().getF().getVx()));
                writer.writeAttribute("vy", Double.toString(barre.getNoeudArrivee().getF().getVy()));
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeAttribute("maxTrac", Double.toString(barre.getMaxTrac()));
                writer.writeAttribute("maxComp", Double.toString(barre.getMaxComp()));
                writer.writeAttribute("cout", Double.toString(barre.getCout()));
                writer.writeEndElement();
            }
            writer.writeEndElement();

            writer.writeStartElement("barresArrivee");
            for (Barre barre : noeud.getBarresArrivee()) {
                writer.writeStartElement("barre");
                writer.writeAttribute("id", Integer.toString(barre.getId()));

                writer.writeStartElement("noeudDepart");
                writer.writeStartElement("noeud");
                writer.writeAttribute("id", Integer.toString(barre.getNoeudDepart().getId()));
                writer.writeAttribute("px", Double.toString(barre.getNoeudDepart().getPx()));
                writer.writeAttribute("py", Double.toString(barre.getNoeudDepart().getPy()));

                writer.writeStartElement("f");
                writer.writeAttribute("vx", Double.toString(barre.getNoeudDepart().getF().getVx()));
                writer.writeAttribute("vy", Double.toString(barre.getNoeudDepart().getF().getVy()));
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeStartElement("noeudArrivee");
                writer.writeStartElement("noeud");
                writer.writeAttribute("id", Integer.toString(barre.getNoeudArrivee().getId()));
                writer.writeAttribute("px", Double.toString(barre.getNoeudArrivee().getPx()));
                writer.writeAttribute("py", Double.toString(barre.getNoeudArrivee().getPy()));

                writer.writeStartElement("f");
                writer.writeAttribute("vx", Double.toString(barre.getNoeudArrivee().getF().getVx()));
                writer.writeAttribute("vy", Double.toString(barre.getNoeudArrivee().getF().getVy()));
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();

                writer.writeAttribute("maxTrac", Double.toString(barre.getMaxTrac()));
                writer.writeAttribute("maxComp", Double.toString(barre.getMaxComp()));
                writer.writeAttribute("cout", Double.toString(barre.getCout()));
                writer.writeEndElement();
            }
            writer.writeEndElement();
        }
        writer.writeEndElement();

        writer.writeStartElement("barres");

        for (Barre barre : treillis.getBarres()) {
            writer.writeStartElement("barre");
            writer.writeAttribute("id", Integer.toString(barre.getId()));

            writer.writeStartElement("noeudDepart");
            writer.writeStartElement("noeud");
            writer.writeAttribute("id", Integer.toString(barre.getNoeudDepart().getId()));
            writer.writeAttribute("px", Double.toString(barre.getNoeudDepart().getPx()));
            writer.writeAttribute("py", Double.toString(barre.getNoeudDepart().getPy()));

            writer.writeStartElement("f");
            writer.writeAttribute("vx", Double.toString(barre.getNoeudDepart().getF().getVx()));
            writer.writeAttribute("vy", Double.toString(barre.getNoeudDepart().getF().getVy()));
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndElement();

            writer.writeStartElement("noeudArrivee");
            writer.writeStartElement("noeud");
            writer.writeAttribute("id", Integer.toString(barre.getNoeudArrivee().getId()));
            writer.writeAttribute("px", Double.toString(barre.getNoeudArrivee().getPx()));
            writer.writeAttribute("py", Double.toString(barre.getNoeudArrivee().getPy()));

            writer.writeStartElement("f");
            writer.writeAttribute("vx", Double.toString(barre.getNoeudArrivee().getF().getVx()));
            writer.writeAttribute("vy", Double.toString(barre.getNoeudArrivee().getF().getVy()));
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndElement();

            writer.writeAttribute("maxTrac", Double.toString(barre.getMaxTrac()));
            writer.writeAttribute("maxComp", Double.toString(barre.getMaxComp()));
            writer.writeAttribute("cout", Double.toString(barre.getCout()));
            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }

    public static void importTreillis() {

    }

}
