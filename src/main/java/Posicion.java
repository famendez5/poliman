public class Posicion {
    private int x;
    private int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Posicion plusX(int x) {
        return new Posicion(this.x + x, y);
    }

    public Posicion plusY(int y) {
        return new Posicion(x, this.y + y);
    }

    /**
     * Valida si esta posición se encuentra entre las dos posiciones dadas
     */
    public boolean between(Posicion start, Posicion end) {
        return x >= start.getX() && y >= start.getY() && x <= end.getX() && y <= end.getY();
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
