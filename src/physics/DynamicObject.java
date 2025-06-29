package physics;

import core.Vector2D;

public class DynamicObject {

    private final double mass;              // Masa actual del objeto
    private Vector2D position;              // Posicion actual, esta saldra el centro de masa
    private Vector2D currentVelocity;       // Velocidad instantanea
    private Vector2D currentAcceleration;   // Aceleracion instantanea
    private Vector2D accumulatedForce;      // Fuerza total instantanea

    public DynamicObject(Vector2D position, double mass) {
        this.position = position;
        this.mass = mass;
        currentVelocity = new Vector2D(0, 0);
        currentAcceleration = new Vector2D(0, 0);
        accumulatedForce = new Vector2D(0, 0);
    }

    public void update(double deltaTime) {
        if (mass > 0) {
            // F = m*a -> a = F/m
            currentAcceleration = Vector2D.divide(accumulatedForce, mass);
        } else {
            currentAcceleration = new Vector2D(0, 0);
        }
        // v = v0 + a*dt
        currentVelocity = Vector2D.add(currentVelocity, Vector2D.multiply(currentAcceleration, deltaTime));
        // x = x0 + v*dt
        position = Vector2D.add(position, Vector2D.multiply(currentVelocity, deltaTime));

        accumulatedForce = new Vector2D(0, 0);
    }

    public void applyForce(Vector2D force) {
        accumulatedForce = Vector2D.add(accumulatedForce, force);
    }

    public double getMass() {
        return mass;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getCurrentVelocity() {
        return currentVelocity;
    }

    public Vector2D getCurrentAcceleration() {
        return currentAcceleration;
    }

    public Vector2D getAccumulatedForce() {
        return accumulatedForce;
    }


}
