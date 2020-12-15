/**
 * BaseGameObject es la clase base que contiene las características comunes
 * de los objetos del juego.
 */
public abstract class BaseGameObject implements GameObject {
    private Posicion posicion;
    private int size;

    public BaseGameObject(int x, int y, int size) {
        this(new Posicion(x, y), size);
    }

    public BaseGameObject(Posicion posicion, int size) {
        this.posicion = posicion;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Posicion getPosicion() {
        return posicion;
    }

    @Override
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean overlaps(GameObject other) {
        if (betweenTopLeftAndBottomRight(this, other) || betweenTopLeftAndBottomRight(other, this)) {
            return true;
        }

        if (betweenTopRightAndBottomLeft(this, other) || betweenTopRightAndBottomLeft(other, this)) {
            return true;
        }

        return false;
    }

    /**
     * Verifica si el otro objeto está entre este, desde el punto
     * superior izquierdo hasta el punto inferior derecho
     * @return
     */
    private static boolean betweenTopLeftAndBottomRight(GameObject a, GameObject b) {
        Posicion otherBottomRight = b.getPosicion().plusX(a.getSize() - 1).plusY(a.getSize() - 1);
        return a.getPosicion().between(b.getPosicion(), otherBottomRight);
    }

    /**
     * Verifica si el otro objeto está entre este, desde el punto
     * superior derecho hasta el punto inferior izquierdo
     * @return
     */
    private static boolean betweenTopRightAndBottomLeft(GameObject a, GameObject b) {
        Posicion topRight = a.getPosicion().plusX(a.getSize() - 1);
        Posicion otherBottomRight = b.getPosicion().plusX(a.getSize() - 1).plusY(a.getSize() - 1);
        return topRight.between(b.getPosicion(), otherBottomRight);
    }
}
