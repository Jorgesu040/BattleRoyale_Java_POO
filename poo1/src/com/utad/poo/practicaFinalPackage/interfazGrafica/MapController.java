package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class MapController {
    private MapGenerator mapGenerator;
    private List<TileEventListener> tileEventListeners;

    public MapController(MapGenerator mapGenerator) {
        this.mapGenerator = mapGenerator;
        this.tileEventListeners = new ArrayList<>();
        this.initializeListeners();
    }
    
    public void addTileEventListener(TileEventListener listener) {
        this.tileEventListeners.add(listener);
    }
    
    private void initializeListeners() {
        mapGenerator.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleTileClick(e);
            }
          
        });
        
        mapGenerator.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleTileHover(e);
            }
        });

    }
    
    private void handleTileClick(MouseEvent click) {
        for (Tile tile : this.mapGenerator.getTiles()) {
            if (tile.contains(click.getPoint())) {
                for (TileEventListener listener : this.tileEventListeners) {
                    listener.onTileClicked(tile);
                }
                break;
            }
        }
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

}