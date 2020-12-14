import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void overlapsTopLeft() {
        GameObject a = new Celda(0, 0, 30);
        GameObject b = new Celda(10, 10, 30);
        assertTrue(a.overlaps(b));
    }

    @Test
    void overlapsTopRight() {
        GameObject a = new Celda(10, 0, 30);
        GameObject b = new Celda(0, 10, 30);
        assertTrue(a.overlaps(b));
    }

    @Test
    void overlapsBottomLeft() {
        GameObject a = new Celda(10, 10, 30);
        GameObject b = new Celda(0, 0, 30);
        assertTrue(a.overlaps(b));
    }

    @Test
    void overlapsBottomRight() {
        GameObject a = new Celda(0, 10, 30);
        GameObject b = new Celda(10, 0, 30);
        assertTrue(a.overlaps(b));
    }

    @Test
    void shouldNotOverlapTop() {
        GameObject a = new Celda(0, 0, 30);
        GameObject b = new Celda(0, 30, 30);
        assertFalse(a.overlaps(b));
    }

    @Test
    void shouldOverlapTop() {
        GameObject a = new Celda(0, 1, 30);
        GameObject b = new Celda(0, 30, 30);
        assertTrue(a.overlaps(b));
    }
}