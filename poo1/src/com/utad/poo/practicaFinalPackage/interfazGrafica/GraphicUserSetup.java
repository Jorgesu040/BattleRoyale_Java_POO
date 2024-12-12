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

import com.utad.poo.practicaFinalPackage.herramientas.*;
import com.utad.poo.practicaFinalPackage.personajes.*;

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
                showOptionStats();
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

    public void showOptionStats() {
        String estadisticasGuerro = "Guerrero:\n"
                + "Vida:" + Personaje.VIDA_DEFAULT + "pts. "
                + "Ataque:" + Guerrero.MOD_ATQ_INICIAL + "% "
                + "Defensa:" + Guerrero.MOD_DEF_INICIAL + "% "
                + "Ira Espartana:" + Guerrero.IRA_ESPARTANA_INICIAL + "%\n"
                + "¿Que es la Ira Espartana?\n"
                + "La Ira Espartana es una característica especial del Guerrero que le permite contraatacar con mayor probabilidad.\n";
        
        String estadisticasArquero = "Arquero:\n"
                + "Vida:" + Personaje.VIDA_DEFAULT + "pts. "
                + "Ataque:" + Arquero.ATAQUE_DEFAULT + "% "
                + "Defensa:" + Arquero.MOD_DEF_INICIAL + "% "
                + "Precisión:" + Arquero.PUNTERIA_INICIAL + "%\n"
                + "¿Que es la Puntería?\n"
                + "La Puntería es una característica especial del Arquero que le permite aumetar la probabilidad de que el enemigo no se retire.\n";

        String estadisticasMago = "Mago:\n"
                + "Vida:" + Personaje.VIDA_DEFAULT + "pts. "
                + "Ataque:" + Mago.MOD_ATQ_INICIAL + "% "
                + "Defensa:" + Mago.MOD_DEF_INICIAL + "% "
                + "Sabiduría:" + Mago.ATAQUE_CRITICO + "%\n"
                + "¿Cual es su especialidad?\n"
                + "El mago tiene una probabilidad de ataque crítico que aumenta con cada ataque y empieza con 2 pociones (ataque y defensa).\n";

        String tiposDeArmasGuerrero = "Armas de Guerrero:\n"
                + "Espada Bastarda: " + EspadaBastarda.DANYO + "pts de daño, " + EspadaBastarda.PRECISION + "% de precisión, " + EspadaBastarda.IRA_ESPARTANA_CONTRAATAQUE + "% de ira espartana de contraataque.\n"
                + "Lanza Puntiaguda: " + LanzaPuntiaguda.DANYO + "pts de daño, " + LanzaPuntiaguda.PRECISION + "% de precisión, " + LanzaPuntiaguda.IRA_ESPARTANA_CONTRAATAQUE + "% de ira espartana de contraataque.\n"
                + "Hacha Doble Filo: " + HachaDobleFilo.DANYO + "pts de daño, " + HachaDobleFilo.PRECISION + "% de precisión, " + HachaDobleFilo.IRA_ESPARTANA_CONTRAATAQUE + "% de ira espartana de contraataque.\n";

        String tiposDeArmasArquero = "Armas de Arquero:\n"
                + "Arco de Guerrilla: " + ArcoDeGuerrilla.DANYO + "pts de daño, " + ArcoDeGuerrilla.PRECISION + "% de precisión, " + ArcoDeGuerrilla.PUNTERIA + "% de puntería.\n"
                + "Arco de Precisión: " + ArcoDePrecision.DANYO + "pts de daño, " + ArcoDePrecision.PRECISION + "% de precisión, " + ArcoDePrecision.PUNTERIA + "% de puntería.\n"
                + "Ballesta: " + Ballesta.DANYO + "pts de daño, " + Ballesta.PRECISION + "% de precisión, " + Ballesta.PUNTERIA + "% de puntería.\n";

        String tiposDeArmasMago = "Armas de Mago:\n"
                + "Varita de Cristal: " + VaritaDeCristal.DANYO + "pts de daño, " + VaritaDeCristal.PRECISION + "% de precisión, " + VaritaDeCristal.PROB_CRITICO + "% de ataque crítico.\n"
                + "Bastón de Sabiduría: " + BastonDeSabiduria.DANYO + "pts de daño, " + BastonDeSabiduria.PRECISION + "% de precisión, " + BastonDeSabiduria.PROB_CRITICO + "% de ataque crítico.\n"
                + "Orbe Ancestral: " + OrbeAncestral.DANYO + "pts de daño, " + OrbeAncestral.PRECISION + "% de precisión, " + OrbeAncestral.PROB_CRITICO + "% de ataque crítico.\n";
        
        String tiposDeEscudos = "Escudos:\n"
                + "Escudo Ligero: " + EscudoLigero.DEFENSA + "% de defensa, " + EscudoLigero.BONUS_DE_RETIRADA + "% de bonificación de retirada.\n"
                + "Escudo Normal: " + EscudoNormal.DEFENSA + "% de defensa, " + EscudoNormal.BONUS_DE_RETIRADA + "% de bonificación de retirada.\n"
                + "Escudo Pesado: " + EscudoPesado.DEFENSA + "% de defensa, " + EscudoPesado.BONUS_DE_RETIRADA + "% de bonificación de retirada.\n";
        
        String separador = "--------------------------------\n";

        JOptionPane.showMessageDialog(null, "Estadísticas de los personajes:\n" + estadisticasGuerro + separador + estadisticasArquero + separador + estadisticasMago + "\n");
        JOptionPane.showMessageDialog(null, "Tipos de armas" + separador + tiposDeArmasGuerrero + separador + tiposDeArmasArquero + separador + tiposDeArmasMago + "\n");
        JOptionPane.showMessageDialog(null, "Tipos de escudos:\n" + tiposDeEscudos + "\n");
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
