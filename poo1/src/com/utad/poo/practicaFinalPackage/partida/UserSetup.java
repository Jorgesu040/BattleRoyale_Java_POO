package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.personajes.*;
import com.utad.poo.practicaFinalPackage.interfazGrafica.GraphicUserSetup;

public class UserSetup extends CharacterSetup {
    private GraphicUserSetup gui = new GraphicUserSetup();

    public Personaje askUserSettings() {

        Personaje personaje = null;

        do {
            String characterType = gui.selectCharacterType();
            if (characterType != null) {
                try {
                    String weapon = gui.selectWeapon(characterType);
                    if (weapon != null) {
                        String shield = gui.selectShield();
                        if (shield != null) {
                            personaje = createCharacter(characterType, weapon, shield);
                            gui.showCharacterCreated(characterType, weapon, shield);
                        } else {
                            gui.showError("Shield selection failed.");
                        }
                    } else {
                        gui.showError("Weapon selection failed.");
                    }
                } catch (Exception e) {
                    gui.showError("An error occurred: " + e.getMessage());
                }
            }
        } while (personaje == null);

        return personaje;
    }

}