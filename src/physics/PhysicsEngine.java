package physics;

import core.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class PhysicsEngine {


    private final List<GameObject> gameObjects = new ArrayList<>();


    private Vector2D gravityAcceleration;

    public PhysicsEngine(Vector2D gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration != null ? gravityAcceleration : new Vector2D(0, 0);
    }

    public void update(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getMass() > 0) {
                gameObject.applyForce(Vector2D.multiply(gravityAcceleration, gameObject.getMass()));
            }
            gameObject.update(deltaTime);
        }
    }

    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    public void addGameObject(GameObject gameObject) {
        if (gameObject != null) {
            gameObjects.add(gameObject);
        }
    }

    public void removeGameObject(GameObject gameObject) {
        if (gameObject != null) {
            gameObjects.remove(gameObject);
        }
    }

    public void setGravityAcceleration(Vector2D gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration != null ? gravityAcceleration : new Vector2D(0, 0);
    }
}