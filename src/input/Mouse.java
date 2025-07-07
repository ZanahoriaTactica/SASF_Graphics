package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que maneja la entrada del ratón.
 * Extiende MouseAdapter para rastrear la posición y el estado de los botones del ratón.
 */
public class Mouse extends MouseAdapter {


    /** Coordenada x actual del puntero del ratón */
    private int x;
    
    /** Coordenada y actual del puntero del ratón */
    private int y;
    
    /** Indica si el botón izquierdo del ratón está presionado */
    private boolean leftButtonPressed;

    /**
     * Obtiene la coordenada x actual del puntero del ratón.
     * 
     * @return La coordenada x en píxeles
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada y actual del puntero del ratón.
     * 
     * @return La coordenada y en píxeles
     */
    public int getY() {
        return y;
    }

    /**
     * Verifica si el botón izquierdo del ratón está siendo presionado.
     * 
     * @return true si el botón izquierdo está presionado, false en caso contrario
     */
    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    /**
     * Se llama cuando se presiona un botón del ratón.
     * Actualiza el estado del botón izquierdo si es presionado.
     *
     * @param e El evento del ratón
     */
    @Override
    public void mousePressed(MouseEvent e) {
        leftButtonPressed = true;
    }

    /**
     * Se llama cuando se libera un botón del ratón.
     * Actualiza el estado del botón izquierdo si es liberado.
     *
     * @param e El evento del ratón
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        leftButtonPressed = false;
    }

    /**
     * Se llama cuando el ratón se mueve (sin botones presionados).
     * Actualiza las coordenadas del puntero.
     *
     * @param e El evento del ratón
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Se llama cuando el ratón es arrastrado (con botón presionado).
     * Actualiza las coordenadas del puntero.
     *
     * @param e El evento del ratón
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}