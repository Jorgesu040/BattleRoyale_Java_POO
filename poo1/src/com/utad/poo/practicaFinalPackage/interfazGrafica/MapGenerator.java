package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Polygon;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


/*
 * Esta clase se tiene que hacer en condiciones
 * Solo se encargara de construir en una ventana todos los tiles
 * Sera la clase tile exclusivamente quien se encargara de dibujarse
 * Por el momento esta clase es puro testing
 */

public class MapGenerator extends JPanel
{

    public static final Integer DEFAULT_SPACING_X = 0; 
    public static final Integer DEFAULT_SPACING_Y = 0; 
    public static final Integer OFFSET_X = 200;
    public static final Integer OFFSET_Y = 150;

    private List<Tile> tiles;
    private Integer size;

    
    public MapGenerator(Integer size)
    {
    	super();
    	this.size = size;
    	this.tiles = new ArrayList<Tile>();
    	
    	addMouseListener(new MouseAdapter() 
    	{
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                handleTileClick(e);
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        renderGrid(g2d);
        renderMap(g2d);
        
    }
    
   
    
    private void renderMap(Graphics g2d)
    {
    	super.setBackground(new Color(76, 143, 220));
    }

    
    /*
     *  

        for (int col = 0; col < cols; col++) {
            int xLbl = row < half ? col - row : col - half;
            int yLbl = row - half;
            int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
            int y = (int) (origin.y + yOff * (row - half) * 3);

            drawHex(g, xLbl, yLbl, x, y, radius);
        }
    }
     */
    
    // TODO revisar la generacion de tiles
    private void renderGrid(Graphics2D g2d)
    {
    	Double ang30 = Math.toRadians(30);
        Double xOff = Math.cos(ang30) * (Tile.HEXAGON_RADIOUS + MapGenerator.DEFAULT_SPACING_X);
        Double yOff = Math.sin(ang30) * (Tile.HEXAGON_RADIOUS + MapGenerator.DEFAULT_SPACING_Y);
        Integer half = this.size / 2;
    	
    	for (Integer row = 0; row < this.size; row++) 
        {
    		Integer cols = this.size - java.lang.Math.abs(row - half);
    		
            for (Integer col = 0; col < cols; col++) 
            {
            	 int xLbl = row < half ? col - row : col - half;
                 int yLbl = row - half;
                 int x = (int) (150 + xOff * (col * 2 + 1 - cols));
                 int y = (int) (150 + yOff * (row - half) * 3);
            	
                /*Integer x = (col * (Tile.HEXAGON_WIDTH + MapGenerator.DEFAULT_SPACING_X)) + MapGenerator.OFFSET_X;
                Integer y = (row * (Tile.HEXAGON_HEIGHT - (Tile.HEXAGON_RADIOUS / 2) + MapGenerator.DEFAULT_SPACING_Y	)) + MapGenerator.OFFSET_Y;

                // Desplaza las columnas impares hacia abajo
                if (col % 2 != 0) 
                {
                   y += Tile.HEXAGON_HEIGHT / 3;
                }
                */
               
                
                Tile newTile = new Tile(TileType.TILE_FREE_SPACE, x, y, false, null);
                
                this.tiles.add(newTile);
                newTile.drawTile(g2d);
            }
        }
    }
   
    private void handleTileClick(MouseEvent click) 
    {
        for (Tile tile : this.tiles) 
        {
            if (tile.contains(click.getPoint())) 
            {
                JOptionPane.showMessageDialog(
                    this,
                    tile.toString(),
                    "Información del Tile",
                    JOptionPane.INFORMATION_MESSAGE
                );
                break;
            }
        }
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Mapa Hexagonal");
        MapGenerator panel = new MapGenerator(5);
        
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
