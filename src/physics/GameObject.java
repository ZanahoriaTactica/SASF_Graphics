package physics;

import core.Vector2D;
import resource.DataLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends DynamicObject {

    private final BufferedImage image;

    private GameObject(Vector2D position, double mass, BufferedImage image) {
        super(position, mass);
        this.image = image != null ? image : DataLoad.BALL;
    }

    public GameObject(Vector2D position, double mass) {
        this(position, mass, DataLoad.BALL);
    }

    public GameObject(Vector2D position) {
        this(position, 0, DataLoad.BALL);
    }

    public GameObject(Vector2D position, BufferedImage image, double mass) {
        this(position, mass, image);
    }

    public GameObject(Vector2D position, BufferedImage image) {
        this(position, 0, image);
    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int) Math.round(getPosition().getX()), (int) Math.round(getPosition().getY()), null);
    }
}