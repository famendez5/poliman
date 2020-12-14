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
}