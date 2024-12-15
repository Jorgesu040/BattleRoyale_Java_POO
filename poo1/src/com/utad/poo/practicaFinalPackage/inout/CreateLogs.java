package com.utad.poo.practicaFinalPackage.inout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;  
import java.io.IOException; 
import java.util.List;
import java.util.ArrayList;

public class CreateLogs 
{
    public static final Integer MAX_LINEAS = 1000;
    public static final String FILE_NAME = "logs.txt";
    public static final String PATH_TO_LOGS_FILE = System.getProperty("user.dir") + File.separator + 
                                                    "poo1" + File.separator + 
                                                    "files" + File.separator + 
                                                    "ficheros" + File.separator + 
                                                    CreateLogs.FILE_NAME;


    public static List<String> lineas = new ArrayList<String>();
    public static Integer lineasCount = 0;

    public static void addLog(String newLog) 
    {
        if (CreateLogs.lineasCount >= CreateLogs.MAX_LINEAS) 
        {
            
            CreateLogs.lineas.remove(0);
            CreateLogs.lineasCount--;
        }
    
        CreateLogs.lineas.add(newLog);
        CreateLogs.lineasCount++;
    }

    
    public static void printLogs()
    {
        try {
            FileWriter writer = new FileWriter(CreateLogs.PATH_TO_LOGS_FILE, true);
          
            for (String log : CreateLogs.lineas)
            {
                writer.write(log + "\n");
            }
        
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        
        CreateLogs.addLog("HOLA ME LLAMO SERGIO");
        CreateLogs.addLog("MATESANZ PODEMITA");
        CreateLogs.addLog("PILLAN A LOLALOLITA FUMANDO EN UN BAR");

        CreateLogs.printLogs();

    }
}
