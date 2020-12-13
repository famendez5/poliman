import javafx.scene.canvas.GraphicsContext;

/**
 * Define el comportamiento que debe tener cada uno de los objetos que conforman el juego
 */
public interface GameObject {
    /**
     * Actualiza el estado del objeto y/o del juego.
     * Es invocado mientras el juego no haya terminado
     */
    void update(PolimanGame game);

    /**
     * Dibujar el objeto de acuerdo su estado y posición
     */
    void render(GraphicsContext context);

    void setX(int x);
    int getX();
    void setY(int x);
    int getY();
    void setSize(int size);
    int getSize();

    default Posicion getPosicion() {
        return new Posicion(getX(), getY());
    }

    /**
     * Verifica si el otro objeto está entre este, desde el punto
     * superior izquierdo hasta el punto inferior derecho
     * @param other
     * @return
     */
    default boolean betweenTopLeftAndBottomRight(GameObject other) {
        Posicion otherBottomRight = other.getPosicion().plusX(getSize() - 1).plusY(getSize() - 1);
        return getPosicion().between(other.getPosicion(), otherBottomRight);
    }

    /**
     * Verifica si el otro objeto está entre este, desde el punto
     * superior derecho hasta el punto inferior izquierdo
     * @param other
     * @return
     */
    default boolean betweenTopRightAndBottomLeft(GameObject other) {
        Posicion topRight = getPosicion().plusX(getSize() - 1);
        Posicion otherBottomRight = other.getPosicion().plusX(getSize() - 1).plusY(getSize() - 1);
        return topRight.between(other.getPosicion(), otherBottomRight);
    }

    /**
     * Verifica si este objeto y otro se superponen
     * @param other
     * @return
     */
    default boolean overlaps(GameObject other) {
        if (this.betweenTopLeftAndBottomRight(other) || other.betweenTopLeftAndBottomRight(this)) {
            return true;
        }

        if (this.betweenTopRightAndBottomLeft(other) || other.betweenTopRightAndBottomLeft(this)) {
            return true;
        }

        return false;
    }
}
