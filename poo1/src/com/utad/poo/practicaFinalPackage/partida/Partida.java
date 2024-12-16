package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.inout.IniciarPartidaFichero;
import com.utad.poo.practicaFinalPackage.interfazGrafica.MapGenerator;
import com.utad.poo.practicaFinalPackage.interfazGrafica.Utility;

import java.util.Scanner;

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


        // Comprobar los valores introducidos
        if (size < 4) {
            size = 4;
            System.out.println("El tamaño del mapa no puede ser menor a 4. Se usará el valor por defecto: 4");
        }

        if (traps < 1) {
            traps = 1;
            System.out.println("La cantidad de trampas no puede ser menor a 1. Se usará el valor por defecto: 1");
        }

        if (bandits < 1) {
            bandits = 1;
            System.out.println("La cantidad de bandidos no puede ser menor a 1. Se usará el valor por defecto: 1");
        }

        if (loot < 1) {
            loot = 1;
            System.out.println("La cantidad de botín no puede ser menor a 1. Se usará el valor por defecto: 1");
        }

        scanner.close();
        Utility utility = new Utility();

        MapGenerator mapGenerator = new MapGenerator(size, traps, players, bandits, loot, utility, cargadorFichero, cargarDesdeFichero);
        GameContoller gameController = new GameContoller(mapGenerator);
        gameController.startGame();

        while (true);

        
    }
}