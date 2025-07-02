package core;

/**
 * An immutable 2D vector with x and y coordinates.
 */
public class Vector2D {

    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D add(Vector2D v1, Vector2D v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vectors cannot be null");
        }
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2D multiply(Vector2D v, double scalar) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector2D(v.x * scalar, v.y * scalar);
    }

    public static Vector2D divide(Vector2D v, double scalar) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Vector2D(v.x / scalar, v.y / scalar);
    }

    public static double getMagnitude(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }

    public static Vector2D normalize(Vector2D v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        double magnitude = getMagnitude(v);
        if (magnitude == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(v.x / magnitude, v.y / magnitude);
    }

    public static double getDistance(Vector2D v1, Vector2D v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vectors cannot be null");
        }
        return Math.sqrt(Math.pow(v2.x - v1.x, 2) + Math.pow(v2.y - v1.y, 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}