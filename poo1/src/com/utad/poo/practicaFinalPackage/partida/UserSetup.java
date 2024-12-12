package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.personajes.*;
import com.utad.poo.practicaFinalPackage.herramientas.*;
import com.utad.poo.practicaFinalPackage.interfazGrafica.GraphicUserSetup;

public class UserSetup {
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

    private Arma stringToWeapon(String weapon) {
        Arma armaPersonaje;
        switch (weapon) {
            case "Espada Bastarda":
                armaPersonaje = new EspadaBastarda();
                break;
            case "Lanza Puntiaguda":
                armaPersonaje = new LanzaPuntiaguda();
                break;
            case "Hacha Doble Filo":
                armaPersonaje = new HachaDobleFilo();
                break;
            case "Arco de Guerrilla":
                armaPersonaje = new ArcoDeGuerrilla();
                break;
            case "Arco de Precisión":
                armaPersonaje = new ArcoDePrecision();
                break;
            case "Ballesta":    
                armaPersonaje = new Ballesta();
                break;
            case "Varita de Cristal":
                armaPersonaje = new VaritaDeCristal();
                break;
            case "Bastón de Sabiduría":
                armaPersonaje = new BastonDeSabiduria();
                break;
            case "Orbe Ancestral":
                armaPersonaje = new OrbeAncestral();
                break;
            default:
                armaPersonaje = null;
                break;
        }
        return armaPersonaje;
    }

    private Escudo stringToShield(String shield) {
        Escudo escudoPersonaje;
        switch (shield) {
            case "Escudo Ligero":
                escudoPersonaje = new EscudoLigero();
                break;
            case "Escudo Normal":
                escudoPersonaje = new EscudoNormal();
                break;
            case "Escudo Pesado":
                escudoPersonaje = new EscudoPesado();
                break;
            default:
                escudoPersonaje = null;
                break;
        }
        return escudoPersonaje;
    }

    private Personaje createCharacter(String characterType, String weapon, String shield) {
        Personaje personaje;
        Arma armaPersonaje = stringToWeapon(weapon);
        Escudo escudoPersonaje = stringToShield(shield);
        switch (characterType) {
            case "Guerrero":
                personaje = new Guerrero(armaPersonaje, escudoPersonaje);
                break;
            case "Arquero":
                personaje = new Arquero(armaPersonaje, escudoPersonaje);
                break;
            case "Mago":
                personaje = new Mago(armaPersonaje, escudoPersonaje);
                break;
            default:
                personaje = null;
                break;
        }
        return personaje;
    }
}