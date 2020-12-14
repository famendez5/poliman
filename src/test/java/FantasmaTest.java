import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FantasmaTest {
    @Test
    void testTerminaJuegoSiAtrapaAPoliman() {
        Poliman poliman = new Poliman();
        Fantasma fantasma = new Fantasma();
        PolimanGame game = new PolimanGame(new GameObject[][]{
                {poliman, fantasma},
        });

        // En la posición inicial el juego continua
        fantasma.update(game);

        assertFalse(game.isGameOver());

        // Si poliman se mueve a la posición del fantasma
        game.onKeyPressed(KeyCode.RIGHT);
        poliman.update(game);
        fantasma.update(game);
        // El juego debe terminar
        assertTrue(game.isGameOver());
    }
}