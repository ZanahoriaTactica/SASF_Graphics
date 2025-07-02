package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Handles mouse input, tracking position and left button state.
 */
public class Mouse extends MouseAdapter {

    private static int x;
    private static int y;
    private static boolean leftButtonPressed;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        leftButtonPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        leftButtonPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}