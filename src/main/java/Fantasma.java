import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Fantasma es la clase que contiene las características y comportamientos
 * de los rivales de poliman.
 */
public class Fantasma extends Personaje implements GameObject {
    private final String color;
    private final String nombre;
    private final RandomGenerator randomGenerator;

    public Fantasma() {
        this("unknown", "0000ff");
    }

    public Fantasma(String color, String nombre) {
        this(color, nombre, 0, 0, 0);
    }

    public Fantasma(String color, String nombre, int x, int y, int size) {
        super(x, y, size);
        assert color != null;
        assert nombre != null;

        this.color = color;
        this.nombre = nombre;
        this.randomGenerator = Math::random;

        setDireccion(nuevaDireccion());
        setVelocity(1 + (int) Math.floor(randomGenerator.generate() * 3));
    }

    /**
     * Retorna el color del fantasma en formato hexadecimal
     */
    public String getColor() {
        return color;
    }

    /**
     * Retorna el nombre del fantasma
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Generar una nueva dirección
     * @return
     */
    private Direccion nuevaDireccion() {
        Direccion[] direcciones = Direccion.values();
        return direcciones[(int) Math.floor(this.randomGenerator.generate() * direcciones.length)];
    }

    @Override
    public void update(PolimanGame game) {
        Poliman poliman = game.getPoliman();
        if (this.overlaps(poliman)) {
            game.setGameOver(true);
        }

        Posicion posicion = getPosicion();
        Posicion nuevaPosicion = nuevaPosicion();

        setPosicion(nuevaPosicion);

        // Si en la nueva posición hay una celda
        if (game.gameObjects.stream().anyMatch((obj) -> obj != this && obj.overlaps(this) && obj instanceof Celda)) {
            // Devolvemos el objeto a la posición inicial
            setDireccion(nuevaDireccion());
            // Cambiamos de dirección
            setPosicion(posicion);
        }
    }

    @Override
    public void render(GraphicsContext context) {
        context.setFill(Color.web(getColor()));
        int horizontalPadding = 2;
        double width = getSize() - horizontalPadding * 2;
        double height = getSize();
        double headHeight = height / 3.0;
        double bodyHeight = height / 3.0;
        double feetHeight = height - headHeight - bodyHeight;
        int x = getX() + horizontalPadding;
        int y = getY();

        context.fillOval(x, y, width, headHeight * 2);
        context.fillRect(x, y + headHeight, width, bodyHeight);
        int feetCount = 3;
        double feetWidth = width / feetCount;
        for (int i = 0; i < feetCount; i++) {
            context.fillOval(x + i * feetWidth, y + width - feetHeight, feetWidth, feetWidth);
        }

        double eyeGap = width / 20;
        double eyeSize = width / 2 - eyeGap * 2;
        context.setFill(Color.WHITE);
        double leftEyeX = x + eyeGap;
        double leftEyeY = y + headHeight - eyeSize / 2;
        context.fillOval(leftEyeX, leftEyeY, eyeSize, eyeSize);
        context.fillOval(leftEyeX + width / 2, leftEyeY, eyeSize, eyeSize);

        double pupileWidth = 2.0 / 5.0 * eyeSize;
        double pupileHeight = 2.0 / 4.0 * eyeSize;
        double leftPupileX = leftEyeX + eyeSize / 2 - pupileWidth / 2;
        double leftPupileY = leftEyeY + eyeSize / 2 - pupileHeight / 2;
        context.setFill(Color.BLUE);
        context.fillOval(leftPupileX, leftPupileY, pupileWidth, pupileHeight);
        context.fillOval(leftPupileX + width / 2, leftPupileY, pupileWidth, pupileHeight);
    }
}
