package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private static int x, y;
    private static boolean MLB; // mouse left button

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static boolean isMLB() {
        return MLB;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MLB = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MLB = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

}
