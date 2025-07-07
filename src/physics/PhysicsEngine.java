package physics;

import core.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de física que gestiona la simulación de objetos físicos en el juego.
 * Se encarga de aplicar fuerzas como la gravedad y actualizar el estado de los objetos.
 */
public class PhysicsEngine {


    /** Lista de objetos del juego que son gestionados por el motor de física */
    private final List<GameObject> gameObjects = new ArrayList<>();

    /** Vector de aceleración gravitatoria aplicada a los objetos con masa */
    private Vector2D gravityAcceleration;

    /**
     * Crea una nueva instancia del motor de física con la aceleración gravitatoria especificada.
     *
     * @param gravityAcceleration Vector de aceleración gravitatoria. Si es nulo, se usará (0,0)
     */
    public PhysicsEngine(Vector2D gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration != null ? gravityAcceleration : new Vector2D(0, 0);
    }

    /**
     * Actualiza el estado de todos los objetos gestionados por este motor.
     * Aplica la gravedad a los objetos con masa y actualiza su estado físico.
     *
     * @param deltaTime Tiempo transcurrido desde la última actualización en segundos
     * @throws IllegalArgumentException Si deltaTime es menor o igual a cero
     */
    public void update(double deltaTime) {
        if (deltaTime <= 0) {
            throw new IllegalArgumentException("deltaTime debe ser mayor que cero");
        }
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getMass() > 0) {
                gameObject.applyForce(Vector2D.multiply(gravityAcceleration, gameObject.getMass()));
            }
            gameObject.update(deltaTime);
        }
    }

    /**
     * Obtiene una copia de la lista de objetos gestionados por este motor.
     *
     * @return Una nueva lista que contiene los objetos actuales del motor
     */
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    /**
     * Añade un nuevo objeto al motor de física.
     * Si el objeto es nulo, se ignora la operación.
     *
     * @param gameObject Objeto a añadir al motor
     */
    public void addGameObject(GameObject gameObject) {
        if (gameObject != null) {
            gameObjects.add(gameObject);
        }
    }

    /**
     * Elimina un objeto del motor de física.
     * Si el objeto es nulo o no está en la lista, no se realiza ninguna acción.
     *
     * @param gameObject Objeto a eliminar del motor
     */
    public void removeGameObject(GameObject gameObject) {
        if (gameObject != null) {
            gameObjects.remove(gameObject);
        }
    }

    /**
     * Establece una nueva aceleración gravitatoria para el motor de física.
     * Si se proporciona un valor nulo, se usará (0,0).
     *
     * @param gravityAcceleration Nuevo vector de aceleración gravitatoria
     */
    public void setGravityAcceleration(Vector2D gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration != null ? gravityAcceleration : new Vector2D(0, 0);
    }
}