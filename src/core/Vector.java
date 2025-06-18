package core;

public class Vector {

    private double x = 0;
    private double y = 0;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divide(double scalar) {
        if (scalar != 0) {
            this.x /= scalar;
            this.y /= scalar;
        }
    }

    public void normalize() {
        double magnitude = getMagnitude();
        if (magnitude != 0) {
            this.x /= magnitude;
            this.y /= magnitude;
        }
    }

    public double getDistance(Vector vector) {
        return Math.sqrt(Math.pow(x - vector.getX(), 2) + Math.pow(y - vector.getY(), 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}