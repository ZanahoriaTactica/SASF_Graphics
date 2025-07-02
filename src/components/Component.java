package components;

import core.Vector2D;

import java.awt.*;

/**
 * Abstract base class for UI components with position, size, and rendering capabilities.
 */
public abstract class Component {

    private Vector2D position;
    private int width;
    private int height;
    protected AnchorPoint renderAnchor;
    private Action action;
    private Component anchorComponent;

    public Component(int x, int y, int width, int height) {
        this.position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public void executeAction() {
        if (action != null) {
            action.doAction();
        }
    }

    public boolean containsPoint(int x, int y) {
        return x >= position.getX() && x < position.getX() + width &&
                y >= position.getY() && y < position.getY() + height;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void setPosition(double x, double y) {
        this.position = new Vector2D(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setAnchorComponent(Component anchorComponent) {
        this.anchorComponent = anchorComponent;
    }

    public Vector2D getRenderDrawingCoordinates() {
        double drawX = position.getX();
        double drawY = position.getY();
        if (anchorComponent != null) {
            drawX += (anchorComponent.getWidth() - width) * renderAnchor.getHorizontalRatio();
            drawY += (anchorComponent.getHeight() - height) * renderAnchor.getVerticalRatio();
        }
        return new Vector2D(drawX, drawY);
    }

    public void setRenderAnchor(AnchorPoint renderAnchor) {
        this.renderAnchor = renderAnchor != null ? renderAnchor : AnchorPoint.TOP_LEFT;
    }
}