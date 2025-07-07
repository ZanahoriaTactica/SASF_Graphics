package core;

/**
 * Clase que representa un vector bidimensional con coordenadas (x, y).
 * Proporciona operaciones matemáticas básicas para vectores 2D.
 */
public class Vector2D {

    /** Coordenada x del vector */
    private final double x;
    
    /** Coordenada y del vector */
    private final double y;

    /** Ángulo del vector en radianes */
    private final double angle;

    /**
     * Crea un nuevo vector 2D con las coordenadas especificadas.
     *
     * @param x Coordenada x del vector
     * @param y Coordenada y del vector
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = Math.atan2(y, x);
    }

    /**
     * Suma dos vectores y devuelve el resultado como un nuevo vector.
     *
     * @param v1 Primer vector
     * @param v2 Segundo vector
     * @return Nuevo vector resultado de la suma
     * @throws IllegalArgumentException Si alguno de los vectores es nulo
     */
    public static Vector2D add(Vector2D v1, Vector2D v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vectors cannot be null");
        }
        return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Multiplica un vector por un escalar y devuelve el resultado como un nuevo vector.
     *
     * @param v Vector a multiplicar
     * @param scalar Escalar por el que multiplicar
     * @return Nuevo vector resultado de la multiplicación
     * @throws IllegalArgumentException Si el vector es nulo
     */
    public static Vector2D multiply(Vector2D v, double scalar) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector2D(v.getX() * scalar, v.getY() * scalar);
    }

    /**
     * Divide un vector por un escalar y devuelve el resultado como un nuevo vector.
     *
     * @param v Vector a dividir
     * @param scalar Escalar por el que dividir
     * @return Nuevo vector resultado de la división
     * @throws IllegalArgumentException Si el vector es nulo o el escalar es cero
     */
    public static Vector2D divide(Vector2D v, double scalar) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Vector2D(v.getX() / scalar, v.getY() / scalar);
    }

    /**
     * Calcula la magnitud (longitud) de un vector.
     *
     * @param v Vector del que calcular la magnitud
     * @return Magnitud del vector
     * @throws IllegalArgumentException Si el vector es nulo
     */
    public static double getMagnitude(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
    }

    /**
     * Normaliza un vector (lo convierte en un vector unitario).
     * Si el vector es el vector cero, devuelve un nuevo vector cero.
     *
     * @param v Vector a normalizar
     * @return Nuevo vector normalizado
     * @throws IllegalArgumentException Si el vector es nulo
     */
    public static Vector2D normalize(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        double magnitude = getMagnitude(v);
        if (magnitude == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(v.getX() / magnitude, v.getY() / magnitude);
    }

    /**
     * Calcula la distancia euclidiana entre dos puntos representados por vectores.
     *
     * @param v1 Primer vector (punto)
     * @param v2 Segundo vector (punto)
     * @return Distancia entre los dos puntos
     * @throws IllegalArgumentException Si alguno de los vectores es nulo
     */
    public static double getDistance(Vector2D v1, Vector2D v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vectors cannot be null");
        }
        return Math.sqrt(Math.pow(v2.getX() - v1.getX(), 2) + Math.pow(v2.getY() - v1.getY(), 2));
    }

    /**
     * Cambia el ángulo del vector.
     *
     * @param angle Nuevo ángulo en radianes
     * @return Nuevo vector con el nuevo ángulo
     */
    public Vector2D changeAngle(double angle) {
        return new Vector2D(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle));
    }

    /**
     * Obtiene la coordenada x del vector.
     *
     * @return Coordenada x
     */
    public double getX() {
        return x;
    }

    /**
     * Obtiene la coordenada y del vector.
     *
     * @return Coordenada y
     */
    public double getY() {
        return y;
    }

    /**
     * Obtiene el ángulo del vector en radianes.
     *
     * @return Ángulo en radianes
     */
    public double getAngle() {
        return angle;
    }
}