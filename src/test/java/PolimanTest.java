import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolimanTest {
    @Test
    void testCalcularDireccionArriba() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.W);
        poliman.update(game);
        assertEquals(Direccion.ARRIBA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionArribaFlecha() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);
        assertEquals(Direccion.ARRIBA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionArribaVIM() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.K);
        poliman.update(game);
        assertEquals(Direccion.ARRIBA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionAbajo() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.W);
        poliman.update(game);
        assertEquals(Direccion.ARRIBA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionAbajoFlecha() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.DOWN);
        poliman.update(game);
        assertEquals(Direccion.ABAJO, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionAbajoVIM() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.J);
        poliman.update(game);
        assertEquals(Direccion.ABAJO, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionIzquierda() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.A);
        poliman.update(game);
        assertEquals(Direccion.IZQUIERDA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionIzquierdaFlecha() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.LEFT);
        poliman.update(game);
        assertEquals(Direccion.IZQUIERDA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionIzquierdaVIM() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.H);
        poliman.update(game);
        assertEquals(Direccion.IZQUIERDA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionDerecha() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.D);
        poliman.update(game);
        assertEquals(Direccion.DERECHA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionDerechaFlecha() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.RIGHT);
        poliman.update(game);
        assertEquals(Direccion.DERECHA, poliman.getDireccion());
    }

    @Test
    void testCalcularDireccionDerechaVIM() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{{poliman}});

        game.onKeyPressed(KeyCode.L);
        poliman.update(game);
        assertEquals(Direccion.DERECHA, poliman.getDireccion());
    }

    @Test
    void testGetStartAngle() {
        assertEquals(135, Poliman.getStartAngle(Direccion.ARRIBA));
        assertEquals(315, Poliman.getStartAngle(Direccion.ABAJO));
        assertEquals(225, Poliman.getStartAngle(Direccion.IZQUIERDA));
        assertEquals(45, Poliman.getStartAngle(Direccion.DERECHA));
    }

    @Test
    void testNoSubirCuandoHayaUnaCeldaEnLaParteSupIzq() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{
                {null, new Celda()},
                {poliman, null},
        });

        // Ir hacia la derecha
        game.onKeyPressed(KeyCode.RIGHT);
        poliman.update(game);
        // Debe moverse en el eje X
        assertEquals(11, poliman.getX());
        assertEquals(70, poliman.getY());

        // Ir hacia arriba
        game.onKeyReleased(KeyCode.RIGHT);
        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);
        // No debe moverse
        assertEquals(11, poliman.getX());
        assertEquals(70, poliman.getY());
    }

    @Test
    void testPasarEntreCeldasHorizontalmente() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(
                new GameObject[][]{
                        {null, new Celda()},
                        {poliman, null},
                        {new Celda(), new Celda()},
                }
        );

        // Posición inicial
        assertEquals(10, poliman.getX());
        assertEquals(70, poliman.getY());
        game.onKeyPressed(KeyCode.RIGHT);
        poliman.update(game);
        // Debe pasar entre las paredes
        assertEquals(11, poliman.getX());
        assertEquals(70, poliman.getY());
    }

    @Test
    void testBajarHastaElLimiteDeLaPared() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{
                {null},
                {poliman},
                {new Celda()},
        });
        // Posición inicial
        assertEquals(10, poliman.getX());
        assertEquals(70, poliman.getY());
        // Ir hacia arriba
        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);
        // Debe subir
        assertEquals(10, poliman.getX());
        assertEquals(69, poliman.getY());

        // Ir hacia abajo
        game.onKeyReleased(KeyCode.UP);
        game.onKeyPressed(KeyCode.DOWN);
        poliman.update(game);
        // Debe bajar hasta el limite de la pared
        assertEquals(10, poliman.getX());
        assertEquals(70, poliman.getY());
    }

    @Test
    void testNoBajarSobreCelda() {
        Poliman poliman = new Poliman();
        Celda celda = new Celda();
        PolimanGame game = new PolimanGame(new GameObject[][]{
                {poliman},
                {celda},
                {new Celda()},
        });

        // Posición inicial de poliman
        assertEquals(10, poliman.getX());
        assertEquals(50, poliman.getY());
        // Posición de la celda
        assertEquals(10, celda.getX());
        assertEquals(70, celda.getY());

        game.onKeyPressed(KeyCode.DOWN);
        poliman.update(game);
        assertEquals(50, poliman.getY());
    }

    @Test
    void testSubirDesdeAbajoDerecha() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{
                {new Punto(), new Celda()},
                {null, poliman},
        });
        assertEquals(30, poliman.getX());
        assertEquals(70, poliman.getY());
        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);
        assertEquals(30, poliman.getX());
        assertEquals(70, poliman.getY());

        // Hacia la izquierda
        game.onKeyReleased(KeyCode.UP);
        game.onKeyPressed(KeyCode.LEFT);
        poliman.update(game);
        assertEquals(29, poliman.getX());
        assertEquals(70, poliman.getY());

        // Hacia arriba
        game.onKeyReleased(KeyCode.LEFT);
        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);
        assertEquals(29, poliman.getX());
        assertEquals(70, poliman.getY());
    }


    @Test
    void testIrIzqHastaLimiteDePared() {
        Poliman poliman = new Poliman();
        PolimanGame game = new PolimanGame(new GameObject[][]{
                {new Celda(), poliman},
        });
        // Posición inicial
        assertEquals(30, poliman.getX());
        // Ir hacia la derecha
        game.onKeyPressed(KeyCode.RIGHT);
        poliman.update(game);
        // Debe moverse
        assertEquals(31, poliman.getX());

        // Ir hacia la izq
        game.onKeyReleased(KeyCode.RIGHT);
        game.onKeyPressed(KeyCode.LEFT);
        poliman.update(game);
        // Debe volver
        assertEquals(30, poliman.getX());

        // Continuando hacia la izq
        poliman.update(game);
    }

    @Test
    void testReleaseKeysOnUpdate() {
        PolimanGame game = new PolimanGame();
        Poliman poliman = game.getPoliman();
        poliman.setReleaseOnUpdate(true);

        game.onKeyPressed(KeyCode.UP);
        poliman.update(game);

        assertFalse(game.hasKeyPressed(KeyCode.UP));
    }
}