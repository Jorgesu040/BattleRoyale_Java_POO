package com.utad.poo.practicaFinalPackage.partida;

import com.utad.poo.practicaFinalPackage.personajes.*;
import com.utad.poo.practicaFinalPackage.herramientas.*;

public abstract class CharacterSetup {

    protected Arma stringToWeapon(String characterType, String weapon) {
        Arma armaPersonaje;
        switch (characterType) {
            case "Guerrero":
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
                    default:
                        armaPersonaje = null;
                        throw new IllegalArgumentException("Invalid weapon for character type, tried to create invalid weapon: " + weapon + " for " + characterType);
                }
                break;
            case "Arquero":
                switch (weapon) {
                    case "Arco de Guerrilla":
                        armaPersonaje = new ArcoDeGuerrilla();
                        break;
                    case "Arco de Precisión":
                        armaPersonaje = new ArcoDePrecision();
                        break;
                    case "Ballesta":
                        armaPersonaje = new Ballesta();
                        break;
                    default:
                        armaPersonaje = null;
                        throw new IllegalArgumentException("Invalid weapon for character type, tried to create invalid weapon: " + weapon + " for " + characterType);
                }
                break;
            case "Mago":
                switch (weapon) {
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
                        throw new IllegalArgumentException("Invalid weapon for character type, tried to create invalid weapon: " + weapon + " for " + characterType);
                }
                break;
            default:
                armaPersonaje = null;
                throw new IllegalArgumentException("Invalid character type, character type: " + characterType + " is not a valid character type");
        }
        return armaPersonaje;
    }

    protected Escudo stringToShield(String shield) {
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
                throw new IllegalArgumentException("Invalid shield type, tried to create invalid shield: " + shield);
        }
        return escudoPersonaje;
    }

    protected Personaje createCharacter(String characterType, String weapon, String shield, Boolean isAI) {
        Arma armaPersonaje = stringToWeapon(characterType, weapon);
        Escudo escudoPersonaje = stringToShield(shield);
        Personaje personaje;
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
                throw new IllegalArgumentException("Invalid character type, character type: " + characterType + " is not a valid character type");
        }

        if (isAI && personaje != null) {
            personaje.setIsAI(isAI);
        }

        return personaje;
    }
}
