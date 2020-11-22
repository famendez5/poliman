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
}