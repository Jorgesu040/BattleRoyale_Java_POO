/**
 * La clase `MapGenerator` es parte del paquete `com.utad.poo.practicaFinalPackage.interfazGrafica` y es responsable de generar y renderizar un mapa hexagonal dentro de una aplicación Swing en Java. Esta clase extiende `JPanel` y utiliza componentes personalizados de Swing para crear una interfaz de mapa interactiva.

 * **Resumen de la Clase `MapGenerator`:**

 * - **Propósito:** Crear y gestionar un mapa hexagonal generado proceduralmente, permitiendo la interacción con los tiles (hexágonos) individuales y la asignación de elementos del juego como personajes, enemigos, trampas y botín.

 * - **Variables Clave:**
   - `size`: Tamaño de la cuadrícula del mapa (número de tiles en cada fila y columna).
   - `tiles`: Lista que almacena todos los objetos `Tile` generados en el mapa.
   - `playerAmount`, `banditAmount`, `trapsAmount`, `lootAmount`: Cantidades de tiles especiales que representan jugadores, bandidos, trampas y botín, respectivamente.
   - `utilityFunctions`: Objeto que proporciona funciones utilitarias para generar valores aleatorios y cargar recursos.
   - `cargadorFichero`: Objeto para cargar datos desde un fichero XML si se requiere.
   - `bandidosGenerados`: Lista de personajes que representan a los bandidos generados en el mapa.

 * - **Constructores:**
   - Inicializan el mapa con tamaños y cantidades por defecto o personalizadas.
   - Configuran si el mapa se carga desde un fichero o se genera aleatoriamente.

 * **Principales Métodos:**

 * - `paintComponent(Graphics g)`:
   - Sobrescribe el método de `JPanel` para realizar el dibujo personalizado del mapa.
   - Llama a `renderMap(Graphics2D g2d)` para dibujar los tiles existentes en la lista `tiles`.

 * - `renderMap(Graphics2D g2d)`:
   - Establece el color de fondo del panel.
   - Itera sobre la lista de `tiles` y llama a `drawTile(Graphics2D g2d)` en cada uno para renderizarlos.

 * - `generateMap()`:
   - Calcula las posiciones de los tiles basándose en la geometría hexagonal.
   - Genera cada tile llamando a `generateRandomTile(int posX, int posY)`.
   - Después de generar todos los tiles, llama a `generateSpecialTiles()` para asignar tiles especiales como trampas, bandidos, botín y puntos de inicio de jugadores.

 * - `generateRandomTile(int posX, int posY)`:
   - Crea una nueva instancia de `Tile` con un tipo aleatorio y posición determinada.
   - Añade el tile a la lista `tiles`.

 * - `generateSpecialTiles()`:
   - Genera y asigna los tiles especiales como trampas, bandidos, botín y spawns de jugadores.
   - Llama a métodos privados como:
     - `generatePlayersTiles()`: Genera los tiles de inicio para los jugadores.
     - `generateBanditTiles()`: Genera tiles con bandidos y los coloca en el mapa.
     - `generateLootTiles()`: Genera tiles con botín y los coloca en el mapa.
     - `generateTrapsTiles()`: Genera tiles con trampas y los coloca en el mapa.

 * - `setPlayers(List<Personaje> players)`:
   - Asigna los jugadores a los tiles de spawn generados previamente.
   - Establece la ubicación de los jugadores en el mapa y marca los tiles como ocupados.

 * **Dependencias y Uso:**

 * - **Clase `Tile`**:
   - Representa los tiles hexagonales individuales en el mapa.
   - Métodos utilizados:
     - `drawTile(Graphics2D graficos)`: Renderiza el tile en el panel.
     - `setTileType(TileType tileType)`: Establece el tipo de tile.
     - `setTileObject(Object obj)`: Asigna un objeto (como `Personaje` o `Item`) al tile.
     - `setSpecialImage(Image img)`: Establece una imagen especial para tiles como botín o trampas.
     - `getTileType()`, `getOcupado()`: Métodos para obtener información del tile.

 * - **Enumeración `TileType`**:
   - Define los tipos posibles de tiles, tales como:
     - `TILE_FREE_SPACE`: Espacio libre donde se puede mover el personaje.
     - `TILE_OBSTACLE`: Obstáculo que no se puede atravesar.
     - `TILE_SPAWN`: Punto de inicio para los jugadores.
     - `TILE_SPAWN_AI`: Punto de inicio para los bandidos controlados por IA.
     - `TILE_TRAP_SET`: Tile que contiene una trampa.
     - `TILE_LOOT`: Tile que contiene botín.

 * - **Clases Auxiliares:**
   - `Utility`: Proporciona funciones para generar números aleatorios y cargar imágenes desde recursos.
   - `IniciarPartidaFichero`: Maneja la carga de configuraciones desde un fichero XML.
   - `ItemGenerator`: Genera instancias de `Item` aleatorias para botín y trampas.
   - `BanditSetup`: Genera instancias de `Personaje` que representan a los bandidos.

 * - **Componentes de Java Swing y AWT:**
   - `JPanel`: Clase base para el panel del mapa.
   - `Graphics2D`: Utilizado para dibujar formas y controlar el renderizado con mayor precisión.
   - `Color`: Para establecer colores en la interfaz.

 * **Resumen:**

 * La clase `MapGenerator` es esencial para la interfaz del mapa, combinando el renderizado gráfico y la generación procedural del mismo. Maneja la creación de tiles, la asignación de elementos del juego a los tiles y la configuración inicial del mapa según los parámetros dados o cargados desde un fichero.

 * Al organizar la lógica de generación dentro de `MapGenerator`, la aplicación separa responsabilidades, permitiendo que la clase `Tile` se centre en el comportamiento de los tiles individuales, mientras que `MapGenerator` gestiona el diseño general del mapa y la colocación de elementos del juego.

 * Esto permite crear un mapa interactivo donde cada tile puede tener características especiales y contener objetos como personajes o ítems, sentando las bases para desarrollar las mecánicas del juego sobre este mapa.

 */


package com.utad.poo.practicaFinalPackage.interfazGrafica;

import com.utad.poo.practicaFinalPackage.inout.*;
import com.utad.poo.practicaFinalPackage.partida.BanditSetup;
import com.utad.poo.practicaFinalPackage.personajes.*;
import com.utad.poo.practicaFinalPackage.items.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MapGenerator extends JPanel
{

	// Necesitado por JPanel
	private static final long serialVersionUID = 1L;
	
	public static final Integer DEFAULT_SPACING_X = 5; 
    public static final Integer DEFAULT_SPACING_Y = 5; 
    
    public static final Integer DEFAULT_TRAPS = 2;
    public static final Integer DEFAULT_BANDITS = 3;
    public static final Integer DEFAULT_LOOT = 3;
	public static final Integer DEFAULT_PLAYERS = 1;
    
    public static final Integer DEFAULT_SIZE = 7;

    public static final Integer OCCUPIED_SIZE_DEFAULT = Tile.HEXAGON_RADIOUS * 2;
    
    private Integer centerX;
    private Integer centerY;
    
    private Integer trapsAmount;
    private Integer playerAmount;
    private Integer banditAmount;
    private Integer lootAmount;
    
    private Utility utilityFunctions;
	private IniciarPartidaFichero cargadorFichero;
	private Boolean cargarDesdeFichero;
    
    // Variables empleadas para la generacion procedural
    private static Integer tileCounter = 0;
    
    private Integer size;
    
    private List<Tile> tiles;

	// pal turno
	private List<Personaje> bandidosGenerados;

	public MapGenerator(IniciarPartidaFichero cargador, Utility func, Boolean fichero)
	{
		this(MapGenerator.DEFAULT_PLAYERS, func, cargador, fichero);
	}

    public MapGenerator(Integer playersNumber, Utility func, IniciarPartidaFichero cargador, Boolean fichero)
    {
    	this(MapGenerator.DEFAULT_SIZE,
    			MapGenerator.DEFAULT_TRAPS,
    			playersNumber,
    			MapGenerator.DEFAULT_BANDITS,
    			MapGenerator.DEFAULT_LOOT,
    			func,
				cargador, 
				fichero);

    }
    
    public MapGenerator(Integer size, Integer traps, Integer players, Integer bandits, Integer loot, Utility func, IniciarPartidaFichero cargador, Boolean fromFichero)
    {
    	super();
    	this.size = size;
    	this.centerX = (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_X) * this.size / 2;
    	this.centerY = (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_Y) * this.size / 2;
    	this.trapsAmount = traps;
    	this.playerAmount = players;
    	this.banditAmount = bandits;
    	this.lootAmount = loot;
    	
    	this.utilityFunctions = func;
		this.cargadorFichero = cargador;
		this.cargarDesdeFichero = fromFichero;
    	
    	// Variables dedicadas al empleo de la generacion procedural
    	// No tocar por usuario bajo ningun concepto

    	this.tiles = new ArrayList<Tile>();
		this.bandidosGenerados = new ArrayList<Personaje>();

		getValuesFichero();
    	
    }
    
	private void getValuesFichero()
	{
		if (this.cargarDesdeFichero)
		{
			this.cargadorFichero.cargarDatosDesdeXML();

			this.setBanditAmount(this.cargadorFichero.getBanditCount());
			this.setTrapsAmount(this.cargadorFichero.getTrapCount());
			this.setLootAmount(this.cargadorFichero.getLootCount());
		}
	}
  
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
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

  
    public void generateMap()
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
            	generateRandomTile(x, y); 
            }
        }
		generateSpecialTiles();
		
    }
   
    
    private void generateRandomTile(Integer posX, Integer posY ) 
    {
        Tile newTile = null;


        MapGenerator.tileCounter++;
        // Crear el tile con el tipo generado
        newTile = new Tile(this.utilityFunctions.generateRandomTileType() , posX, posY, false, null, MapGenerator.tileCounter);
        
        this.tiles.add(newTile);
       
        
    }
    
   
    public synchronized void setPlayers(List<Personaje> players)
    {
    	for (Integer i = 0; i < this.playerAmount; i++)
    	{
    		Personaje player = players.get(i);
    		
    		for (Tile generatedTile : this.tiles)
    		{
    			if (generatedTile.getTileType().equals(TileType.TILE_SPAWN) && !generatedTile.getOcupado())
        		{
        			generatedTile.setTileObject(player);
					player.setUbicacionPersonaje(generatedTile);
        		}
    		}
    	}
    }
    
    private void generateSpecialTiles()
    {
    	Boolean loading = false;

    	
    	while(!loading)
    	{
    		
			try {
				// generate players
				generatePlayersTiles();
				// generate bandits
				generateBanditTiles();
				// generate loot
				generateLootTiles();
				// generate trap
				generateTrapsTiles();
				
				loading = true;
			} catch (Exception e) {
				System.out.println("Error en la generacion de tiles especiales");
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se ha implementado el jugador todavia", "Error" , JOptionPane.ERROR_MESSAGE);
			}
			
    	}
    }
    
    private void generatePlayersTiles() throws Exception
    {
		if (this.playerAmount > 1)
		{
			throw new Exception("No se ha implementado el multijugador");
		}


    	for(Integer i = 0; i < this.playerAmount; i++)
		{
			Integer random = this.utilityFunctions.generateRandom(1, MapGenerator.tileCounter);
			
			this.tiles.get(random).setTileType(TileType.TILE_SPAWN);
		}
    }
    
    private void generateBanditTiles()
    {
    	checkBanditAmount();
		
		BanditSetup banditGenerator = new BanditSetup();
		
		
		for(Integer i = 0; i < this.banditAmount; i++)
		{
			Integer random = this.utilityFunctions.generateRandom(1, MapGenerator.tileCounter);
			
			Personaje bandit = banditGenerator.createRandomBandit();

			this.tiles.get(random).setTileType(TileType.TILE_SPAWN_AI);
			this.tiles.get(random).setTileObject(bandit);
			bandit.setUbicacionPersonaje(this.tiles.get(random));
			this.bandidosGenerados.add(bandit);
		}
    }
    
    private void generateLootTiles()
    {

    	for(Integer i = 0; i < this.lootAmount; i++)
		{
			Integer random = this.utilityFunctions.generateRandom(1, MapGenerator.tileCounter);
			Item item = new ItemGenerator().generateRandomCharacterItem();


			this.tiles.get(random).setTileType(TileType.TILE_LOOT);
			this.tiles.get(random).setTileObject(item);
			this.tiles.get(random).setSpecialImage(this.utilityFunctions.getImage("cubo.png"));
		}
    }
    
    private void generateTrapsTiles()
    {
    	for(Integer i = 0; i < this.trapsAmount; i++)
		{
			Integer random = this.utilityFunctions.generateRandom(1, MapGenerator.tileCounter);
			Item trap = new ItemGenerator().generateRandomTrap();


			this.tiles.get(random).setTileType(TileType.TILE_TRAP_SET);
			this.tiles.get(random).setTileObject(trap);
			this.tiles.get(random).setSpecialImage(this.utilityFunctions.getImage("cubo.png"));
		}
    }
    
    private void checkBanditAmount()
    {
    	if (this.banditAmount > MapGenerator.tileCounter)
    	{
    		this.setBanditAmount( (int) (this.banditAmount / 8));;
    	}
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


	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
    
	 public List<Tile> getTiles() {
	        return this.tiles;
	    }
    
	public List<Personaje> getBandidos()
	{
		return this.bandidosGenerados;
	}

}
