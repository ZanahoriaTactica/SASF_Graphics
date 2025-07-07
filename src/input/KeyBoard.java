package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que maneja la entrada del teclado.
 * Extiende KeyAdapter para rastrear el estado de las teclas presionadas.
 */
public class KeyBoard extends KeyAdapter {

    /** 
     * Arreglo que mantiene el estado de cada tecla (presionada o no).
     * El índice del array corresponde al código de la tecla.
     */
    private final boolean[] keys = new boolean[256];

    /**
     * Verifica si una tecla específica está siendo presionada.
     *
     * @param keyCode Código de la tecla a verificar
     * @return true si la tecla está presionada, false en caso contrario
     */
    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return keys[keyCode];
    }

    /**
     * Se llama cuando una tecla es presionada.
     * Actualiza el estado de la tecla correspondiente a presionada.
     *
     * @param e El evento de teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    /**
     * Se llama cuando una tecla es liberada.
     * Actualiza el estado de la tecla correspondiente a no presionada.
     *
     * @param e El evento de teclado
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }
    }
}