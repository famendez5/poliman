/**
 * Un punto que da más puntos y que al ser comido
 * por poliman, asusta a los fantasmas
 */
public class PuntoGrande extends Punto {

    public PuntoGrande() {
        this(0, 0, 0);
    }

    public PuntoGrande(int x, int y, int size) {
        super(x, y, size, 1.0, 100);
    }
}
