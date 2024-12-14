package com.utad.poo.practicaFinalPackage.interfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.utad.poo.practicaFinalPackage.items.Item;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class ItemsButton extends JButton {

    Item itemAsociado;

    public ItemsButton(Item itemAsociado, Personaje jugador, JPanel inventoryPanel, GraphicWindowManager graphicWindowManager) {
        super(itemAsociado.getNombre());
        this.itemAsociado = itemAsociado;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemAsociado.usar(jugador);
                // Deshabilitar el botón
                setEnabled(false);
                // Actualizar la ventana
                inventoryPanel.updateUI();
                graphicWindowManager.updateStatsPanel(jugador);

            }
        });
    };
    
}
