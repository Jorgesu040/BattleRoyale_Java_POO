package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.utad.poo.practicaFinalPackage.GameLogicHandler;

public class GraphicWindowManager {

    JFrame frame;
    MapController mapController;
    MapGenerator panel;

    public static void main(String[] args) {
        MapGenerator panel = new MapGenerator(7, 1, 1, 2, 4, new Utility());
        new GraphicWindowManager(panel);
    }

    public GraphicWindowManager(MapGenerator panel) {
        this.frame = setupFrame();
        this.panel = panel;
        this.mapController = new MapController(this.panel);
    }

    public void setupGame() {
        addGameLogic(this.mapController);
        addUIFeedback(this.mapController, this.panel);
        setupLayout(this.frame, this.panel, this.panel.getThisSize());
        initializeFrame(this.frame);
    }

    private JFrame setupFrame() {
        JFrame frame = new JFrame("Mapa Hexagonal");
        return frame;
    }

    private void addGameLogic(MapController mapController) {
        mapController.addTileEventListener(new GameLogicHandler());
    }

    private void addUIFeedback(MapController mapController, MapGenerator panel) {
        mapController.addTileEventListener(new TileEventListener() {
            @Override
            public void onTileClicked(Tile tile) {
            	if (!tile.getTileType().equals(TileType.TILE_OBSTACLE))
            	{
            		 JOptionPane.showMessageDialog(panel, tile.toString());
            	}
               
            }

            @Override
            public void onTileHovered(Tile tile) {
                // Nada, dentro de Tile ya está el stroke
            }
        });
    }

    private void setupLayout(JFrame frame, MapGenerator panel, int panelSize) {
        frame.setLayout(new BorderLayout());

        frame.add(BorderLayout.SOUTH, new JButton("Estoy en el Sur"));
        frame.add(BorderLayout.NORTH, new JButton("Estoy en el Norte"));

        JPanel eastPanel = new JPanel(new GridLayout(3, 1));
        eastPanel.add(new JButton("Atacar"));
        eastPanel.add(new JButton("Defender"));
        eastPanel.add(new JButton("Retirada"));
        frame.add(BorderLayout.EAST, eastPanel);

        JPanel westPanel = new JPanel(new GridLayout(5, 1));
        westPanel.add(new JButton("Item 1"));
        westPanel.add(new JButton("Item 2"));
        westPanel.add(new JButton("Item 3"));
        westPanel.add(new JButton("Item 4"));
        westPanel.add(new JButton("Item 5"));
        frame.add(BorderLayout.WEST, westPanel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        int width = panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_X);
        int height = panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_Y);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        centerPanel.add(panel);
        frame.add(BorderLayout.CENTER, centerPanel);
        centerPanel.setBackground(new java.awt.Color(76, 143, 200));
    }

    private void initializeFrame(JFrame frame) {
        frame.setSize(1080, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
