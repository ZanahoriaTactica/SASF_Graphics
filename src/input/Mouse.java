package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {


    private int x;


    private int y;


    private boolean leftButtonPressed;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLeftButtonPressed() {
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