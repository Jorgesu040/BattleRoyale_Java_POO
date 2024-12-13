package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ventanaTesting 
{
	public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Mapa Hexagonal");
   
        
        MapGenerator panel = new MapGenerator(7, 1, 2, 2, 4, new Utility());
        MapController mapController = new MapController(panel);
                
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
