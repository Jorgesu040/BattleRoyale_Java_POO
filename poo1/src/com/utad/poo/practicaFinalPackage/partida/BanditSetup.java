package com.utad.poo.practicaFinalPackage.partida;

import java.util.Random;
import com.utad.poo.practicaFinalPackage.personajes.Personaje;

public class BanditSetup extends CharacterSetup {

    private Random random = new Random();

    public Personaje createRandomBandit() {
        String[] characterTypes = { "Guerrero", "Arquero", "Mago" };
        String[] weaponsGuerrero = { "Espada Bastarda", "Lanza Puntaiguda", "Hacha Doble Filo" };
        String[] weaponsArquero = { "Arco de Guerrilla", "Arco de Precisión", "Ballesta" };
        String[] weaponsMago = { "Varita de Cristal", "Bastón de Sabiduría", "Orbe Ancestral" }; 

        String[] shields = { "Escudo Ligero", "Escudo Normal", "Escudo Pesado" };

        String characterType = characterTypes[random.nextInt(characterTypes.length)];
        String weapon = null;
        
        if (characterType.equals("Guerrero")) {
            weapon = weaponsGuerrero[random.nextInt(weaponsGuerrero.length)];
        } else if (characterType.equals("Arquero")) {
            weapon = weaponsArquero[random.nextInt(weaponsArquero.length)];
        } else if (characterType.equals("Mago")) {
            weapon = weaponsMago[random.nextInt(weaponsMago.length)];
        }

        String shield = shields[random.nextInt(shields.length)];

        return createCharacter(characterType, weapon, shield);
    }
}
