package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import java.util.ArrayList;

import com.utad.poo.practicaFinalPackage.partida.TileClickListener;

public class MapController {
    private MapGenerator mapGenerator;
    private List<TileEventListener> tileEventListeners;
    private Tile selectedTile; // Variable to store the selected Tile
    private TileClickListener tileClickListener;

    public MapController(MapGenerator mapGenerator) {
        this.mapGenerator = mapGenerator;
        this.tileEventListeners = new ArrayList<>();
        this.initializeListeners();
    }
    
    public void addTileEventListener(TileEventListener listener) {
        this.tileEventListeners.add(listener);
    }
    
    public void setTileClickListener(TileClickListener listener) {
        this.tileClickListener = listener;
    }
    
    private void initializeListeners() {
        mapGenerator.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tile tile = handleTileClick(e); // Capture the returned Tile
                if (tile != null) {
                    selectedTile = tile; // Update the variable with the returned Tile
                    // Notificar al listener que se ha hecho clic en un Tile
                    if (tileClickListener != null) {
                        tileClickListener.onTileClicked(tile);
                    }
                }
            }
        });
        
        mapGenerator.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleTileHover(e);
            }
        });

    }
    
    private Tile handleTileClick(MouseEvent click) {
        for (Tile tile : this.mapGenerator.getTiles()) {
            if (tile.contains(click.getPoint())) {
                if (!tile.getTileType().equals(TileType.TILE_OBSTACLE)) {
                    //JOptionPane.showMessageDialog(mapGenerator, tile.toString());
                }
                for (TileEventListener listener : this.tileEventListeners) {
                    listener.onTileClicked(tile);
                }
                return tile; // Return the clicked Tile
            }
        }
        return null; // No tile clicked
    }
    
    private void handleTileHover(MouseEvent e) {
        boolean anyTileHovered = false;

        for (Tile tile : this.mapGenerator.getTiles()) {
            if (tile.contains(e.getPoint())) {
                tile.setHovered(true);
                anyTileHovered = true;

                for (TileEventListener listener : this.tileEventListeners) {
                    listener.onTileHovered(tile);
                }
            } else {
                tile.setHovered(false);
            }
        }

        if (anyTileHovered) {
            this.mapGenerator.repaint();
        }
    }

    // Optional: Getter for selectedTile
    public Tile getSelectedTile() {
        return selectedTile;
    }

}