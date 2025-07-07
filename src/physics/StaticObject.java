package physics;

import core.Vector2D;

public class StaticObject {

    private Vector2D position;


    public StaticObject(Vector2D position) {
        this.position = position != null ? position : new Vector2D(0, 0);
    }

    public void update(double deltaTime) {
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        if (position != null) {
            this.position = position;
        }
    }
}
