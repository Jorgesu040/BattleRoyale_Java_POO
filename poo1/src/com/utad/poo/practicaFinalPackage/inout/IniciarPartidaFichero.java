package com.utad.poo.practicaFinalPackage.inout;


import com.utad.poo.practicaFinalPackage.personajes.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class IniciarPartidaFichero {

    private String fileToSearch;
    private Personaje jugador;
    private Integer lootCount;
    private Integer trapCount;
    private Integer banditCount;

    public IniciarPartidaFichero(String fichero) {
        this.fileToSearch = fichero;
        this.jugador = null;
        this.lootCount = 0;
        this.trapCount = 0;
        this.banditCount = 0;
    }

    public void cargarDatosDesdeXML() {
        try {
            // Crear el DocumentBuilder para leer el XML
            File xmlFile = new File(fileToSearch);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            // Normalizar el documento
            doc.getDocumentElement().normalize();

            // Buscar el nodo GameStart
            Node gameStartNode = doc.getElementsByTagName("GameStart").item(0);
            
            if (gameStartNode != null) {
                // Obtener el nodo hijo <Player> y extraer el atributo Name
                NodeList playerList = ((Element) gameStartNode).getElementsByTagName("Player");
                if (playerList.getLength() > 0) {
                    Element playerElement = (Element) playerList.item(0);
                    String playerName = playerElement.getAttribute("Name");
                    jugador = null;  // Crear un nuevo personaje con el nombre
                }

                // Obtener el nodo <Loot> y extraer el atributo LootCount
                NodeList lootList = ((Element) gameStartNode).getElementsByTagName("Loot");
                if (lootList.getLength() > 0) {
                    Element lootElement = (Element) lootList.item(0);
                    lootCount = Integer.parseInt(lootElement.getAttribute("LootCount"));
                }

                // Obtener el nodo <Traps> y extraer el atributo TrapCount
                NodeList trapsList = ((Element) gameStartNode).getElementsByTagName("Traps");
                if (trapsList.getLength() > 0) {
                    Element trapsElement = (Element) trapsList.item(0);
                    trapCount = Integer.parseInt(trapsElement.getAttribute("TrapCount"));
                }

                // Obtener el nodo <Bandits> y extraer el atributo BanditCount
                NodeList banditsList = ((Element) gameStartNode).getElementsByTagName("Bandits");
                if (banditsList.getLength() > 0) {
                    Element banditsElement = (Element) banditsList.item(0);
                    banditCount = Integer.parseInt(banditsElement.getAttribute("BanditCount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IniciarPartidaFichero gen = new IniciarPartidaFichero("/src/");
        gen.cargarDatosDesdeXML();
        System.out.println(gen);

       
    }
}
