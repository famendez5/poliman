import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntoTest {
    /**
     * Validar que si poliman está sobre un punto (llegando desde la izquierda), se sumen los puntos y que solo ocurra una vez
     */
    @Test
    void testPuntoSumaPuntaje() {
        Poliman poliman = new Poliman();
        Punto punto = new Punto();
        PolimanGame game = new PolimanGame(new GameObject[][]{{punto, poliman}});

        // Aun no está sobre el punto -> No debe sumar
        punto.update(game);
        assertEquals(0, game.getScore());

        // Mover a poliman sobre el punto
        game.onKeyPressed(KeyCode.LEFT);
        poliman.update(game);

        // Debe sumar el puntaje
        punto.update(game);
        assertEquals(10, game.getScore());

        // No debe incrementar el puntaje pues el punto ya fue usado
        punto.update(game);
        assertEquals(10, game.getScore());
    }

    /**
     * Validar que si poliman está sobre un punto (llegando desde la derecha), se sumen los puntos y que solo ocurra una vez
     */
    @Test
    void testPuntoSumaPuntajeDesdeLaDerecha() {
        Poliman poliman = new Poliman();
        Punto punto = new Punto();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman, punto}});

        // Aun no está sobre el punto -> No debe sumar
        punto.update(game);
        assertEquals(0, game.getScore());

        // Mover a poliman sobre el punto
        game.onKeyPressed(KeyCode.RIGHT);
        poliman.update(game);

        // Debe sumar el puntaje
        punto.update(game);
        assertEquals(10, game.getScore());

        // No debe incrementar el puntaje pues el punto ya fue usado
        punto.update(game);
        assertEquals(10, game.getScore());
    }

    /**
     * Validar que si poliman está sobre un punto (llegando desde arriba), se sumen los puntos y que solo ocurra una vez
     */
    @Test
    void testPuntoSumaPuntajeDesdeArriba() {
        Poliman poliman = new Poliman();
        Punto punto = new Punto();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}, {punto}});

        // Aun no está sobre el punto -> No debe sumar
        punto.update(game);
        assertEquals(0, game.getScore());

        // Mover a poliman sobre el punto
        game.onKeyPressed(KeyCode.DOWN);
        poliman.update(game);

        // Debe sumar el puntaje
        punto.update(game);
        assertEquals(10, game.getScore());

        // No debe incrementar el puntaje pues el punto ya fue usado
        punto.update(game);
        assertEquals(10, game.getScore());
    }

    /**
     * Validar que si poliman está sobre un punto (llegando desde abajo), se sumen los puntos y que solo ocurra una vez
     */
    @Test
    void testPuntoSumaPuntajeDesdeAbajo() {
        Poliman poliman = new Poliman();
        Punto punto = new Punto();
        PolimanGame game = new PolimanGame(new GameObject[][]{{punto}, {poliman}});

        // Aun no está sobre el punto -> No debe sumar
        punto.update(game);
        assertEquals(0, game.getScore());

        // Mover a poliman sobre el punto
        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);

        // Debe sumar el puntaje
        punto.update(game);
        assertEquals(10, game.getScore());

        // No debe incrementar el puntaje pues el punto ya fue usado
        punto.update(game);
        assertEquals(10, game.getScore());
    }
}