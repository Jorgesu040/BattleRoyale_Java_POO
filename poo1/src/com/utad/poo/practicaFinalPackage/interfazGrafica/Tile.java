package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.*;

import com.utad.poo.practicaFinalPackage.items.*;
import com.utad.poo.practicaFinalPackage.personajes.*;
import java.awt.image.BufferedImage;



public class Tile 
{
	public static final Integer HEXAGON_RADIOUS = 40;
	public static final Integer HEXAGON_WIDTH = (int) (Math.sqrt(3) * Tile.HEXAGON_RADIOUS);
	public static final Integer HEXAGON_HEIGHT = 2 * Tile.HEXAGON_RADIOUS;
	public static final Double MAX_DISTANCE_LEGAL_MOVE = 80.0d;
	
	public static final Integer IMAGE_WIDTH = (int) (0.8 * HEXAGON_WIDTH); 
	public static final Integer IMAGE_HEIGHT = (int) (0.8 * HEXAGON_HEIGHT); 


	private Integer posX;
	private Integer posY;
	private Integer tileId;
	
	private TileType tileType;
	
	private Boolean ocupado;
	private Object objectoOcupado; // el objeto que esta ocupando el tile
	private Polygon hexagono;
	private boolean isHovered;
	private boolean containsSpecialImage;
	private BufferedImage specialImage;
	private boolean targetTile;

	
	public Tile(TileType type, Integer posX, Integer posY, Boolean ocupado, Object objectoOcupado, Integer id)
	{
		this.tileType = type;
		this.posX = posX;
		this.posY = posY;
		this.ocupado = ocupado;
		this.objectoOcupado = objectoOcupado;
		this.tileId = id;
		this.isHovered = false;
		this.containsSpecialImage = false;
		this.specialImage = null;
		this.targetTile = false;
	}
	

	public boolean contains(Point p) 
	{
		return this.hexagono.contains(p);
	}

	private Double getTileDistance(Tile tile)
	{
		Point tileCenter = new Point(tile.posX, tile.posY);
		
		Double rawDistance = new Point(this.posX, this.posY).distance(tileCenter);
	
		// Siempre da el mismo valor, 78.39
		return rawDistance;
	}
	
	public boolean isLegalMove(Tile tile)
	{
		Boolean obstacle = tile.getTileType().equals(TileType.TILE_OBSTACLE);
		Boolean ocupado = tile.getObjectoOcupado() instanceof Personaje;

		return this.getTileDistance(tile) <= Tile.MAX_DISTANCE_LEGAL_MOVE && !obstacle && !ocupado;
	}

	public boolean isLegalAction(Tile tile)
	{
		Boolean obstacle = tile.getTileType().equals(TileType.TILE_OBSTACLE);
		
		return this.getTileDistance(tile) <= Tile.MAX_DISTANCE_LEGAL_MOVE && !obstacle;
	}
	
	public void setTileObject(Object object)
	{
		this.objectoOcupado = object;
		this.ocupado = true;
	}
	
	public void removeTileObject()
	{
		if (this.objectoOcupado instanceof Item)
		{
			this.specialImage = null;
			this.containsSpecialImage = false;
		}

		this.objectoOcupado = null;
		this.ocupado = false;
	}

	public boolean isTrap()
	{
		return this.tileType.equals(TileType.TILE_TRAP_SET);
	}

	public void explodeTrap()
	{
		this.tileType = TileType.TILE_TRAP_EXPLODED;
		removeTileObject();
		this.specialImage = null;
		this.containsSpecialImage = false;
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
		
		
		// Dibujos especiales
		if (this.isHovered && !this.tileType.equals(TileType.TILE_OBSTACLE)) {
	        // Agregar borde rojo más grueso para indicar hover
	        graficos.setStroke(new BasicStroke(3)); 
	        graficos.setColor(new Color(108,70,49));          
	        graficos.drawPolygon(this.hexagono);    
	        graficos.setStroke(new BasicStroke(1)); 
	    }

		if (this.targetTile && !this.tileType.equals(TileType.TILE_OBSTACLE))
		{
			graficos.setColor(new Color(189, 145, 78));
			graficos.fillPolygon(this.hexagono);

			graficos.setStroke(new BasicStroke(3)); 
	        graficos.setColor(new Color(108,70,49));          
	        graficos.drawPolygon(this.hexagono);    
	        graficos.setStroke(new BasicStroke(1)); 
		}

		setTileImage(graficos);
	}
	
	private void setTileImage(Graphics2D graficos)
	{
		if (this.ocupado && this.objectoOcupado instanceof Personaje)
		{
			Personaje player = (Personaje) this.objectoOcupado;
			drawImage(player.getImagen(), graficos);

		} else if (this.containsSpecialImage)
		{
			drawImage(this.specialImage, graficos);
		}
	}

	private void drawImage(BufferedImage image, Graphics2D graficos)
	{
		Integer imageX = this.posX - Tile.IMAGE_WIDTH / 2;
		Integer imageY = this.posY - Tile.IMAGE_HEIGHT / 2;
	   
	   try {
		   graficos.drawImage(image, imageX, imageY, Tile.IMAGE_WIDTH, Tile.IMAGE_HEIGHT, null);
	   } catch (Exception e) {
		   System.out.println("Error with loading tile image");
		   e.printStackTrace();
	   }
	}

	public void setSpecialImage(BufferedImage image)
	{
		this.containsSpecialImage = true;
		this.specialImage = image;
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
		
		
			case TILE_SPAWN:
			{
				// Interior
				graficos.setColor(new Color(26, 118, 35));
				graficos.fillPolygon(hexagono);	
	
			} break;

			case TILE_TRAP_EXPLODED:
			{
				// Interior
				graficos.setColor(new Color(114, 68, 23));
				graficos.fillPolygon(hexagono);

				graficos.setStroke(new BasicStroke(3)); 
				graficos.setColor(new Color(114, 40, 40));        
				graficos.drawPolygon(hexagono);    
				graficos.setStroke(new BasicStroke(3));

			} break;

			case TILE_TRAP_SET:
			case TILE_LOOT:
			{
				// Interior
				graficos.setColor(new Color(32, 108, 159));
				graficos.fillPolygon(hexagono);

				graficos.setStroke(new BasicStroke(3)); 
				graficos.setColor(new Color(23, 77, 114));        
				graficos.drawPolygon(hexagono);    
				graficos.setStroke(new BasicStroke(3));
			} break;
			
			default:  // Se trata como un TILE_FREE_SPACE
			{
				// Interior
				graficos.setColor(new Color(44, 131, 58));
				graficos.fillPolygon(hexagono);

			}
		
		}

		if (this.objectoOcupado instanceof Personaje)
		{
			Personaje player = (Personaje) this.objectoOcupado;

			if (player.getIsAI())
			{
				graficos.setStroke(new BasicStroke(3)); 
				graficos.setColor(new Color(172, 103, 33));        
				graficos.drawPolygon(hexagono);    
				graficos.setStroke(new BasicStroke(3));
			}
			else
			{
				graficos.setStroke(new BasicStroke(3)); 
				graficos.setColor(new Color(155,50,120));          
				graficos.drawPolygon(hexagono);    
				graficos.setStroke(new BasicStroke(3)); 
			}
		}
	}

	public void setTargetTile(boolean isTarget)
	{
		this.targetTile = isTarget;
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
