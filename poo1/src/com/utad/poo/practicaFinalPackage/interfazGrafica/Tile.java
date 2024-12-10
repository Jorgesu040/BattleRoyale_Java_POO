package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.*;


public abstract class Tile 
{
	public static final Integer HEXAGON_RADIOUS = 10; // 10 pixeles de radio
	public static final Integer HEXAGON_WIDTH = (int) (Math.sqrt(3) * Tile.HEXAGON_RADIOUS);
	public static final Integer HEXAGON_HEIGHT = 2 * Tile.HEXAGON_RADIOUS;

	protected Integer posX;
	protected Integer posY;
	
	protected TileType tileType;
	protected Boolean ocupaid;
	protected OcupaidState ocupaidType;
	
	public Tile()
	{
		
	}
	
	
	public void drawTile(Graphics2D graficos)
	{
		 Polygon hexagono = new Polygon();
		  
		 // 6 repeticiones por ser un hexagono
		 for (Integer vertice = 0; vertice < 6; vertice++)
		 {
			 Double anguloVertice = Math.toRadians(vertice * 60);
			 
			 Integer verticePosX = (int) (this.posX + Tile.HEXAGON_RADIOUS * Math.cos(anguloVertice));
			 Integer verticePosY = (int) (this.posY + Tile.HEXAGON_RADIOUS * Math.sin(anguloVertice));
					 
					 	
			 hexagono.addPoint(verticePosX, verticePosY);
		 }
		 
		
		setTileColor(graficos, hexagono);
		
	}
	
	private void setTileColor(Graphics2D graficos, Polygon hexagono)
	{
		switch(this.tileType)
		{
			case TILE_FREE_SPACE: 
			{
				// Interior
				graficos.setColor(Color.GREEN);
				graficos.fillPolygon(hexagono);
				
				// Exterior
				graficos.setColor(Color.DARK_GRAY);
				graficos.drawPolygon(hexagono);
			}

			case TILE_OBSTACLE:
			{
				// Interior
				graficos.setColor(Color.CYAN);
				graficos.fillPolygon(hexagono);
				
				// Exterior
				graficos.setColor(Color.CYAN);
				graficos.drawPolygon(hexagono);
			}
		
			case TILE_LOOT:
			{
				// Interior
				graficos.setColor(Color.PINK);
				graficos.fillPolygon(hexagono);
				
				// Exterior
				graficos.setColor(Color.DARK_GRAY);
				graficos.drawPolygon(hexagono);
			}
		
			case TILE_SPAWN:
			{
				// Interior
				graficos.setColor(Color.BLACK);
				graficos.fillPolygon(hexagono);
				
				// Exterior
				graficos.setColor(Color.RED);
				graficos.drawPolygon(hexagono);
			}
		
			case TILE_SPAWN_AI:
			{
				// Interior
				graficos.setColor(Color.ORANGE);
				graficos.fillPolygon(hexagono);
				
				// Exterior
				graficos.setColor(Color.YELLOW);
				graficos.drawPolygon(hexagono);
			}
			
			
			default:  // Se trata como un TILE_FREE_SPACE
			{
				// Interior
				graficos.setColor(Color.GREEN);
				graficos.fillPolygon(hexagono);
				
				// Exterior
				graficos.setColor(Color.DARK_GRAY);
				graficos.drawPolygon(hexagono);
			}
		
		}
	}
	
}
