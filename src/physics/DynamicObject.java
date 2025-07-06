package physics;

import core.Vector2D;

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
        updateAcceleration();
        updateVelocity(deltaTime);
        updatePosition(deltaTime);
        resetForce();
    }

    private void updateAcceleration() {
        if (mass > 0) {
            acceleration = Vector2D.divide(accumulatedForce, mass);
        } else {
            acceleration = new Vector2D(0, 0);
        }
    }

    private void updateVelocity(double deltaTime) {
        velocity = Vector2D.add(velocity, Vector2D.multiply(acceleration, deltaTime));
    }

    private void updatePosition(double deltaTime) {
        position = Vector2D.add(position, Vector2D.multiply(velocity, deltaTime));
    }

    private void resetForce() {
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
        if (position != null) {
            this.position = position;
        }
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        if (velocity != null) {
            this.velocity = velocity;
        }
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        if (acceleration != null) {
            this.acceleration = acceleration;
        }
    }

    public Vector2D getAccumulatedForce() {
        return accumulatedForce;
    }

    public void setAccumulatedForce(Vector2D accumulatedForce) {
        if (accumulatedForce != null) {
            this.accumulatedForce = accumulatedForce;
        }
    }
}