package components;

/**
 * Interfaz funcional que representa una acción en un componente de la interfaz de usuario.
 * Define el comportamiento que se debe ejecutar cuando se activa el componente.
 */
@FunctionalInterface
public interface Action {
    /**
     * Ejecuta la acción definida por la implementación.
     */
    void execute();
}