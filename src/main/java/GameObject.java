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
}
