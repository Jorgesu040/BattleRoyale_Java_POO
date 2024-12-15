package com.utad.poo.practicaFinalPackage.items;

import java.util.Random;

public abstract class ItemGenerator {

    public static Item generateRandomItem() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2); // 0 for trap, 1 for character item
        if (randomNum == 0) {
            return generateRandomTrap();
        } else {
            return generateRandomCharacterItem();
        }
    }

    public static Item generateRandomTrap() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3); // Number of trap types
        switch(randomNum) {
            case 0:
                return new TrampaReducirAtaque(TrampaReducirAtaque.VALOR_EFECTO);
            case 1:
                return new TrampaReducirDefensa(TrampaReducirDefensa.VALOR_EFECTO);
            case 2:
                return new TrampaQuitarVida(TrampaQuitarVida.VALOR_EFECTO);
            default:
                return null;
        }
    }

    public static Item generateRandomCharacterItem() {
        Random rand = new Random();
        int randomNum = rand.nextInt(5); // Number of character item types
        switch(randomNum) {
            case 0:
                return new Vendas();
            case 1:
                return new Botiquin();
            case 2:
                return new PocionDeRetirada(PocionDeRetirada.VALOR_EFECTO);
            case 3:
                return new PocionDeAtaque(PocionDeAtaque.VALOR_EFECTO);
            case 4:
                return new PocionDeDefensa(PocionDeDefensa.VALOR_EFECTO);
            default:
                return null;
        }
    }
}
