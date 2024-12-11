package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.JFrame;

public class ventanaTesting 
{
	public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Mapa Hexagonal");
        MapGenerator panel = new MapGenerator(7, 540, 375, 2, 3, 5);
        
        frame.add(panel);
        frame.setSize(1080, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
