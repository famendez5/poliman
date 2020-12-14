import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosicionTest {

    @Test
    void testBetween() {
        Posicion posicion = new Posicion(5, 5);
        assertTrue(posicion.between(new Posicion(0, 0), new Posicion(10, 10)));
        assertTrue(posicion.between(new Posicion(5, 5), new Posicion(10, 10)));
        assertTrue(posicion.between(new Posicion(0, 0), new Posicion(5, 5)));

        assertFalse(posicion.between(new Posicion(0, 0), new Posicion(4, 4)));
        assertFalse(posicion.between(new Posicion(6, 6), new Posicion(10, 10)));
    }
}