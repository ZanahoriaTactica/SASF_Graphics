package physics;

import core.Vector2D;

/**
 * Clase que representa un objeto dinámico en un espacio 2D que puede moverse y ser afectado por fuerzas.
 * Implementa física básica incluyendo posición, velocidad, aceleración y masa.
 */
public class DynamicObject {

    /** Masa del objeto en unidades arbitrarias */
    private final double mass;

    /** Posición actual del objeto en el espacio 2D */
    private Vector2D position;

    /** Velocidad actual del objeto */
    private Vector2D velocity;

    /** Aceleración actual del objeto */
    private Vector2D acceleration;

    /** Fuerza acumulada que se aplicará en el siguiente paso de simulación */
    private Vector2D accumulatedForce;

    /**
     * Crea un nuevo objeto dinámico con la posición y masa especificadas.
     *
     * @param position Posición inicial del objeto. Si es nulo, se usará (0,0)
     * @param mass Masa del objeto. Debe ser un valor no negativo
     */
    public DynamicObject(Vector2D position, double mass) {
        this.position = position != null ? position : new Vector2D(0, 0);
        this.mass = mass<0?0:mass;
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.accumulatedForce = new Vector2D(0, 0);
    }

    /**
     * Actualiza el estado del objeto en el tiempo.
     * Actualiza la aceleración, velocidad y posición basándose en las fuerzas aplicadas.
     *
     * @param deltaTime Tiempo transcurrido desde la última actualización en segundos
     */
    public void update(double deltaTime) {
        updateAcceleration();
        updateVelocity(deltaTime);
        updatePosition(deltaTime);
        resetForce();
    }

    /**
     * Actualiza la aceleración del objeto basándose en la fuerza acumulada y su masa.
     * Usa la segunda ley de Newton: F = m*a
     */
    private void updateAcceleration() {
        if (mass > 0) {
            acceleration = Vector2D.divide(accumulatedForce, mass);
        } else {
            acceleration = new Vector2D(0, 0);
        }
    }

    /**
     * Actualiza la velocidad del objeto basándose en la aceleración actual.
     *
     * @param deltaTime Tiempo transcurrido desde la última actualización en segundos
     */
    private void updateVelocity(double deltaTime) {
        velocity = Vector2D.add(velocity, Vector2D.multiply(acceleration, deltaTime));
    }

    /**
     * Actualiza la posición del objeto basándose en la velocidad actual.
     *
     * @param deltaTime Tiempo transcurrido desde la última actualización en segundos
     */
    private void updatePosition(double deltaTime) {
        position = Vector2D.add(position, Vector2D.multiply(velocity, deltaTime));
    }

    /**
     * Reinicia la fuerza acumulada a cero.
     * Debe llamarse después de cada paso de simulación.
     */
    private void resetForce() {
        accumulatedForce = new Vector2D(0, 0);
    }

    /**
     * Aplica una fuerza al objeto.
     * La fuerza se acumulará y se aplicará en el siguiente paso de simulación.
     *
     * @param force Vector de fuerza a aplicar. Si es nulo, no se aplica ninguna fuerza
     */
    public void applyForce(Vector2D force) {
        if (force != null) {
            accumulatedForce = Vector2D.add(accumulatedForce, force);
        }
    }

    /**
     * Obtiene la masa del objeto.
     *
     * @return Masa del objeto
     */
    public double getMass() {
        return mass;
    }

    /**
     * Obtiene la posición actual del objeto.
     *
     * @return Vector de posición actual
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Establece una nueva posición para el objeto.
     *
     * @param position Nueva posición. Si es nulo, se ignora
     */
    public void setPosition(Vector2D position) {
        if (position != null) {
            this.position = position;
        }
    }

    /**
     * Obtiene la velocidad actual del objeto.
     *
     * @return Vector de velocidad actual
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Establece una nueva velocidad para el objeto.
     *
     * @param velocity Nueva velocidad. Si es nulo, se ignora
     */
    public void setVelocity(Vector2D velocity) {
        if (velocity != null) {
            this.velocity = velocity;
        }
    }

    /**
     * Obtiene la aceleración actual del objeto.
     *
     * @return Vector de aceleración actual
     */
    public Vector2D getAcceleration() {
        return acceleration;
    }

    /**
     * Establece una nueva aceleración para el objeto.
     * Sobrescribirá cualquier aceleración calculada.
     *
     * @param acceleration Nueva aceleración. Si es nulo, se ignora
     */
    public void setAcceleration(Vector2D acceleration) {
        if (acceleration != null) {
            this.acceleration = acceleration;
        }
    }

    /**
     * Obtiene la fuerza acumulada que se aplicará en el siguiente paso de simulación.
     *
     * @return Vector de fuerza acumulada
     */
    public Vector2D getAccumulatedForce() {
        return accumulatedForce;
    }

    /**
     * Establece la fuerza acumulada directamente.
     * Usar con precaución, ya que sobrescribe cualquier fuerza previamente aplicada.
     *
     * @param accumulatedForce Nueva fuerza acumulada. Si es nulo, se ignora
     */
    public void setAccumulatedForce(Vector2D accumulatedForce) {
        if (accumulatedForce != null) {
            this.accumulatedForce = accumulatedForce;
        }
    }
}