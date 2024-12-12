package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;



public class Tile 
{
	public static final Integer HEXAGON_RADIOUS = 40;
	public static final Integer HEXAGON_WIDTH = (int) (Math.sqrt(3) * Tile.HEXAGON_RADIOUS);
	public static final Integer HEXAGON_HEIGHT = 2 * Tile.HEXAGON_RADIOUS;
	public static final Double MAX_DISTANCE_LEGAL_MOVE = 80.0d;

	private Integer posX;
	private Integer posY;
	private Integer tileId;
	
	private TileType tileType;
	
	private Boolean ocupado;
	private Object objectoOcupado; // el objeto que esta ocupando el tile
	private Polygon hexagono;
	private boolean isHovered;

	private List<Integer> tileConection; // cantidad de conexiones que tiene este tile con otros
	
	public Tile(TileType type, Integer posX, Integer posY, Boolean ocupado, Object objectoOcupado, Integer id)
	{
		this.tileType = type;
		this.posX = posX;
		this.posY = posY;
		this.ocupado = ocupado;
		this.objectoOcupado = objectoOcupado;
		this.tileId = id;
		this.isHovered = false;
		
		this.tileConection = new ArrayList<Integer>();
	}
	
	public void setTileConection(Integer tileId)
	{
		this.tileConection.add(tileId);
	}
	
	public boolean contains(Point p) 
	{
		return this.hexagono.contains(p);
	}

	// ni grafos ni pollas, a la cuenta de la vieja
	private Double getTileDistance(Tile tile)
	{
		Point tileCenter = new Point(tile.posX, tile.posY);
		
		Double rawDistance = new Point(this.posX, this.posY).distance(tileCenter);
	
		// Siempre da el mismo valor, 78.39
		return rawDistance;
	}
	
	public boolean isLegalMove(Tile tile)
	{
		return this.getTileDistance(tile) <= Tile.MAX_DISTANCE_LEGAL_MOVE && !tile.getTileType().equals(TileType.TILE_OBSTACLE);
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
		
		if (this.isHovered && !this.tileType.equals(TileType.TILE_OBSTACLE)) {
	        // Agregar borde rojo más grueso para indicar hover
	        graficos.setStroke(new BasicStroke(3)); 
	        graficos.setColor(new Color(108,70,49));          
	        graficos.drawPolygon(this.hexagono);    
	        graficos.setStroke(new BasicStroke(1)); 
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


			} break;

			case TILE_OBSTACLE:
			{
				// Interior
				graficos.setColor(new Color(76, 143, 220));
				graficos.fillPolygon(hexagono);


			} break;
		
			
			case TILE_LOOT:
			{
				// Interior
				graficos.setColor(Color.PINK);
				graficos.fillPolygon(hexagono);

				
			} break;
		
			case TILE_SPAWN:
			{
				// Interior
				graficos.setColor(Color.BLACK);
				graficos.fillPolygon(hexagono);	

				
			} break;
		
			case TILE_SPAWN_AI:
			{
				// Interior
				graficos.setColor(Color.ORANGE);
				graficos.fillPolygon(hexagono);

				
			} break;
			
			case TILE_TRAP_SET:
			{
				// Interior
				graficos.setColor(Color.RED);
				graficos.fillPolygon(hexagono);

				
			} break;
			
			case TILE_TRAP_IDLE:
			{
				// Interior
				graficos.setColor(Color.GRAY);
				graficos.fillPolygon(hexagono);

			} break;
				
			case TILE_TRAP_EXPLODED:
			{
				// Interior
				graficos.setColor(Color.DARK_GRAY);
				graficos.fillPolygon(hexagono);

			} break;
			
			default:  // Se trata como un TILE_FREE_SPACE
			{
				// Interior
				graficos.setColor(new Color(44, 131, 58));
				graficos.fillPolygon(hexagono);

			}
		
		}
	}

	public void setHovered(boolean hovered) 
	{
        this.isHovered = hovered;
    }

	public Boolean getHovered()
	{
		return this.isHovered;
	}
	
	public Integer getTileId()
	{
		return this.tileId;
	}
	
	public Integer getPosX() {
		return this.posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return this.posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public TileType getTileType() {
		return this.tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

	public Boolean getOcupado() {
		return this.ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Object getObjectoOcupado() {
		return this.objectoOcupado;
	}

	public void setObjectoOcupado(Object objectoOcupado) {
		this.objectoOcupado = objectoOcupado;
	}

	public Polygon getHexagono() {
		return this.hexagono;
	}

	public void setHexagono(Polygon hexagono) {
		this.hexagono = hexagono;
	}

	@Override
	public String toString() 
	{
		return "Tile [posX=" + this.posX + ", posY=" + this.posY + ", tileType=" + this.tileType + ", ocupado=" + this.ocupado
				+ ", objectoOcupado=" + this.objectoOcupado + ", tileId=" + this.tileId + ", isHovered=" + this.isHovered + "]";
	}
	
	
}
