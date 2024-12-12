/**
 * El archivo MapGenerator.java es parte del paquete com.utad.poo.practicaFinalPackage.interfazGrafica y es responsable de generar y renderizar un mapa hexagonal dentro de una aplicación Swing en Java. Esta clase extiende `JPanel` y utiliza componentes personalizados y de Swing para crear una interfaz de mapa interactiva.
 *
 * ### Resumen de la clase MapGenerator
 *
 * - **Propósito**: Crear y gestionar un mapa hexagonal procedimentalmente generado, permitiendo la interacción con los tiles (hexágonos) individuales.
 *
 * - **Variables clave**:
 *   - `size`: Tamaño de la cuadrícula del mapa (número de tiles).
 *   - `centerX`, `centerY`: Coordenadas centrales desde donde se dibuja el mapa.
 *   - `tiles`: Una lista que almacena todos los objetos `Tile` generados en el mapa.
 *   - `playerAmount`, `banditAmount`, `trapsAmount`: Cantidades de tiles especiales que representan spawns de jugadores, bandidos y trampas.
 *   - Contadores estáticos como `tileCounter`, `trapCounter`, `playerCounter`, `banditCounter` para rastrear la creación de tiles.
 *
 * - **Constructores**:
 *   - Permite inicializar el mapa con tamaños y cantidades por defecto o personalizadas.
 *   - Configura el listener de ratón para permitir la interacción con los tiles.
 *
 * ### Métodos principales
 *
 * - **`paintComponent(Graphics g)`**:
 *   - Sobreescribe el método de `JPanel` para realizar el dibujo personalizado.
 *   - Llama a `renderGrid(g2d)` y `renderMap(g2d)` para dibujar los tiles y el fondo.
 *
 * - **`renderGrid(Graphics2D g2d)`**:
 *   - Calcula las posiciones de los tiles basándose en geometría hexagonal.
 *   - Itera a través de filas y columnas, posicionando cada tile correctamente.
 *   - Para cada posición, llama a `generateRandomTile(x, y, g2d)`.
 *
 * - **`renderMap(Graphics g2d)`**:
 *   - Establece el color de fondo del panel.
 *   - Puede expandirse para renderizar elementos adicionales del mapa.
 *
 * - **`generateRandomTile(int posX, int posY, Graphics2D g2d)`**:
 *   - Determina el tipo de tile a crear, pudiendo colocar tiles especiales como spawns o trampas.
 *   - Crea una nueva instancia de `Tile` con el tipo y posición determinados.
 *   - Añade el tile a la lista `tiles` y lo dibuja usando `newTile.drawTile(g2d)`.
 *
 * - **`generateRandomTileType()`**:
 *   - Genera aleatoriamente un `TileType`, excluyendo ciertos tipos especiales como spawns y trampas, para asegurar una distribución variada en el mapa.
 *
 * - **`handleTileClick(MouseEvent click)`**:
 *   - Responde a eventos de clic del ratón.
 *   - Verifica si un tile contiene el punto clicado y muestra información utilizando `JOptionPane`.
 *
 * ### Dependencias y su uso
 *
 * - **Clase `Tile`** (`Tile.java`):
 *   - Representa los tiles hexagonales individuales en el mapa.
 *   - **Métodos utilizados**:
 *     - `drawTile(Graphics2D graficos)`: Renderiza el tile en el panel.
 *     - `contains(Point p)`: Verifica si un punto está dentro del área del tile.
 *   - Cada `Tile` contiene propiedades como tipo (`tileType`), posición (`posX`, `posY`) y estado de ocupación.
 *
 * - **Enum `TileType`** (`TileType.java`):
 *   - Define los tipos posibles de tiles (e.g., `TILE_FREE_SPACE`, `TILE_OBSTACLE`, `TILE_SPAWN`, etc.).
 *   - Se usa para asignar comportamientos y apariencias a los tiles según su tipo.
 *
 * - **Componentes de Java Swing**:
 *   - `JPanel`: Clase base para el panel del mapa.
 *   - `Graphics2D`: Utilizado para dibujar formas y controlar el renderizado con mayor precisión.
 *   - `MouseAdapter` y `MouseEvent`: Manejan las interacciones del usuario con los tiles del mapa.
 *
 * - **Clases utilitarias**:
 *   - `Color`, `Polygon`, `Point`: Ayudan en el dibujo y detección de interacciones con los tiles.
 *   - `List`, `ArrayList`: Gestionan colecciones de objetos `Tile`.
 *
 * ### Resumen
 *
 * La clase `MapGenerator` es esencial para la interfaz del mapa, combinando el renderizado gráfico y la interacción con el usuario. Utiliza:
 *
 * - **Clases personalizadas**:
 *   - `Tile`: Maneja el comportamiento y renderizado de cada tile individual.
 *   - `TileType`: Enumera los diferentes tipos de tiles disponibles.
 *
 * - **Bibliotecas de Java**:
 *   - Componentes de Swing para la interfaz gráfica.
 *   - Clases de AWT para gráficos y manejo de eventos.
 *
 * Al organizar la lógica de generación del mapa dentro de `MapGenerator`, la aplicación separa responsabilidades, permitiendo que `Tile` se centre en el comportamiento de los tiles individuales y `MapGenerator` en gestionar el diseño general del mapa y las interacciones.
 *
 * Esto permite crear un mapa interactivo donde cada tile puede responder a acciones del usuario, como clics, y mostrar información relevante, sentando las bases para desarrollar las mecánicas del juego encima de este mapa.
 *
 */


package com.utad.poo.practicaFinalPackage.interfazGrafica;

import com.utad.poo.practicaFinalPackage.personajes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;


/*
 * Esta clase se tiene que hacer en condiciones
 * Solo se encargara de construir en una ventana todos los tiles
 * Sera la clase tile exclusivamente quien se encargara de dibujarse
 * Por el momento esta clase es puro testing
 */

public class MapGenerator extends JPanel
{

	// Necesitado por JPanel
	private static final long serialVersionUID = 1L;
	
	public static final Integer DEFAULT_SPACING_X = 5; 
    public static final Integer DEFAULT_SPACING_Y = 5; 
    
    public static final Integer DEFAULT_TRAPS = 2;
    public static final Integer DEFAULT_BANDITS = 3;
    public static final Integer DEFAULT_LOOT = 3;
    
    public static final Integer DEFAULT_SIZE = 7;

    public static final Integer OCCUPIED_SIZE_DEFAULT = Tile.HEXAGON_RADIOUS * 2;
    
    private Integer centerX;
    private Integer centerY;
    
    private Integer trapsAmount;
    private Integer playerAmount;
    private Integer banditAmount;
    private Integer lootAmount;
    
   
    
    // Variables empleadas para la generacion procedural
    private static Integer tileCounter = 0;
    
    private Integer size;
    
    private List<Tile> tiles;
    private Boolean firstGeneration;
    private List<Integer> generatedSpecialTiles;


    public MapGenerator(Integer playersNumber)
    {
    	this(MapGenerator.DEFAULT_SIZE,
    			MapGenerator.DEFAULT_TRAPS,
    			playersNumber,
    			MapGenerator.DEFAULT_BANDITS,
    			MapGenerator.DEFAULT_LOOT);
    }
    
    public MapGenerator(Integer size, Integer traps, Integer players, Integer bandits, Integer loot)
    {
    	super();
    	this.size = size;
    	this.centerX = (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_X) * this.size / 2;
    	this.centerY = (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_Y) * this.size / 2;
    	this.trapsAmount = traps;
    	this.playerAmount = players;
    	this.banditAmount = bandits;
    	this.lootAmount = loot;
    	
    	// Variables dedicadas al empleo de la generacion procedural
    	// No tocar por usuario bajo ningun concepto
    	this.firstGeneration = false;
    	this.generatedSpecialTiles = new ArrayList<Integer>();
    	
    	this.tiles = new ArrayList<Tile>();
    	
    }
    
   
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Solo permite la generacion del mapa una vez en toda la ejecucion
        if (this.firstGeneration.equals(false))
        {
        	   generateMap(g2d);
        	   generateSpecialTiles();
        	   this.firstGeneration = true;
        }
     
        renderMap(g2d);
       
    }
    
   
    private void renderMap(Graphics2D g2d)
    {
    	super.setBackground(new Color(76, 143, 220));
    	
    	for (Tile myTile: this.tiles)
     	{
    		myTile.drawTile(g2d);
     	}
    }

  
    private void generateMap(Graphics2D g2d)
    {
    	Double ang30 = Math.toRadians(30);
        Double xOff = Math.cos(ang30) * (Tile.HEXAGON_RADIOUS + MapGenerator.DEFAULT_SPACING_X);
        Double yOff = Math.sin(ang30) * (Tile.HEXAGON_RADIOUS + MapGenerator.DEFAULT_SPACING_Y);
        Integer half = this.size / 2;
    	
    	for (Integer row = 0; row < this.size; row++) 
        {
    		Integer cols = this.size - java.lang.Math.abs(row - half);
    		
            for (Integer col = 0; col < cols; col++) 
            {
                 Integer x = (int) (this.centerX + xOff * (col * 2 + 1 - cols));
                 Integer y = (int) (this.centerY + yOff * (row - half) * 3);
            	
               
                 generateRandomTile(x, y, g2d);
                
                 
            }
        }
    }
   
    
    private void generateRandomTile(Integer posX, Integer posY, Graphics2D g2d) 
    {
        Tile newTile = null;
        TileType tileType;


        MapGenerator.tileCounter++;
        // Crear el tile con el tipo generado
        newTile = new Tile(generateRandomTileType() , posX, posY, false, null, MapGenerator.tileCounter);
        
        this.tiles.add(newTile);
       
        
    }
    
    private Integer generateRandom(Integer min, Integer max)
    {
    	Random r = new Random();
    	Integer randomNum;

        do {
            randomNum = r.nextInt(max - min) + min; 
        } while (this.generatedSpecialTiles.contains(randomNum)); 

        this.generatedSpecialTiles.add(randomNum); 
        return randomNum;
    }
    
    public void setPlayers(List<Personaje> players)
    {
    	for (Integer i = 0; i < this.playerAmount; i++)
    	{
    		Personaje player = players.get(i);
    		
    		for (Tile generatedTile : this.tiles)
    		{
    			if (generatedTile.getTileType().equals(TileType.TILE_SPAWN) && !generatedTile.getOcupado())
        		{
        			generatedTile.setTileObject(player);
        		}
    		}
    	}
    }
    
    private void generateSpecialTiles()
    {
    	Boolean loadingPlayerSpawn = false;
    	Boolean loadingBanditSpawn = false;
    	Boolean loadingLootSpawn = false;
    	Boolean loadingTrapSpawn = false;
    	
    	while(!loadingPlayerSpawn && !loadingBanditSpawn && !loadingLootSpawn && !loadingTrapSpawn)
    	{
    		// generate players
    		for(Integer i = 0; i < this.playerAmount; i++)
    		{
    			Integer random = generateRandom(1, MapGenerator.tileCounter);
    			
    			this.tiles.get(random).setTileType(TileType.TILE_SPAWN);
    		}
    		loadingPlayerSpawn = true;
    		
    		// generate bandits
    		for(Integer i = 0; i < this.banditAmount; i++)
    		{
    			Integer random = generateRandom(1, MapGenerator.tileCounter);
    			
    			this.tiles.get(random).setTileType(TileType.TILE_SPAWN_AI);
    			//this.tiles.get(random).setTileObject(bandit);
    		}
    		loadingBanditSpawn = true;
    		
    		// generate loot
    		for(Integer i = 0; i < this.lootAmount; i++)
    		{
    			this.tiles.get(generateRandom(1, MapGenerator.tileCounter)).setTileType(TileType.TILE_LOOT);;
    		}
    		loadingLootSpawn = true;
    		
    		// generate trap
    		for(Integer i = 0; i < this.trapsAmount; i++)
    		{
    			this.tiles.get(generateRandom(1, MapGenerator.tileCounter)).setTileType(TileType.TILE_TRAP_SET);;
    		}
    		loadingTrapSpawn = true;
    	}
    }
    
    private TileType generateRandomTileType() 
    {
        TileType[] values = TileType.values();
        TileType result = null;
        
   
        Integer randomIndex = this.generateSimpleRandom(values.length);
        
        if (randomIndex < (values.length / 4))
        {
        	result = TileType.TILE_OBSTACLE;
        }
        else
        {
        	result = TileType.TILE_FREE_SPACE;
        }
        
      
        
        return result;
    }
    
    private Integer generateSimpleRandom(Integer lenght)
    {
    	return (int) (Math.random() * lenght);
    }
    
   
    
    public Integer getThisSize() {
    	return this.size;
    }

	public Integer getCenterX() {
		return centerX;
	}

	public void setCenterX(Integer centerX) {
		this.centerX = centerX;
	}

	public Integer getCenterY() {
		return centerY;
	}

	public void setCenterY(Integer centerY) {
		this.centerY = centerY;
	}

	public Integer getTrapsAmount() {
		return trapsAmount;
	}

	public void setTrapsAmount(Integer trapsAmount) {
		this.trapsAmount = trapsAmount;
	}

	public Integer getPlayerAmount() {
		return playerAmount;
	}

	public void setPlayerAmount(Integer playerAmount) {
		this.playerAmount = playerAmount;
	}

	public Integer getBanditAmount() {
		return banditAmount;
	}

	public void setBanditAmount(Integer banditAmount) {
		this.banditAmount = banditAmount;
	}

	public Integer getLootAmount() {
		return lootAmount;
	}

	public void setLootAmount(Integer lootAmount) {
		this.lootAmount = lootAmount;
	}

	public static Integer getTileCounter() {
		return tileCounter;
	}

	public static void setTileCounter(Integer tileCounter) {
		MapGenerator.tileCounter = tileCounter;
	}

	public Boolean getFirstGeneration() {
		return firstGeneration;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
    
	 public List<Tile> getTiles() {
	        return this.tiles;
	    }
    
}
