package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicUserSetup {

    public String selectCharacterType() {
        String[] characterTypes = {"Guerrero", "Arquero", "Mago"};
        String title = "Selección de Personaje";
        String message = "Elige tu personaje:";
        return showPanel(characterTypes, title, message);
    }

    public String selectWeapon(String characterType) {
        String[] weapons;
        switch (characterType) {
            case "Guerrero":
                weapons = new String[]{"Espada Bastarda", "Lanza Puntiaguda", "Hacha Doble Filo"};
                break;
            case "Arquero":
                weapons = new String[]{"Arco de Guerrilla", "Arco de Precisión", "Ballesta"};
                break;
            case "Mago":
                weapons = new String[]{"Varita de Cristal", "Bastón de Sabiduría", "Orbe Ancestral"};
                break;
            default:
                weapons = new String[]{};
                break;
        }
        String title = "Selección de Arma";
        String message = "Elige tu arma:";
        return showPanel(weapons, title, message);
    }

    public String selectShield() {
        String[] shields = {"Escudo Ligero", "Escudo Normal", "Escudo Pesado"};
        String title = "Selección de Escudo";
        String message = "Elige tu escudo:";
        return showPanel(shields, title, message);
    }

    private String showPanel(String[] opciones, String titulo, String mensaje) {
        // Crear el panel personalizado
        JPanel panel = new JPanel(new BorderLayout());

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem menuItemStats = new JMenuItem("Mostrar Estadísticas");

        menuItemStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphicHelp.showOptionStats();
            }
        });

        menuAyuda.add(menuItemStats);
        menuBar.add(menuAyuda);

        panel.add(menuBar, BorderLayout.NORTH);

        // Añadir el mensaje y las opciones
        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel(mensaje));
        panel.add(messagePanel, BorderLayout.CENTER);

        // Mostrar el diálogo personalizado
        return (String) JOptionPane.showInputDialog(
                null,
                panel,
                titulo,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);
    }

    public void showCharacterCreated(String characterType, String weapon, String shield) {
        JOptionPane.showMessageDialog(null, "Personaje creado: " + characterType + " con " + weapon + " y " + shield);
    }

    public void showCharacterNotCreated() {
        JOptionPane.showMessageDialog(null, "Personaje no creado");
    }

    public void showCharacterNotCreated(String message) {
        JOptionPane.showMessageDialog(null, "Personaje no creado: " + message);
    }

    public void showError(String string) {
        JOptionPane.showMessageDialog(null, string);
    }
}
