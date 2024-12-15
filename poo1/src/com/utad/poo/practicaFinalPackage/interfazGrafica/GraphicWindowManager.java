package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.utad.poo.practicaFinalPackage.items.Item;
import com.utad.poo.practicaFinalPackage.partida.EmpezarTurnoEventListener;
import com.utad.poo.practicaFinalPackage.personajes.EstadoPersonaje;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GraphicWindowManager {

    JFrame frame;
    MapController mapController;
    MapGenerator panel;

    private JPanel accionesPersonaje;
    private JPanel panelItemsPersonaje;
    private JPanel statsPersonaje;
    private JPanel centerPanel;
    private JPanel bottomJPanel;

    private JButton attackButton;
    private JButton defendButton;
    private JButton retreatButton;
    private JButton moveButton;
    private JButton startTurnoButton;

    private List<JButton> stats;

    private EmpezarTurnoEventListener empezarTurnoEventListener;

    // Testing
    public static void main(String[] args) {
        MapGenerator panel = new MapGenerator(7, 1, 1, 2, 4, new Utility());
        new GraphicWindowManager(panel);
    }

    public GraphicWindowManager(MapGenerator panel) {
        this.frame = setupFrame();
        this.panel = panel;
        this.mapController = new MapController(this.panel);
        this.stats = new ArrayList<JButton>();
    }

    public void setupGame(Personaje jugador) {
        setupLayout(this.frame, this.panel, this.panel.getThisSize(), jugador);
        initializeFrame(this.frame);
    }

    private JFrame setupFrame() {
        JFrame frame = new JFrame("Battle Royale");

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Ayuda");
        JMenuItem actionsItem = new JMenu("Acciones");

        JMenuItem statsItem = new JMenuItem("Estadisticas de personajes, armas y escudos");
        JMenuItem playItem = new JMenuItem("Instrucciones de juego");
        JMenuItem exitItem = new JMenuItem("Salir");
        JMenuItem logsItem = new JMenuItem("Crear logs");

        helpMenu.add(statsItem);
        helpMenu.add(playItem);
        actionsItem.add(exitItem);
        actionsItem.add(logsItem);

        menuBar.add(helpMenu);
        menuBar.add(actionsItem);

        MenuListener menuListener = new MenuListener();
        statsItem.addActionListener(menuListener);
        playItem.addActionListener(menuListener);
        exitItem.addActionListener(menuListener);
        logsItem.addActionListener(menuListener);

        frame.setJMenuBar(menuBar);

        return frame;
    }

    public void updateInventoryPanel(Personaje jugador) {

        List<Item> items = jugador.getItems();
        // Borra todos los elementos del panel westPanel
        panelItemsPersonaje.removeAll();
        // Añade los items del jugador al panel westPanel
        if (items.isEmpty()) {
            panelItemsPersonaje.add(new JButton("Tu personaje no tiene items"));
        } else {
            for (Item item : items) {
                panelItemsPersonaje.add((new ItemsButton(item, jugador, panelItemsPersonaje, this)));
            }
        }

        panelItemsPersonaje.revalidate(); // Informa al panel que se ha modificado su contenido
        panelItemsPersonaje.updateUI();
    }

    public void updateActionsPanel() {
        attackButton.setEnabled(true);
        defendButton.setEnabled(true);
        retreatButton.setEnabled(true);
        moveButton.setEnabled(true);
        accionesPersonaje.revalidate();
        accionesPersonaje.updateUI();
    }

    public void updateStatsPanel(Personaje jugador) {
        stats.get(0).setText("Vida: " + jugador.getVida() + " puntos");
        stats.get(1).setText("Ataque: " + jugador.getAtaque() + "%");
        stats.get(2).setText("Defensa: " + jugador.getDefensa() + "%");
        stats.get(3).setText("Habilidad Especial: " + jugador.getSpecialAbility() + "%");

        statsPersonaje.revalidate();
        statsPersonaje.updateUI();
    }

    private void setupLayout(JFrame frame, MapGenerator panel, int panelSize, Personaje jugador) {
        frame.setLayout(new BorderLayout());

        /// *** PANEL DE ESTADISTICAS *** ///
        statsPersonaje = new JPanel(new GridLayout(3, 1));
        
        JPanel playerStats = new JPanel(new GridLayout(1, 4));
        stats.add(new JButton("Vida: " + jugador.getVida() + " puntos"));
        stats.add(new JButton("Ataque: " + jugador.getAtaque() + "%"));
        stats.add(new JButton("Defensa: " + jugador.getDefensa() + "%"));
        stats.add(new JButton("Habilidad Especial: " + jugador.getSpecialAbility()));

        for (JButton stat : stats) {
            playerStats.add(stat);
        }

        
        // Stats que no cambian
        JPanel statsArma = new JPanel(new GridLayout(1, 3));
        statsArma.add(new JButton("Arma: " + jugador.getArma().getNombre()));
        statsArma.add(new JButton("Daño-Precision: " + jugador.getArma().getDanio() + "-"
        + jugador.getArma().getPrecision()));
        statsArma.add(new JButton("Modificador a la habilidad especial: " + jugador.getArma().getSpecialEffect()));
        
        JPanel statsEscudo = new JPanel(new GridLayout(1, 2));
        statsEscudo.add(new JButton("Escudo: " + jugador.getEscudo().getNombre()));
        statsEscudo.add(new JButton("Defensa-Modificador Retirada: " + jugador.getEscudo().getDefensa() + "-"
        + jugador.getEscudo().getProbabilidadEscape()));
        
        
        statsPersonaje.add(playerStats);
        statsPersonaje.add(statsArma);
        statsPersonaje.add(statsEscudo);

        frame.add(BorderLayout.NORTH, statsPersonaje);

        /// *** PANEL DE ACCIONES *** ///
        accionesPersonaje = new JPanel(new GridLayout(4, 1));
        attackButton = new AttackButton(jugador);
        defendButton = new DefendButton(jugador);
        retreatButton = new RetreatButton(jugador);
        moveButton = new MoveButton(jugador);

        this.addCharacterOptionsListeners(jugador);

        accionesPersonaje.add(attackButton);
        accionesPersonaje.add(defendButton);
        accionesPersonaje.add(retreatButton);
        accionesPersonaje.add(moveButton);

        frame.add(BorderLayout.EAST, accionesPersonaje);

        /// *** PANEL DE ITEMS *** ///
        panelItemsPersonaje = new JPanel(new GridLayout(5, 1));
        frame.add(BorderLayout.WEST, panelItemsPersonaje);

        /// *** PANEL DE INICIAR TURNO *** ///
        bottomJPanel = new JPanel();
        startTurnoButton = new StartTurnoButton();
        startTurnoButton.addActionListener(new StartTurnoButtonListener(jugador));
        bottomJPanel.add(startTurnoButton);
        frame.add(BorderLayout.SOUTH, bottomJPanel);

        /// *** PANEL DE MAPA *** ///
        centerPanel = new JPanel(new GridBagLayout());
        int width = panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_X);
        int height = panelSize * (MapGenerator.OCCUPIED_SIZE_DEFAULT + MapGenerator.DEFAULT_SPACING_Y);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        centerPanel.add(panel);
        frame.add(BorderLayout.CENTER, centerPanel);
        centerPanel.setBackground(new java.awt.Color(76, 143, 200));
    }

    private void initializeFrame(JFrame frame) {
        frame.setSize(1080, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setGameEventListener(EmpezarTurnoEventListener empezarTurnoEventListener) {
        this.empezarTurnoEventListener = empezarTurnoEventListener;
    }

    private void addCharacterOptionsListeners(Personaje jugador) {
        attackButton.addActionListener(new onClickDisableButtons(EstadoPersonaje.ATACANDO, jugador));
        defendButton.addActionListener(new onClickDisableButtons(EstadoPersonaje.DEFENDIENDO, jugador));
        retreatButton.addActionListener(new onClickDisableButtons(EstadoPersonaje.RETIRANDOSE, jugador));
        moveButton.addActionListener(new onClickDisableButtons(EstadoPersonaje.MOVIENDOSE, jugador));
    }

    class onClickDisableButtons implements ActionListener {

        private EstadoPersonaje estado;
        private Personaje jugador;

        public onClickDisableButtons(EstadoPersonaje estado, Personaje jugador) {
            this.estado = estado;
            this.jugador = jugador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            attackButton.setEnabled(false);
            defendButton.setEnabled(false);
            retreatButton.setEnabled(false);
            moveButton.setEnabled(false);

            jugador.setEstado(estado);

            // TODO: añadir un boton para iniciar las acciones
            // TODO: opcional añadir una seccion de log (ventana emergente scrollable) para
            // mostrar las acciones realizadas

            // TODO: isLegalMove debería ser llamado cuando se selecciona un tile, no un
            // botón, si no crashea

            // jugador.getUbicacionPersonaje().removeTileObject();
            // jugador.setUbicacionPersonaje(GraphicWindowManager.this.mapController.getSelectedTile());
            // GraphicWindowManager.this.mapController.getSelectedTile().setTileObject(jugador);

            GraphicWindowManager.this.updateActionsPanel();
        }

    }

    class StartTurnoButtonListener implements ActionListener {

        private Personaje jugador;

        public StartTurnoButtonListener(Personaje jugador) {
            this.jugador = jugador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (jugador.getEstado() != EstadoPersonaje.NADA && jugador.getTargetTile() != null) {
                if (jugador.getUbicacionPersonaje().isLegalMove(GraphicWindowManager.this.mapController.getSelectedTile())) {
                    try {
                        empezarTurnoEventListener.onExecuteTurn();
                    } catch (Exception ex) {
                        System.err.println("Error al ejecutar el turno, no se ha podido ejecutar el turno");
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Movimiento no permitido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (jugador.getEstado() == EstadoPersonaje.NADA) {
                JOptionPane.showMessageDialog(null, "Selecciona una acción", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (jugador.getTargetTile() == null) {
                JOptionPane.showMessageDialog(null, "Selecciona un objetivo", "Error", JOptionPane.ERROR_MESSAGE);
            }

            
        }

        
    }

    class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            if (item.getText().equals("Salir")) {
                System.exit(0);
            }

            if (item.getText().equals("Estadisticas de personajes, armas y escudos")) {
                // Update to use GraphicHelp
                GraphicHelp.showOptionStats();
            }

            if (item.getText().equals("Instrucciones de juego")) {
                GraphicHelp.showOptionHelp();
            }

            if (item.getText().equals("Crear logs")) {
                // Show unimplemented dialog
                JOptionPane.showMessageDialog(null, "Funcionalidad no implementada", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void updateMapPanel() {
        this.panel.repaint();
        return;
    }

    public MapController getMapController() {
        return mapController;
    }
}
