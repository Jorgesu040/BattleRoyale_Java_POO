package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.utad.poo.practicaFinalPackage.GameLogicHandler;

public class ventanaTesting 
{
	public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Mapa Hexagonal");
        MapGenerator panel = new MapGenerator(7, 540, 375, 2, 3, 5);
        MapController controller = new MapController(panel);
        
        // Add game logic
        controller.addTileEventListener(new GameLogicHandler());
        
        // Add UI feedback
        controller.addTileEventListener(new TileEventListener() {
            @Override
            public void onTileClicked(Tile tile) {
                JOptionPane.showMessageDialog(panel, tile.toString());
            }
            
            @Override
            public void onTileHovered(Tile tile) {
                // Handle UI feedback
            }
        });
        
        frame.add(panel);
        frame.setSize(1080, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
