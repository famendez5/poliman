import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntoGrandeTest {
    @Test
    void testAsustarFantasmas() {
        Poliman poliman = new Poliman();
        Fantasma fantasma1 = new Fantasma();
        Fantasma fantasma2 = new Fantasma();
        PuntoGrande puntoGrande = new PuntoGrande();

        PolimanGame game = new PolimanGame(new GameObject[][]{
                {poliman, fantasma1},
                {puntoGrande, fantasma2}
        });

        game.onKeyPressed(KeyCode.DOWN);
        poliman.update(game);
        puntoGrande.update(game);

        assertTrue(fantasma1.isScared());
        assertTrue(fantasma2.isScared());
    }
}