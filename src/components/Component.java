package components;

import core.Vector;

import java.awt.*;


public abstract class Component {

    private Vector vector;
    private int width;
    private int height;
    private ActionInterface actionInterface;
    private Position position;

    public Component(int x, int y, int width, int height) {
        vector = new Vector(x, y);
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
        return new Rectangle((int) vector.getX(), (int) vector.getY(), width, height).contains(x, y);
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
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

    public enum Position {LEFT, RIGHT, CENTER, TOP, DOWN}

}
