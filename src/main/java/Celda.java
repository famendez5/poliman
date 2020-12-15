import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Una celda de la pared
 */
public class Celda extends BaseGameObject implements GameObject {

    public Celda() {
        this(0, 0, 0);
    }

    public Celda(int x, int y, int size) {
        super(x, y, size);
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
        context.fillRect(getX(), getY(), getSize(), getSize());
    }
}

