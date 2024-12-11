package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.*;




public class Tile 
{
	public static final Integer HEXAGON_RADIOUS = 40; // 10 pixeles de radio
	public static final Integer HEXAGON_WIDTH = (int) (Math.sqrt(3) * Tile.HEXAGON_RADIOUS);
	public static final Integer HEXAGON_HEIGHT = 2 * Tile.HEXAGON_RADIOUS;

	protected Integer posX;
	protected Integer posY;
	protected Integer tileId;
	
	protected TileType tileType;
	
	protected Boolean ocupado;
	protected Object objectoOcupado; // el objeto que esta ocupando el tile
	
	protected Polygon hexagono;
	private Integer debug;
	private boolean isHovered;
	
	public Tile(TileType type, Integer posX, Integer posY, Boolean ocupado, Object objectoOcupado, Integer id)
	{
		this.tileType = type;
		this.posX = posX;
		this.posY = posY;
		this.ocupado = ocupado;
		this.objectoOcupado = objectoOcupado;
		this.tileId = id;
		this.debug = 0;
		this.isHovered = false;
	}
	
	public boolean contains(Point p) 
	{
		return this.hexagono.contains(p);
	}
	
	  public void setHovered(boolean hovered) {
	        this.isHovered = hovered;
	    }

	    public boolean isHovered() {
	        return this.isHovered;
	    }
	
	public void createHexagon()
	{
		this.hexagono = new Polygon();
		  
		 // 6 repeticiones por ser un hexagono
		 for (Integer vertice = 0; vertice < 6; vertice++)
		 {
			 Double anguloVertice = Math.toRadians((vertice * 60) + 90);
			 
			 Integer verticePosX = (int) (this.posX + Tile.HEXAGON_RADIOUS * Math.cos(anguloVertice));
			 Integer verticePosY = (int) (this.posY + Tile.HEXAGON_RADIOUS * Math.sin(anguloVertice));
					 
					 	
			 this.hexagono.addPoint(verticePosX, verticePosY);
		 }
	}
	
	public void drawTile(Graphics2D graficos)
	{
		createHexagon();
		setTileColor(graficos, this.hexagono);	
		
		if (this.isHovered) {
	        // Agregar borde rojo más grueso para indicar hover
	        graficos.setStroke(new BasicStroke(3)); // Borde más grueso
	        graficos.setColor(Color.RED);           // Color del borde
	        graficos.drawPolygon(this.hexagono);    // Dibujar borde
	        graficos.setStroke(new BasicStroke(1)); // Restablecer grosor normal
	    }
	}
	
	private void setTileColor(Graphics2D graficos, Polygon hexagono)
	{

		
		switch(this.tileType)
		{
			case TILE_FREE_SPACE: 
			{
				// Interior
				graficos.setColor(new Color(44, 131, 58));
				graficos.fillPolygon(hexagono);
				this.debug = 1;

			} break;

			case TILE_OBSTACLE:
			{
				// Interior
				graficos.setColor(Color.ORANGE);
				graficos.fillPolygon(hexagono);
				this.debug = 2;

			} break;
		
			
			case TILE_LOOT:
			{
				// Interior
				graficos.setColor(Color.PINK);
				graficos.fillPolygon(hexagono);
				this.debug = 3;
				
			} break;
		
			case TILE_SPAWN:
			{
				// Interior
				graficos.setColor(Color.BLACK);
				graficos.fillPolygon(hexagono);	
				this.debug = 4;
				
			} break;
		
			case TILE_SPAWN_AI:
			{
				// Interior
				graficos.setColor(Color.ORANGE);
				graficos.fillPolygon(hexagono);
				this.debug = 5;
				
			} break;
			
			case TILE_TRAP_SET:
			{
				// Interior
				graficos.setColor(Color.RED);
				graficos.fillPolygon(hexagono);
				this.debug = 6;
				
			} break;
			
			case TILE_TRAP_IDLE:
			{
				// Interior
				graficos.setColor(Color.GRAY);
				graficos.fillPolygon(hexagono);
				this.debug = 7;
			} break;
				
			
			default:  // Se trata como un TILE_FREE_SPACE
			{
				// Interior
				graficos.setColor(Color.BLACK);
				graficos.fillPolygon(hexagono);
				this.debug = 8;
			}
		
		}
	}


	@Override
	public String toString() {
		return "Tile [posX=" + this.posX + ", posY=" + this.posY + ", tileType=" + this.tileType + ", ocupado=" + this.ocupado
				+ ", objectoOcupado=" + this.objectoOcupado + ", tileId=" + this.tileId + " " + this.debug + "]";
	}
	
	
}
