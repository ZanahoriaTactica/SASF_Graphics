package components;

/**
 * Enumeración que define puntos de anclaje para posicionar componentes.
 * Los puntos de anclaje se utilizan para alinear componentes dentro de contenedores.
 */
public enum AnchorPoint {

    TOP_LEFT(0.0, 0.0),
    TOP_CENTER(0.5, 0.0),
    TOP_RIGHT(1.0, 0.0),
    CENTER_LEFT(0.0, 0.5),
    CENTER(0.5, 0.5),
    CENTER_RIGHT(1.0, 0.5),
    BOTTOM_LEFT(0.0, 1.0),
    BOTTOM_CENTER(0.5, 1.0),
    BOTTOM_RIGHT(1.0, 1.0);

    /** Proporción horizontal del punto de anclaje (0.0 a 1.0) */
    private final double horizontalRatio;
    
    /** Proporción vertical del punto de anclaje (0.0 a 1.0) */
    private final double verticalRatio;

    /**
     * Constructor del punto de anclaje.
     *
     * @param horizontalRatio Proporción horizontal (0.0 = izquierda, 0.5 = centro, 1.0 = derecha)
     * @param verticalRatio Proporción vertical (0.0 = arriba, 0.5 = centro, 1.0 = abajo)
     */
    AnchorPoint(double horizontalRatio, double verticalRatio) {
        this.horizontalRatio = horizontalRatio;
        this.verticalRatio = verticalRatio;
    }

    /**
     * Obtiene la proporción horizontal del punto de anclaje.
     *
     * @return Valor entre 0.0 (izquierda) y 1.0 (derecha)
     */
    public double getHorizontalRatio() {
        return horizontalRatio;
    }

    /**
     * Obtiene la proporción vertical del punto de anclaje.
     *
     * @return Valor entre 0.0 (arriba) y 1.0 (abajo)
     */
    public double getVerticalRatio() {
        return verticalRatio;
    }
}