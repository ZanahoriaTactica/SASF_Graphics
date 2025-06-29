package physics;

import core.Vector2D;

import java.util.ArrayList;

public class PhysicsEngine {
    private final ArrayList<GameObject> gameObjects;
    private Vector2D gravityAceleration;

    public PhysicsEngine(Vector2D gravityAceleration) {
        this.gravityAceleration = gravityAceleration;
        gameObjects = new ArrayList<>();
    }

    public void update(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            gameObject.applyForce(Vector2D.multiply(gravityAceleration, gameObject.getMass()));
            gameObject.update(deltaTime);
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject gameObject) {
        if (gameObject != null)
            gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        if (gameObject != null)
            gameObjects.remove(gameObject);
    }

    public void setGravityAceleration(Vector2D gravityAceleration) {
        this.gravityAceleration = gravityAceleration;
    }
}

