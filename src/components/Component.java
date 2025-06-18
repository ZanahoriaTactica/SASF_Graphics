package components;

import core.Vector;

import java.awt.*;

public abstract class Component {

    protected AnchorPoint renderAnchor; // El punto de anclaje para el renderizado visual de este componente.
    private Vector position; // La posición lógica (x, y) superior izquierda del componente.
    private int width;       // Ancho del componente.
    private int height;      // Alto del componente.
    private ActionInterface actionInterface; // Interfaz para la acción a ejecutar.
    private Component anchorComponent;     // Componente al que este componente está anclado.

    public Component(int x, int y, int width, int height) {
        this.position = new Vector(x, y);
        this.width = width;
        this.height = height;
        this.renderAnchor = AnchorPoint.TOP_LEFT; // Por defecto, se dibuja desde la esquina superior izquierda.
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    /**
     * Ejecuta la acción asociada a este componente, si existe.
     */
    public void doAction() {
        if (actionInterface != null) {
            actionInterface.doAction();
        }
    }

    /**
     * Verifica si las coordenadas dadas están dentro de los límites del componente.
     *
     * @return true si el punto está dentro, false en caso contrario.
     */
    public boolean contains(int x, int y) {
        return new Rectangle(
                (int) position.getX(),
                (int) position.getY(),
                width,
                height
        ).contains(x, y);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ActionInterface getActionInterface() {
        return actionInterface;
    }

    public void setActionInterface(ActionInterface actionInterface) {
        this.actionInterface = actionInterface;
    }

    /**
     * Establece el componente al que este componente se anclará.
     *
     * @param anchorComponent El componente de referencia para el anclaje.
     */
    public void setAnchorComponent(Component anchorComponent) {
        this.anchorComponent = anchorComponent;
    }

    /**
     * Obtiene las coordenadas de dibujo ajustadas de este componente,
     * basándose en su posición lógica y su punto de anclaje (renderAnchor)
     * relativo al componente de anclaje (anchorComponent), si está configurado.
     * Si no hay anchorComponent, devuelve la posición lógica sin ajustes de anclaje relativo.
     *
     * @return Un Vector con las coordenadas (x, y) donde el componente debe ser dibujado.
     */
    public Vector getRenderDrawingCoordinates() {
        double drawX = position.getX();
        double drawY = position.getY();

        // Si hay un componente de anclaje, ajusta la posición de dibujo
        // basándose en las dimensiones del componente de anclaje y cómo
        // este componente se quiere alinear dentro de él.
        if (anchorComponent != null) {
            switch (renderAnchor) {
                case TOP_LEFT:
                    // Sin cambios, es el punto de referencia
                    break;
                case TOP_CENTER:
                    // Centrar horizontalmente este componente dentro del componente de anclaje
                    drawX += (anchorComponent.getWidth() - width) / 2.0;
                    break;
                case TOP_RIGHT:
                    // Alinear a la derecha de este componente dentro del componente de anclaje
                    drawX += anchorComponent.getWidth() - width;
                    break;
                case CENTER_LEFT:
                    // Centrar verticalmente este componente dentro del componente de anclaje
                    drawY += (anchorComponent.getHeight() - height) / 2.0;
                    break;
                case CENTER:
                    // Centrar horizontal y verticalmente este componente dentro del componente de anclaje
                    drawX += (anchorComponent.getWidth() - width) / 2.0;
                    drawY += (anchorComponent.getHeight() - height) / 2.0;
                    break;
                case CENTER_RIGHT:
                    // Alinear a la derecha y centrar verticalmente este componente dentro del componente de anclaje
                    drawX += anchorComponent.getWidth() - width;
                    drawY += (anchorComponent.getHeight() - height) / 2.0;
                    break;
                case BOTTOM_LEFT:
                    // Alinear a la parte inferior de este componente dentro del componente de anclaje
                    drawY += anchorComponent.getHeight() - height;
                    break;
                case BOTTOM_CENTER:
                    // Centrar horizontalmente y alinear a la parte inferior de este componente dentro del componente de anclaje
                    drawX += (anchorComponent.getWidth() - width) / 2.0;
                    drawY += anchorComponent.getHeight() - height;
                    break;
                case BOTTOM_RIGHT:
                    // Alinear a la derecha y a la parte inferior de este componente dentro del componente de anclaje
                    drawX += anchorComponent.getWidth() - width;
                    drawY += anchorComponent.getHeight() - height;
                    break;
            }
        }
        return new Vector(drawX, drawY);
    }

    /**
     * Establece el punto de anclaje para el renderizado de este componente.
     *
     * @param renderAnchor El nuevo punto de anclaje.
     */
    public void setRenderAnchor(AnchorPoint renderAnchor) {
        this.renderAnchor = renderAnchor;
    }

    // Define los posibles puntos de anclaje para el renderizado o posicionamiento relativo.
    public enum AnchorPoint {
        TOP_LEFT,      // Esquina superior izquierda (comportamiento por defecto para imágenes)
        TOP_CENTER,    // Centro superior
        TOP_RIGHT,     // Esquina superior derecha
        CENTER_LEFT,   // Centro izquierdo
        CENTER,        // Centro del componente (útil para texto o elementos centrados)
        CENTER_RIGHT,  // Centro derecho
        BOTTOM_LEFT,   // Esquina inferior izquierda (comportamiento por defecto para drawString de texto)
        BOTTOM_CENTER, // Centro inferior
        BOTTOM_RIGHT   // Esquina inferior derecha
    }
}