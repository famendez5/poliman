import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Celda implements GameObject {
    private int x;
    private int y;
    private int size;

    public Celda() {
        this(0, 0, 0);
    }

    public Celda(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    private String color = "1D4ED8";

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void update(PolimanGame game) {

    }

    @Override
    public void render(GraphicsContext context) {
        context.setFill(Color.web(color));
        context.fillRect(x, y, size, size);
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

