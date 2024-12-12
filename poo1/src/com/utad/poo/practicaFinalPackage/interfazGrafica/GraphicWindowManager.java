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
    
    public static void main(String[] args) {
        GraphicWindowManager graphicWindowManager = new GraphicWindowManager();
        graphicWindowManager.createWindow();
    }

    public void createWindow() {
        JFrame frame = setupFrame();
        MapGenerator panel = setupMapGenerator();
        MapController mapController = new MapController(panel);

        addGameLogic(mapController);
        addUIFeedback(mapController, panel);
        setupLayout(frame, panel, panel.getThisSize());
        initializeFrame(frame);
    }

    private JFrame setupFrame() {
        JFrame frame = new JFrame("Mapa Hexagonal");
        return frame;
    }

    private MapGenerator setupMapGenerator() {
        MapGenerator panel = new MapGenerator(7, 1, 1, 2, 4);
        return panel;
    }

    private void addGameLogic(MapController mapController) {
        mapController.addTileEventListener(new GameLogicHandler());
    }

    private void addUIFeedback(MapController mapController, MapGenerator panel) {
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
    }

    private void setupLayout(JFrame frame, MapGenerator panel, int panelSize) {
        frame.setLayout(new BorderLayout());

        frame.add(BorderLayout.SOUTH, new JButton("Estoy en el Sur"));
        frame.add(BorderLayout.NORTH, new JButton("Estoy en el Norte"));
        
        // Añadir botones en los bordes de la ventana
        // East border - Aqui irian las acciones: atacar, defender, retirada, etc;
        JPanel eastPanel = new JPanel(new GridLayout(3, 1));
        eastPanel.add(new JButton("Atacar"));
        eastPanel.add(new JButton("Defender"));
        eastPanel.add(new JButton("Retirada"));
        frame.add(BorderLayout.EAST, eastPanel);

        // West border - Aqui irian los 5 items que se añaden en el panel
        JPanel westPanel = new JPanel(new GridLayout(5, 1));
        westPanel.add(new JButton("Item 1"));
        westPanel.add(new JButton("Item 2"));
        westPanel.add(new JButton("Item 3"));
        westPanel.add(new JButton("Item 4"));
        westPanel.add(new JButton("Item 5"));
        frame.add(BorderLayout.WEST, westPanel);

        // Toda esta logica es para centrar el panel en la ventana principal por tanto la posiscion ya no la maneja el objeto panel
        // Ahora las coordenadas de los ejes X e Y se emplean para centrar el panel en su espacio
        JPanel centerPanel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_X), panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_Y)));
        panel.setMinimumSize(new Dimension(panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_X), panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_Y)));
        centerPanel.add(panel); 
        frame.add(BorderLayout.CENTER, centerPanel);
        // Cambio un poco este color para ver las dos ventanas de las que hablo
        centerPanel.setBackground(new java.awt.Color(76, 143, 200));
    }

    private void initializeFrame(JFrame frame) {
        frame.setSize(1080, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
