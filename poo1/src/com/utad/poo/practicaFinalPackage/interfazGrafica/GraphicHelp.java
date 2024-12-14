package com.utad.poo.practicaFinalPackage.interfazGrafica;

import javax.swing.JOptionPane;
import com.utad.poo.practicaFinalPackage.personajes.*;
import com.utad.poo.practicaFinalPackage.herramientas.*;

public abstract class GraphicHelp {

    public final static String separador = "--------------------------------\n";

    public static void showOptionStats() {
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
        
        

        JOptionPane.showMessageDialog(null, "Estadísticas de los personajes:\n" + estadisticasGuerro + separador + estadisticasArquero + separador + estadisticasMago + "\n");
        JOptionPane.showMessageDialog(null, "Tipos de armas" + separador + tiposDeArmasGuerrero + separador + tiposDeArmasArquero + separador + tiposDeArmasMago + "\n");
        JOptionPane.showMessageDialog(null, "Tipos de escudos:\n" + tiposDeEscudos + "\n");
    }


    public static void showOptionHelp() {
        JOptionPane.showMessageDialog(null, "Ayuda de cómo Jugar:\n"
                + "Battle Royale por turnos.\n"
                + "El juego consiste en mover a tu personaje por el mapa y atacar, defenderte o evitar (retirada) a los enemigos.\n");

        JOptionPane.showMessageDialog(null, "Instrucciones de juego:\n"
                + "Por cada turno, el jugador puede realizar las siguientes acciones:\n" + GraphicHelp.separador
                + "Para moverte, haz click en el botón de movimiento y luego en el tile al que quieres moverte (CONTIGUO).\n"
                    + "- IMPORTANTE: No puedes moverte a un tile ocupado por otro personaje.\n"
                + "Para atacar, haz click en el botón de ataque y luego en el tile del enemigo (CONTIGUO).\n" 
                    + "- IMPORTANTE: Atacar a un tile vacio hará que no suceda nada.\n" + GraphicHelp.separador
                + "Para defenderte, haz click en el botón de defensa y luego en el tile del enemigo (CONTIGUO).\n" 
                    + "- IMPORTANTE: Defenderse de un tile reduce el daño que se recibe y permite un contraataque.\n"
                    + "- Contrataque: Si el enemigo ataca y tu te defiendes, tienes una probabilidad de contraatacar y devolver parte del daño\n"
                + "Para retirarte, haz click en el botón de retirada y luego en el tile del enemigo (CONTIGUO).\n" + GraphicHelp.separador
                    + "- La retirada permite evitar el daño que se recibe de un ataque/tile enemigo.\n"
                    + "- Sin embargo, la retirada no permite contraatacar y la probabilidad es baja ¡Úsalo en caso de emergencia!\n");

        JOptionPane.showMessageDialog(null, "¿Como funcionan los turnos? \n"
                + "Los turnos se dividen en dos fases:\n" + GraphicHelp.separador
                + "Fase 1: Acciones\n"
                + "En esta fase, el jugador puede realizar las acciones de defensa y retirada.\n"
                + "Una vez realizadas las acciones, se pasa a la siguiente fase.\n" + GraphicHelp.separador
                + "Fase 2: Ataques\n"
                + "En esta fase, los personajes atacan a los tiles que han seleccionado.\n"
                + "Una vez realizados los ataques, se pasa a la siguiente fase.\n" + GraphicHelp.separador
                + "Fase 3: Movimientos\n"
                + "En esta fase, los personajes que deseen moverse, se mueven.\n"); 

        JOptionPane.showMessageDialog(null, "¿Que son los items y como se usan?\n"
                + "Los items son objetos que se pueden usar en el juego para mejorar las estadísticas de los personajes.\n"
                    + "- Los items se pueden usar al inicio de cada turno y tienen un uso.\n"
                    + "- Los hay de aumento de vida, ataque, defensa y de retirada.\n"
                    + "- IMPORTANTE: Menos el el de aumento de vida, el resto se revierten al final del turno.\n" + GraphicHelp.separador
                + "Para usar un item, haz click en el botón con su nombre\n"
                + "Cada personaje tiene un máximo de 5 items y no hay limite de uso por turno.\n" + GraphicHelp.separador
                + "Tipos de items:\n"
                    + "1- Botiquín y vendas: aumentas 50 y 20 puntos de vida respectivamente\n"
                    + "2- Poción de ataque: aumenta un 30% el ataque\n"
                    + "3- Poción de defensa: aumenta un 30% la defensa\n"
                    + "4- Poción de retirada: garantiza un 100% de retirada\n");

        JOptionPane.showMessageDialog(null, "TIpos de casillas:\n"
                + "Las casillas del mapa pueden ser de varios tipos:\n" + GraphicHelp.separador
                    + "1- Casilla vacia: No hay nada en la casilla\n"
                    + "2- Casilla de agua: No permite el paso\n"
                    + "3- Casilla ocupada por un personaje: No permite el paso\n" 
                    + "4- Casilla de objeto: Permite recoger un item\n"
                    + "5- Casilla de trampa: Casilla que hace aplica un efecto negativo al personaje que pase por ella\n"
                        + "- Las trampas pueden reducir la vida, ataque o defensa del personaje\n"
                        + "- IMPORTANTE: Menos la trampa de vida, el resto se revierten al final del turno.\n");

        JOptionPane.showMessageDialog(null, "¿Como se gana el juego?\n"
                + "El juego se gana cuando el jugador sea el ultimo en pie.\n"
                + "El juego se pierde cuando un personaje llega a 0 puntos de vida.\n"
                + "¡Buena suerte y que gane el mejor!\n");

    }
                

}



