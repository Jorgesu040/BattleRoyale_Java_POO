package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.Polygon;

/*
 * Esta clase se tiene que hacer en condiciones
 * Solo se encargara de construir en una ventana todos los tiles
 * Sera la clase tile exclusivamente quien se encargara de dibujarse
 * Por el momento esta clase es puro testing
 */

public class MapGenerator extends JPanel
{
	
	private final Integer hexRadius = 30; 
    private final Integer hexWidth = (int) (Math.sqrt(3) * hexRadius); 
    private final Integer hexHeight = 2 * hexRadius; 
    private final Integer hexSpacing = -1; 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Esto hay que hacerlo en condiciones
        int cols = 5; 
        int rows = 5; 

        for (Integer row = 0; row < rows; row++) {
            for (Integer col = 0; col < cols; col++) {
                // Calcular la posición del hexágono
                int x = col * (hexWidth + hexSpacing);
                int y = row * (hexHeight - hexRadius / 2 + hexSpacing);

                // Desplaza las columnas impares hacia abajo
                if (col % 2 != 0) 
                {
                    y += hexHeight / 2;
                }

                drawHexagon(g2d, x, y, hexRadius);
            }
        }
    }

    private void drawHexagon(Graphics2D g2d, Integer x, Integer y, Integer radius) 
    {
        Polygon hex = new Polygon();
        
        // vertice = posicion_centro + radio * angulo
        for (int i = 0; i < 6; i++) 
        {
            double angle = Math.toRadians(i * 60);
            int xPoint = (int) (x + radius * Math.cos(angle));
            int yPoint = (int) (y + radius * Math.sin(angle));
            hex.addPoint(xPoint, yPoint);
        }
        
        g2d.setColor(Color.YELLOW);
        g2d.fillPolygon(hex);
        g2d.setColor(Color.GREEN);
        g2d.drawPolygon(hex);
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Mapa Hexagonal");
        MapGenerator panel = new MapGenerator();
        
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
