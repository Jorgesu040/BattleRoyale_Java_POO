package com.utad.poo.practicaFinalPackage.personajes;

import org.junit.Test;
import static org.junit.Assert.*;

public class PersonajeTest {

    @Test
    public void testAtacar() {
        Personaje atacante = new Guerrero(new EspadaBastarda(), new EscudoNormal());
        Personaje oponente = new Mago(new BastonDeSabiduria(), new EscudoLigero());

        int vidaInicial = oponente.getVida();
        atacante.atacar(oponente);
        assertTrue(oponente.getVida() < vidaInicial);
    }

    @Test
    public void testDefensa() {
        Personaje defensor = new Guerrero(new EspadaBastarda(), new EscudoNormal());
        defensor.defensa(null); // Passing null as opponent for simplicity
        assertEquals(EstadoPersonaje.DEFENDIENDO, defensor.getEstado());
    }

    @Test
    public void testRetirada() {
        Personaje personaje = new Arquero(new ArcoDePrecision(), new EscudoLigero());
        Boolean seHaRetirado = personaje.retirada();
        assertNotNull(seHaRetirado);
    }

    @Test
    public void testRecibirAtaque() {
        Personaje personaje = new Guerrero(new EspadaBastarda(), new EscudoNormal());
        int vidaInicial = personaje.getVida();
        personaje.recibirAtaque(50.0);
        assertTrue(personaje.getVida() < vidaInicial);
    }

    // Tests from MagoTest
    @Test
    public void testCalcularDanioMago() {
        Mago mago = new Mago(new BastonDeSabiduria(), new EscudoLigero());
        Double danio = mago.calcularDanio();
        assertNotNull(danio);
        assertTrue(danio > 0);
    }

    @Test
    public void testAtaqueCriticoMago() {
        Mago mago = new Mago(new BastonDeSabiduria(), new EscudoLigero());
        boolean criticoOcurrido = false;
        for (int i = 0; i < 100; i++) {
            if (mago.momentoAtaqueCritico()) {
                criticoOcurrido = true;
                break;
            }
        }
        assertTrue(criticoOcurrido);
    }

    // Tests from GuerreroTest
    @Test
    public void testIraEspartanaIncremento() {
        Guerrero guerrero = new Guerrero(new EspadaBastarda(), new EscudoNormal());
        Double iraInicial = guerrero.getIraEspartanaContraataque();
        guerrero.recibirAtaque(20.0);
        assertTrue(guerrero.getIraEspartanaContraataque() > iraInicial);
    }

    @Test
    public void testContraAtacoGuerrero() {
        Guerrero guerrero = new Guerrero(new EspadaBastarda(), new EscudoNormal());
        guerrero.setIraEspartanaContraataque(Guerrero.IRA_ESPARTANA_MAX);
        assertTrue(guerrero.contraAtaco());
    }

    // Tests from ArqueroTest
    @Test
    public void testPunteriaInicial() {
        Arquero arquero = new Arquero(new ArcoDePrecision(), new EscudoLigero());
        assertEquals(Arquero.PUNTERIA_INICIAL, arquero.getPunteria());
    }

    @Test
    public void testIncrementarPunteria() {
        Arquero arquero = new Arquero(new ArcoDePrecision(), new EscudoLigero());
        arquero.setPunteria(Arquero.PUNTERIA_MAX);
        assertEquals(Arquero.PUNTERIA_MAX, arquero.getPunteria());
    }

    // ...other test methods...

}