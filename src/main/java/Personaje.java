/**
 * Personaje es la clase base que contiene las características comunes
 * de los personajes del juego: poliman y los fantasmas.
 */
public abstract class Personaje extends BaseGameObject {
    private Direccion direccion;
    private int velocity = 1;

    public Personaje(int x, int y, int size) {
        super(x, y, size);
    }

    /**
     * Obtener la dirección del personaje
     * @return
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Asignar la dirección del personaje
     * @param direccion
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtener la velocidad del personaje
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * Asignar la velocidad del personaje
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
