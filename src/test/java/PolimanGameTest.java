import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolimanGameTest {
    @Test
    void testPressAndReleaseSingleKey() {
        PolimanGame game = new PolimanGame();
        game.onKeyPressed(KeyCode.UP);
        assertTrue(game.hasKeyPressed(KeyCode.UP));
        game.onKeyReleased(KeyCode.UP);
        assertFalse(game.hasKeyPressed(KeyCode.UP));
    }

    @Test
    void testReleaseMultipleKeys() {
        PolimanGame game = new PolimanGame();

        game.onKeyPressed(KeyCode.UP);
        game.onKeyPressed(KeyCode.LEFT);

        assertTrue(game.hasKeyPressed(KeyCode.UP));
        assertTrue(game.hasKeyPressed(KeyCode.LEFT));

        game.onKeyReleased(KeyCode.UP, KeyCode.LEFT);

        assertFalse(game.hasKeyPressed(KeyCode.UP));
        assertFalse(game.hasKeyPressed(KeyCode.LEFT));
    }

    @Test
    void testGameWon() {
        Punto punto1 = new Punto();
        Punto punto2 = new Punto();
        Punto punto3 = new Punto();
        PuntoGrande punto4 = new PuntoGrande();

        PolimanGame game = new PolimanGame(new GameObject[][]{
                {punto1, punto2, punto3, punto4}
        });

        assertFalse(game.isGameWon());

        punto1.setUsed(true);
        assertFalse(game.isGameWon());

        punto2.setUsed(true);
        assertFalse(game.isGameWon());

        punto3.setUsed(true);
        assertFalse(game.isGameWon());

        punto4.setUsed(true);
        assertTrue(game.isGameWon());
    }
}