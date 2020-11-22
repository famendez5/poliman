/**
 * Fantasma es la clase que contiene las características y comportamientos
 * de los rivales de poliman.
 */
public class Fantasma extends Personaje {
    private final String color;
    private final String nombre;

    public Fantasma(String color, String nombre) {
        assert color != null;
        assert nombre != null;

        this.color = color;
        this.nombre = nombre;
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
}
