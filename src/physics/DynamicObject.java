package physics;

import core.Vector2D;

/**
 * Represents a dynamic object with mass, position, velocity, and acceleration.
 */
public class DynamicObject {

    private final double mass;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private Vector2D accumulatedForce;

    public DynamicObject(Vector2D position, double mass) {
        this.position = position != null ? position : new Vector2D(0, 0);
        this.mass = mass;
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.accumulatedForce = new Vector2D(0, 0);
    }

    public void update(double deltaTime) {
        if (mass > 0) {
            acceleration = Vector2D.divide(accumulatedForce, mass);
        } else {
            acceleration = new Vector2D(0, 0);
        }
        velocity = Vector2D.add(velocity, Vector2D.multiply(acceleration, deltaTime));
        position = Vector2D.add(position, Vector2D.multiply(velocity, deltaTime));
        accumulatedForce = new Vector2D(0, 0);
    }

    public void applyForce(Vector2D force) {
        if (force != null) {
            accumulatedForce = Vector2D.add(accumulatedForce, force);
        }
    }

    public double getMass() {
        return mass;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public void setAccumulatedForce(Vector2D accumulatedForce) {
        this.accumulatedForce = accumulatedForce;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public Vector2D getAccumulatedForce() {
        return accumulatedForce;
    }
}