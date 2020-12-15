import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    @Override
    public void update(PolimanGame game) {
        if (used) return;
        // Si pacman está en esta posición, marcar como usado y agregar puntaje
        Poliman poliman = game.getPoliman();
        Posicion polimanPosicion = new Posicion(poliman.getX(), poliman.getY());
        Posicion puntoPosicion = new Posicion(getX(), getY());
        if (polimanPosicion.between(puntoPosicion, new Posicion(getX() + getSize() - 1, getY() + getSize() - 1)) ||
                puntoPosicion.between(polimanPosicion, polimanPosicion.plusX(poliman.getSize() - 1).plusY(poliman.getSize() - 1))) {
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
