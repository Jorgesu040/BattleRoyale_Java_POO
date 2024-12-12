package com.utad.poo.practicaFinalPackage.interfazGrafica;

import com.utad.poo.practicaFinalPackage.personajes.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.utad.poo.practicaFinalPackage.GameLogicHandler;
import com.utad.poo.practicaFinalPackage.herramientas.Arma;
import com.utad.poo.practicaFinalPackage.herramientas.Escudo;

import java.util.List;
import java.util.ArrayList;

public class ventanaTesting 
{
	public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Mapa Hexagonal");
   
        
        MapGenerator panel = new MapGenerator(7, 1, 2, 2, 4);
        MapController mapController = new MapController(panel);
        
        // Add game logic
        mapController.addTileEventListener(new GameLogicHandler());
        
        // Add UI feedback
        mapController.addTileEventListener(new TileEventListener() {
            @Override
            public void onTileClicked(Tile tile) {
                JOptionPane.showMessageDialog(panel, tile.toString());
            }
            
            @Override
            public void onTileHovered(Tile tile) {
            	 // nada, dentro de Tile ya esta el stroke
            }
        });
        
        frame.add(panel);
        frame.setSize(1080, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
