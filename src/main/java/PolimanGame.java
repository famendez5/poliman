import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PolimanGame extends AnimationTimer {
    static final int CELLS = 28;
    static final int SIZE = 20;
    static final int STATUS_BAR_HEIGHT = SIZE * 2;
    static final int DEBUG_BAR_HEIGHT = SIZE * 2;
    static final int PADDING = 10;
    static final int X = PADDING;
    static final int Y = STATUS_BAR_HEIGHT + PADDING;
    static final int WIDTH = CELLS * SIZE + PADDING * 2;
    static final int BASE_HEIGHT = (CELLS - 8 /*TODO: Dejar 28*/) * SIZE + PADDING * 2 + STATUS_BAR_HEIGHT;

    private int score = 0;
    private boolean debugEnabled;
    private boolean gameOver;

    // Contiene las teclas presionadas
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    private Canvas canvas;
    private GraphicsContext graphicsContext;

    final List<GameObject> gameObjects = new LinkedList<>();

    private static GameObject[][] defaultLevel() {
        return new GameObject[][]{
                new GameObject[]{new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new PuntoGrande(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new PuntoGrande(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), null, new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), null, new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), null, new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), null, new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda()},
                new GameObject[]{null, null, null, null, new Celda(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Celda(), null, null, null, null},
                new GameObject[]{new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), null, new Celda(), null, new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), null, new Celda(), null, new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda()},
                new GameObject[]{null, null, null, null, null, null, null, null, new Celda(), null, null, null, null, null, null, null, null, null, null, new Celda(), null, null, null, null, null, null, null, null},
                new GameObject[]{null, null, null, null, null, null, null, null, new Celda(), null, new Fantasma("00FFFF", "P"), null, new Fantasma("00FF00", "Y"), null, null, new Fantasma("FCD34D", "Y"), null, new Fantasma("ff0000", "P"), null, new Celda(), null, null, null, null, null, null, null, null},
                new GameObject[]{new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), null, new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), null, new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda()},

                new GameObject[]{null, null, null, null, new Celda(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Celda(), null, null, null, null},
                new GameObject[]{new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), null, new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), null, new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), null, new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), null, new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda(), new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new PuntoGrande(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Punto(), new Celda(), new Celda(), new Celda(), new PuntoGrande(), new Celda()},
                new GameObject[]{new Celda(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Poliman(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Punto(), new Celda()},
                new GameObject[]{new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda(), new Celda()},
        };
    }

    public PolimanGame() {
        this(defaultLevel());
    }

    public PolimanGame(GameObject[][] level) {
        for (int r = 0; r < level.length; r++) {
            GameObject[] row = level[r];
            for (int c = 0; c < row.length; c++) {
                GameObject col = row[c];
                if (col == null) continue;
                col.setX(X + c * SIZE);
                col.setY(Y + r * SIZE);
                col.setSize(SIZE);
                gameObjects.add(col);
            }
        }
    }

    public void setup() {
        canvas = new Canvas(WIDTH, getHeight());
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public GameObject getObjectAt(Posicion posicion) {
        for (GameObject gameObject : gameObjects) {
            boolean inX = gameObject.getX() <= posicion.getX() && gameObject.getX() + gameObject.getSize() > posicion.getX();
            boolean inY = gameObject.getY() <= posicion.getY() && gameObject.getY() + gameObject.getSize() > posicion.getY();
            if (inX && inY) {
                return gameObject;
            }
        }
        return null;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Mostrar barra de estado con el puntaje actual.
     */
    private void renderStatusBar() {
        graphicsContext.setFill(Color.web("6366F1"));
        graphicsContext.fillRect(0, 0, WIDTH, STATUS_BAR_HEIGHT);

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font("Times New Roman", FontWeight.BOLD, SIZE));
        graphicsContext.fillText("Puntaje: " + getScore(), PADDING, PADDING + SIZE);
    }

    /**
     * Mostrar barra de depuración con las teclas presionadas.
     */
    private void renderDebugBar() {
        int y = getHeight() - DEBUG_BAR_HEIGHT;
        graphicsContext.setFill(Color.web("F59E0B"));
        graphicsContext.fillRect(0, y, WIDTH, DEBUG_BAR_HEIGHT);

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font("Times New Roman", FontWeight.BOLD, SIZE));
        // graphicsContext.fillText("Teclas presionadas: " + getPressedKeysString(), PADDING, y + SIZE + PADDING);
        Poliman poliman = getPoliman();
        graphicsContext.fillText("Posición Poliman: " + poliman.getX() + ", " + poliman.getY(), PADDING, y + SIZE + PADDING);
    }

    @Override
    public void handle(long now) {
        if (isGameOver()) {
            return;
        }

        graphicsContext.clearRect(0, 0, WIDTH, getHeight());
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, WIDTH, getHeight());

        renderStatusBar();

        if (isDebugEnabled()) {
            renderDebugBar();
        }

        for (GameObject gameObject : gameObjects) {
            gameObject.update(this);
            gameObject.render(graphicsContext);
        }
    }

    private String getPressedKeysString() {
        return pressedKeys.stream().map(KeyCode::getName).collect(Collectors.joining(", "));
    }

    public void onKeyPressed(KeyCode keyCode) {
        pressedKeys.add(keyCode);
    }

    public void onKeyReleased(KeyCode ...keyCodes) {
        pressedKeys.removeAll(Arrays.asList(keyCodes));
    }

    public boolean hasKeyPressed(KeyCode... keyCode) {
        return Arrays.stream(keyCode).anyMatch(pressedKeys::contains);
    }

    public int getHeight() {
        return BASE_HEIGHT + (isDebugEnabled() ? DEBUG_BAR_HEIGHT : 0);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Poliman getPoliman() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Poliman) {
                return (Poliman) gameObject;
            }
        }
        return null;
    }

    /**
     * Retorna si la salida de depuración está activa
     */
    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /**
     * Activar o desactivar la salida de depuracion
     */
    public void enableDebug(boolean enable) {
        debugEnabled = enable;
        if (canvas != null) {
            canvas.setHeight(getHeight());
        }
    }

    /**
     * Indica si el juego ya terminó
     * @return
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Cambiar el estado del juego
     * @param gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
