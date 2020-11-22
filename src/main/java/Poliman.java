import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 * Poliman es el personaje principal del juego
 */
public class Poliman extends Personaje implements GameObject {
    private Direccion direccion;

    private int size;

    public Poliman() {
        this(0, 0, 0);
    }

    public Poliman(int x, int y, int size) {
        super(x, y);
        this.direccion = Direccion.ABAJO;
        this.size = size;
    }

    /**
     * Retorna la dirección actual de poliman
     */
    public Direccion getDireccion() {
        return direccion;
    }

    int velocity = 1;

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

        direccion = nuevaDireccion;

        Posicion posicion = new Posicion(getX(), getY());
        Posicion nuevaPosicion = new Posicion(getX(), getY());

        // velocity = this.velocity * getSize();
        switch (direccion) {
            case ARRIBA:
                // this.setY(getY() - velocity);
                if (game.getObjectAt(nuevaPosicion.plusY(-velocity)) instanceof Celda) {
                    return;
                }
                nuevaPosicion.setY(getY() - velocity);
                break;
            case ABAJO:
                if (game.getObjectAt(nuevaPosicion.plusY(velocity + getSize())) instanceof Celda) {
                    return;
                }
                // this.setY(getY() + velocity);
                nuevaPosicion.setY(getY() + velocity);
                break;
            case IZQUIERDA:
                if (game.getObjectAt(nuevaPosicion.plusX(-velocity)) instanceof Celda) {
                    return;
                }
                // this.setX(getX() - velocity);
                nuevaPosicion.setX(getX() - velocity);
                break;
            case DERECHA:
                if (game.getObjectAt(nuevaPosicion.plusX(velocity + getSize())) instanceof Celda) {
                    return;
                }
                // this.setX(getX() + velocity);
                nuevaPosicion.setX(getX() + velocity);
                break;
        }

        /*GameObject gameObject = game.getObjectAt(nuevaPosicion);
        //List<GameObject> gameObjects = game.getObjectsAt(nuevaPosicion);
        //boolean isCelda = gameObjects.stream().anyMatch(Celda.class::isInstance);
        System.out.println("Actual: " + posicion.getX() + ", " + posicion.getY());
        //System.out.println(gameObjects);
        if (gameObject instanceof Celda) {
            ((Celda) gameObject).setColor("ff00ff");
            System.out.println("A");
            System.out.println("Nueva: " + nuevaPosicion.getX() + ", " + nuevaPosicion.getY());
            System.out.println("Object: " + gameObject.getX() + ", " + gameObject.getY());
            return;
        }

        gameObject = game.getObjectAt(nuevaPosicion.plusY(getSize()));
        if (gameObject instanceof Celda) {
            ((Celda) gameObject).setColor("ff00ff");
            System.out.println("B");
            System.out.println("Nueva: " + nuevaPosicion.getX() + ", " + nuevaPosicion.getY());
            System.out.println("Object: " + gameObject.getX() + ", " + gameObject.getY());
            return;
        }

        gameObject = game.getObjectAt(nuevaPosicion.plusX(getSize()));
        if (gameObject instanceof Celda) {
            ((Celda) gameObject).setColor("ff00ff");
            System.out.println("C");
            System.out.println("Nueva: " + nuevaPosicion.getX() + ", " + nuevaPosicion.getY());
            System.out.println("Object: " + gameObject.getX() + ", " + gameObject.getY());
            return;
        }

        gameObject = game.getObjectAt(nuevaPosicion.plusX(getSize()).plusY(getSize()));
        if (gameObject instanceof Celda) {
            ((Celda) gameObject).setColor("ff00ff");
            System.out.println("D");
            System.out.println("Nueva: " + nuevaPosicion.getX() + ", " + nuevaPosicion.getY());
            System.out.println("Object: " + gameObject.getX() + ", " + gameObject.getY());
            return;
        }*/

        this.setX(nuevaPosicion.getX());
        this.setY(nuevaPosicion.getY());
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
        context.fillArc(getX(), getY(), size, size, Poliman.getStartAngle(getDireccion()), 360 - 90, ArcType.ROUND);
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
