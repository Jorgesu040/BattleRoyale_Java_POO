package com.utad.poo.practicaFinalPackage.interfazGrafica;

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
        tileEventListeners.add(listener);
    }
    
    private void initializeListeners() {
        mapGenerator.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleTileClick(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                handleTileHover(e);
            }
        });
    }
    
    private void handleTileClick(MouseEvent click) {
        for (Tile tile : mapGenerator.getTiles()) {
            if (tile.contains(click.getPoint())) {
                for (TileEventListener listener : tileEventListeners) {
                    listener.onTileClicked(tile);
                }
                break;
            }
        }
    }
    
    private void handleTileHover(MouseEvent e) {
        for (Tile tile : mapGenerator.getTiles()) {
            if (tile.contains(e.getPoint())) {
                for (TileEventListener listener : tileEventListeners) {
                    listener.onTileHovered(tile);
                }
                break;
            }
        }
    }
}