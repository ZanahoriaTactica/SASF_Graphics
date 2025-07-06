package components;

import core.Vector2D;

import java.awt.*;

public abstract class Component {

    protected AnchorPoint renderAnchor;

    private Vector2D position;

    private int width;

    private int height;

    private Action action;
    private Component anchorComponent;

    public Component(Vector2D position, int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height must be non-negative");
        }
        this.position = position != null ? position : new Vector2D(0, 0);
        this.width = width;
        this.height = height;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public void executeAction() {
        if (action != null) {
            action.execute();
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

    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Vector2D getPosition() {
        return position;
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