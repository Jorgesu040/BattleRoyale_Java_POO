package com.utad.poo.practicaFinalPackage.items;

import java.util.Random;

public class ItemGenerator {


    public Item generateRandomItem() {
        Random rand = new Random();
        Integer randomNum = rand.nextInt(2); // 0 para ºtrap, 1 para character item
        if (randomNum.equals(0)) {
            return generateRandomTrap();
        } else {
            return generateRandomCharacterItem();
        }
    }

    public Item generateRandomTrap() {
        Random rand = new Random();
        Integer randomNum = rand.nextInt(3); // Numero de tipos de trampas
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

    public Item generateRandomCharacterItem() {
        Random rand = new Random();
        Integer randomNum = rand.nextInt(5); // Numero de tipos de items de personaje
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
