package com.utad.poo.practicaFinalPackage.partida;
// filepath: /c:/Users/jorge/wsjorge/gitclone/BattleRoyale_Java_POO/poo1/src/com/utad/poo/practicaFinalPackage/Main.java

import com.utad.poo.practicaFinalPackage.inout.IniciarPartidaFichero;
import com.utad.poo.practicaFinalPackage.interfazGrafica.MapGenerator;
import com.utad.poo.practicaFinalPackage.interfazGrafica.Utility;

import java.util.Scanner;
import java.io.File;

public class Partida {
    public static void main(String[] args) {

        IniciarPartidaFichero cargadorFichero = new IniciarPartidaFichero();


        int size = 7;
        int traps = 3;
        int players = 1;
        int bandits = 1;
        int loot = 3;

        Scanner scanner = new Scanner(System.in);
        boolean cargarDesdeFichero = false;

        System.out.println("¿Desea cargar la configuración desde un archivo (iniciar.xml)? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            if (cargadorFichero.cargarDatosDesdeXML()) {
                cargarDesdeFichero = true;
                System.out.println("Configuración cargada desde el archivo iniciar.xml");
            } else {
                System.out.println("El archivo iniciar.xml no se encontró. Se usará la configuración por defecto.");
            }
        }

        


        if (!cargarDesdeFichero) {
            System.out.println("Ingrese el tamaño del mapa (entero, por ejemplo 7): ");
            size = scanner.nextInt();

            System.out.println("Ingrese la cantidad de trampas: ");
            traps = scanner.nextInt();

            System.out.println("Ingrese la cantidad de bandidos: ");
            bandits = scanner.nextInt();

            System.out.println("Ingrese la cantidad de botín: ");
            loot = scanner.nextInt();
        }

        scanner.close();
        Utility utility = new Utility();

        MapGenerator mapGenerator = new MapGenerator(size, traps, players, bandits, loot, utility, cargadorFichero, cargarDesdeFichero);
        GameContoller gameController = new GameContoller(mapGenerator);
        gameController.startGame();

        while (true);

        
    }
}