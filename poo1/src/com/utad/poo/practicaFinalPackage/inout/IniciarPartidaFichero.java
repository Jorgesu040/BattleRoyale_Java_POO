package com.utad.poo.practicaFinalPackage.inout;

import java.io.File;
import java.util.Scanner;
import java.io.InputStream;

public class IniciarPartidaFichero {

    public static final String FILE_NAME = "iniciar.xml";
    public static final String PATH_TO_BOOT_FILE = System.getProperty("user.dir") + File.separator + 
                                                    "poo1" + File.separator + 
                                                    "files" + File.separator + 
                                                    "ficheros" + File.separator + 
                                                    IniciarPartidaFichero.FILE_NAME;

    private String jugador;
    private Integer lootCount;
    private Integer trapCount;
    private Integer banditCount;

    public IniciarPartidaFichero() 
    {
        this.jugador = "Sin Nombre";
        this.lootCount = 0;
        this.trapCount = 0;
        this.banditCount = 0;
    }

    public Boolean cargarDatosDesdeXML() 
    {   
        Boolean cargado = true;
        try {
            // Intentar cargar el archivo desde el directorio actual
            File myObj = new File(FILE_NAME);

            if (!myObj.exists()) {
                System.out.println("Error al leer el archivo en el directorio actual.");
                System.out.println("Asegúrate de que el archivo " + FILE_NAME + " existe en el directorio actual.");
                System.out.println("Intentando cargar el archivo incluido en el jar...");

                // Intentar cargar el archivo desde el jar usando InputStream
                InputStream inputStream = getClass().getResourceAsStream("/ficheros/" + FILE_NAME);
                if (inputStream != null) {
                    Scanner myReader = new Scanner(inputStream);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine().trim(); 

                        if (data.startsWith("<Player")) {
                            String[] parts = data.split("Name=\"");
                            if (parts.length > 1) 
                            {
                                String name = parts[1].split("\"")[0]; 
                                this.jugador = name;
                            }

                        } else if (data.startsWith("<Loot")) 
                        {
                            String[] parts = data.split("LootCount=\"");
                            if (parts.length > 1) 
                            {
                                this.lootCount = Integer.parseInt(parts[1].split("\"")[0]);
                            }

                        } else if (data.startsWith("<Traps")) 
                        {
                            String[] parts = data.split("TrapCount=\"");
                            if (parts.length > 1) 
                            {
                                this.trapCount = Integer.parseInt(parts[1].split("\"")[0]);
                            }

                        } else if (data.startsWith("<Bandits")) 
                        {
                            String[] parts = data.split("BanditCount=\"");
                            if (parts.length > 1) 
                            {
                                this.banditCount = Integer.parseInt(parts[1].split("\"")[0]); 
                            }
                        }
                    }
                    myReader.close();
                } else {
                    System.out.println("Archivo no encontrado en el jar: " + FILE_NAME);
                    cargado = false;
                }
            } else {
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().trim(); 

                    if (data.startsWith("<Player")) {
                        String[] parts = data.split("Name=\"");
                        if (parts.length > 1) 
                        {
                            String name = parts[1].split("\"")[0]; 
                            this.jugador = name;
                        }

                    } else if (data.startsWith("<Loot")) 
                    {
                        String[] parts = data.split("LootCount=\"");
                        if (parts.length > 1) 
                        {
                            this.lootCount = Integer.parseInt(parts[1].split("\"")[0]);
                        }

                    } else if (data.startsWith("<Traps")) 
                    {
                        String[] parts = data.split("TrapCount=\"");
                        if (parts.length > 1) 
                        {
                            this.trapCount = Integer.parseInt(parts[1].split("\"")[0]);
                        }

                    } else if (data.startsWith("<Bandits")) 
                    {
                        String[] parts = data.split("BanditCount=\"");
                        if (parts.length > 1) 
                        {
                            this.banditCount = Integer.parseInt(parts[1].split("\"")[0]); 
                        }
                    }
                }
                myReader.close();
            }

            this.verifyValues();

        } catch (Exception e) {
            e.printStackTrace();
            cargado = false;
        }
        return cargado;
    }

    private void verifyValues() {
        if (this.lootCount < 1) {
            this.lootCount = 1;
            System.out.println("La cantidad de botín no puede ser menor a 1. Se usará el valor por defecto: 1");
        }

        if (this.trapCount < 1) {
            this.trapCount = 1;
            System.out.println("La cantidad de trampas no puede ser menor a 1. Se usará el valor por defecto: 1");
        }

        if (this.banditCount < 1) {
            this.banditCount = 1;
            System.out.println("La cantidad de bandidos no puede ser menor a 1. Se usará el valor por defecto: 1");
        }

        if (this.jugador == null || this.jugador.isEmpty()) {
            this.jugador = "Sin Nombre";
            System.out.println("El nombre del jugador no puede estar vacío. Se usará el valor por defecto: Sin Nombre");
        }
    }


    public String getJugador() {
        return this.jugador;
    }

    public Integer getLootCount() {
        return this.lootCount;
    }

    public Integer getTrapCount() {
        return this.trapCount;
    }

    public Integer getBanditCount() {
        return this.banditCount;
    }


    
    @Override
    public String toString() {
        return "IniciarPartidaFichero [jugador=" + jugador + ", lootCount=" + lootCount + ", trapCount=" + trapCount
                + ", banditCount=" + banditCount + "]";
    }

    public static void main(String[] args) {
        IniciarPartidaFichero partida = new IniciarPartidaFichero();
        partida.cargarDatosDesdeXML();

        System.out.println(partida);
    }
}
