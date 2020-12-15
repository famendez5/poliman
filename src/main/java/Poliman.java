import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.function.Predicate;

/**
 * Poliman es el personaje principal del juego
 */
public class Poliman extends Personaje implements GameObject {

    private boolean releaseOnUpdate = false;

    public Poliman() {
        this(0, 0, 0);
    }

    public Poliman(int x, int y, int size) {
        super(x, y, size);
        setDireccion(Direccion.ABAJO);
    }

    /**
     * Calcular la nueva dirección en base a las teclas presionadas.
     * Retorna null si no hay movimiento, es decir, si no hay ninguna tecla de movimiento presionada.
     */
    protected Direccion calcularDireccion(PolimanGame game) {
        if (game.hasKeyPressed(KeyCode.W, KeyCode.UP, KeyCode.K)) {
            return  Direccion.ARRIBA;
        } else if (game.hasKeyPressed(KeyCode.S, KeyCode.DOWN, KeyCode.J)) {
            return Direccion.ABAJO;
        } else if (game.hasKeyPressed(KeyCode.A, KeyCode.LEFT, KeyCode.H)) {
            return Direccion.IZQUIERDA;
        } else if (game.hasKeyPressed(KeyCode.D, KeyCode.RIGHT, KeyCode.L)) {
            return Direccion.DERECHA;
        }

        return null;
    }

    @Override
    public void update(PolimanGame game) {
        Direccion nuevaDireccion = calcularDireccion(game);
        if (nuevaDireccion == null) {
            return;
        }

        setDireccion(nuevaDireccion);

        // Guardamos la posición inicial
        Posicion posicion = new Posicion(getX(), getY());
        Posicion nuevaPosicion;

        // Calculamos la nueva posición en base a la dirección
        switch (getDireccion()) {
            case ARRIBA:
                nuevaPosicion = posicion.plusY(-getVelocity());
                break;
            case ABAJO:
                nuevaPosicion = posicion.plusY(getVelocity());
                break;
            case IZQUIERDA:
                nuevaPosicion = posicion.plusX(-getVelocity());
                break;
            case DERECHA:
                nuevaPosicion = posicion.plusX(getVelocity());
                break;
            default:
                return;
        }

        // Asignamos la nueva posición
        this.setX(nuevaPosicion.getX());
        this.setY(nuevaPosicion.getY());

        Predicate<GameObject> overlappingCellPredicate = (obj) -> obj != this && obj.overlaps(this) && obj instanceof Celda;

        // Si en la nueva posición hay una celda
        if (game.gameObjects.stream().anyMatch(overlappingCellPredicate)) {
            // En modo debug, coloreamos la celda que impide que nos movamos
            if (game.isDebugEnabled()) {
                Celda m = (Celda) game.gameObjects.stream().filter(overlappingCellPredicate).findFirst().get();
                System.out.println(m.getClass().getSimpleName() + m.getPosicion());
                m.setColor("ff0000");
                game.gameObjects.stream().filter(Celda.class::isInstance).forEach((c) -> {
                    if (c != m) ((Celda) c).setColor("1D4ED8");
                });
            }

            // Devolvemos el objeto a la posición inicial
            this.setX(posicion.getX());
            this.setY(posicion.getY());
        }

        // Para tener más precisión se actualiza solo una vez el juego
        if (releaseOnUpdate) {
            game.onKeyReleased(KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT);
            game.onKeyReleased(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D);
            game.onKeyReleased(KeyCode.H, KeyCode.J, KeyCode.K, KeyCode.L);
        }
    }

    /**
     * Obtener el angulo inicial en grados para empezar a dibujar la boca de poliman, en base a la dirección.
     */
    protected static double getStartAngle(Direccion direccion) {
        switch (direccion) {
            case ARRIBA:
                return 90 + 45;
            case IZQUIERDA:
                return 180 + 45;
            case ABAJO:
                return 270 + 45;
            case DERECHA:
            default:
                return 45;
        }
    }

    @Override
    public void render(GraphicsContext context) {
        context.setFill(Color.YELLOW);
        context.fillArc(getX(), getY(), getSize(), getSize(), Poliman.getStartAngle(getDireccion()), 360 - 90, ArcType.ROUND);
    }

    public void setReleaseOnUpdate(boolean releaseOnUpdate) {
        this.releaseOnUpdate = releaseOnUpdate;
    }
}
