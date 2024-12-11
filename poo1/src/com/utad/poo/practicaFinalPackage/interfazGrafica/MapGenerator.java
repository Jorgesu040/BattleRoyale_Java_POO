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
    private Integer rows;
    private Integer columns;
    
    public MapGenerator(Integer rows, Integer cols)
    {
    	super();
    	this.rows = rows;
    	this.columns = cols;
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

    // TODO revisar la generacion de tiles
    private void renderGrid(Graphics2D g2d)
    {
    	for (Integer row = 0; row < this.rows; row++) 
        {
            for (Integer col = 0; col < this.columns; col++) 
            {
                // Calcular la posición del hexágono
                Integer x = (col * (Tile.HEXAGON_WIDTH + MapGenerator.DEFAULT_SPACING_X)) + MapGenerator.OFFSET_X;
                Integer y = (row * (Tile.HEXAGON_HEIGHT - (Tile.HEXAGON_RADIOUS / 2) + MapGenerator.DEFAULT_SPACING_Y	)) + MapGenerator.OFFSET_Y;

                // Desplaza las columnas impares hacia abajo
                if (col % 2 != 0) 
                {
                   y += Tile.HEXAGON_HEIGHT / 3;
                }
                
                TileType tipo = TileType.TILE_FREE_SPACE;
                
                if (row == 0 && col == 0)
                {
                	tipo = TileType.TILE_SPAWN;
                }
                else if ( row == 2 && col == 4)
                {
                	tipo = TileType.TILE_LOOT;
                }
                
                Tile newTile = new Tile(tipo, x, y, false, null);
                
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
        MapGenerator panel = new MapGenerator(5, 5);
        
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
