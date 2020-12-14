import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Punto implements GameObject {
    private int x;
    private int y;
    private int size;
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
        this.x = x;
        this.y = y;
        this.size = size;
        this.ratio = ratio;
        this.score = score;
    }

    @Override
    public void update(PolimanGame game) {
        if (used) return;
        // Si pacman está en esta posición, marcar como usado y agregar puntaje
        Poliman poliman = game.getPoliman();
        Posicion polimanPosicion = new Posicion(poliman.getX(), poliman.getY());
        Posicion puntoPosicion = new Posicion(x, y);
        if (polimanPosicion.between(puntoPosicion, new Posicion(x + size - 1, y + size - 1)) ||
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
        context.fillOval(x + pointOffset, y + pointOffset, pointSize, pointSize);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}
