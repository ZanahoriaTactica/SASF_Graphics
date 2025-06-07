package components;

import java.awt.*;

public abstract class Component {

    private int x;
    private int y;
    private int width;
    private int height;
    private ActionInterface actionInterface;
    private Position position;

    public Component(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public void doAction() {
        if (actionInterface != null) {
            actionInterface.doAction();
        }
    }

    public boolean contains(int x, int y) {
        return new Rectangle(this.x, this.y, width, height).contains(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private enum Position {
        LEFT, RIGHT, CENTER, TOP, DOWN
    }

}
