import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Un punto que al ser comido por pacman, le da puntos
 */
public class Punto extends BaseGameObject implements GameObject {
    private final double ratio;
    private final int score;
    private boolean used;

    public Punto() {
        this(0, 0, 0);
    }

    public Punto(int x, int y, int size) {
        this(x, y, size, 0.5, 10);
    }

    public Punto(int x, int y, int size, double ratio, int score) {
        super(x, y, size);
        this.ratio = ratio;
        this.score = score;
    }

    /**
     * Cambiar el estado del punto
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Indica si ya se usó el punto
     */
    public boolean isUsed() {
        return used;
    }

    @Override
    public void update(PolimanGame game) {
        if (used) return;
        Poliman poliman = game.getPoliman();
        // Si pacman está en esta posición, marcar como usado y agregar puntaje
        if (this.overlaps(poliman)) {
            this.used = true;
            game.setScore(game.getScore() + score);
        }
    }

    @Override
    public void render(GraphicsContext context) {
        if (used) return;
        context.setFill(Color.WHITE);
        double pointSize = getSize() * ratio;
        double pointOffset = (getSize() - pointSize) / 2;
        context.fillOval(getX() + pointOffset, getY() + pointOffset, pointSize, pointSize);
    }
}
