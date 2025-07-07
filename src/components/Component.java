package components;

import core.Vector2D;
import java.awt.*;

/**
 * Clase abstracta base para todos los componentes de la interfaz de usuario.
 * Proporciona funcionalidad común como posición, tamaño y manejo de acciones.
 */
public abstract class Component {

    /** Punto de anclaje para el renderizado del componente */
    protected AnchorPoint renderAnchor;
    
    /** Posición del componente en coordenadas 2D */
    private Vector2D position;
    
    /** Ancho del componente en píxeles */
    private int width;
    
    /** Alto del componente en píxeles */
    private int height;
    
    /** Acción a ejecutar cuando se activa el componente */
    private Action action;
    
    /** Componente de referencia para el posicionamiento relativo */
    private Component anchorComponent;

    /**
     * Crea un nuevo componente con la posición y dimensiones especificadas.
     *
     * @param position Posición inicial del componente (si es null, se usa (0,0))
     * @param width Ancho del componente (debe ser no negativo)
     * @param height Alto del componente (debe ser no negativo)
     * @throws IllegalArgumentException Si width o height son negativos
     */
    public Component(Vector2D position, int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height must be non-negative");
        }
        this.position = position != null ? position : new Vector2D(0, 0);
        this.width = width;
        this.height = height;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
    }

    /**
     * Actualiza el estado del componente.
     * Debe ser implementado por las clases hijas.
     */
    public abstract void update();

    /**
     * Renderiza el componente en el contexto gráfico proporcionado.
     * Debe ser implementado por las clases hijas.
     *
     * @param g Contexto gráfico donde se dibujará el componente
     * @throws IllegalArgumentException Si el contexto gráfico es nulo
     */
    public abstract void render(Graphics2D g);

    /**
     * Ejecuta la acción asociada al componente, si existe.
     */
    public void executeAction() {
        if (action != null) {
            action.execute();
        }
    }

    /**
     * Verifica si el punto (x,y) está dentro de los límites del componente.
     *
     * @param x Coordenada x del punto a verificar
     * @param y Coordenada y del punto a verificar
     * @return true si el punto está dentro del componente, false en caso contrario
     */
    public boolean containsPoint(int x, int y) {
        return x >= position.getX() && x < position.getX() + width &&
                y >= position.getY() && y < position.getY() + height;
    }

    public Vector2D getPosition() {
        return position;
    }

    /**
     * Establece una nueva posición para el componente.
     *
     * @param x Nueva coordenada x
     * @param y Nueva coordenada y
     */
    public void setPosition(double x, double y) {
        this.position = new Vector2D(x, y);
    }

    public int getWidth() {
        return width;
    }

    /**
     * Establece el ancho del componente.
     *
     * @param width Nuevo ancho (debe ser no negativo)
     * @throws IllegalArgumentException Si el ancho es negativo
     */
    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Establece el alto del componente.
     *
     * @param height Nuevo alto (debe ser no negativo)
     * @throws IllegalArgumentException Si el alto es negativo
     */
    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public Action getAction() {
        return action;
    }

    /**
     * Establece la acción a ejecutar cuando se active el componente.
     *
     * @param action Acción a establecer (puede ser null)
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Establece el componente de referencia para posicionamiento relativo.
     * Cuando se establece, las coordenadas del componente serán relativas a este.
     *
     * @param anchorComponent Componente de referencia (puede ser null)
     */
    public void setAnchorComponent(Component anchorComponent) {
        this.anchorComponent = anchorComponent;
    }

    /**
     * Calcula las coordenadas de renderizado teniendo en cuenta el anclaje.
     * 
     * @return Vector2D con las coordenadas (x,y) donde se debe dibujar el componente
     */
    public Vector2D getRenderDrawingCoordinates() {
        double drawX = position.getX();
        double drawY = position.getY();

        if (anchorComponent != null) {
            drawX += (anchorComponent.getWidth() - width) * renderAnchor.getHorizontalRatio();
            drawY += (anchorComponent.getHeight() - height) * renderAnchor.getVerticalRatio();
        }
        return new Vector2D(drawX, drawY);
    }

    /**
     * Establece el punto de anclaje para el renderizado del componente.
     * Si se proporciona null, se usa TOP_LEFT por defecto.
     *
     * @param renderAnchor Punto de anclaje a utilizar
     */
    public void setRenderAnchor(AnchorPoint renderAnchor) {
        this.renderAnchor = renderAnchor != null ? renderAnchor : AnchorPoint.TOP_LEFT;
    }
}