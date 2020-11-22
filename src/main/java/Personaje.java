/**
 * Personaje es la clase base que contiene las características comunes
 * de los personajes del juego: poliman y los fantasmas.
 */
public abstract class Personaje {
    private int x;
    private int y;

    public Personaje(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna la posición X del personaje
     */
    public int getX() {
        return x;
    }

    /**
     * Asigna la posición X del personaje
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retorna la posición Y del personaje
     */
    public int getY() {
        return y;
    }

    /**
     * Asigna la posición Y del personaje
     */
    public void setY(int y) {
        this.y = y;
    }
}
