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

    /**
     * Asignar la posición del objeto
     * @param pos
     */
    void setPosicion(Posicion pos);

    /**
     * Retornar la posición del objeto
     * @return
     */
    Posicion getPosicion();

    /**
     * Retorna la posición en el eje X del objeto
     * @return
     */
    default int getX() {
        return getPosicion().getX();
    }

    /**
     * Asignar la posición en el eje X del objeto
     */
    default void setX(int x) {
        getPosicion().setX(x);
    }

    /**
     * Retorna la posición en el eje Y del objeto
     * @return
     */
    default int getY() {
        return getPosicion().getY();
    }

    /**
     * Asignar la posición en el eje Y del objeto
     */
    default void setY(int y) {
        getPosicion().setY(y);
    }

    /**
     * Asignar el tamaño del objeto
     * @param size
     */
    void setSize(int size);

    /**
     * Retornar el tamaño del objeto
     * @return
     */
    int getSize();

    /**
     * Verifica si este objeto y otro se superponen
     * @param other
     * @return
     */
    boolean overlaps(GameObject other);
}
