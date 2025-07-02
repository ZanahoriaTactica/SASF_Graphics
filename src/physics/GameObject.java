package physics;

import core.Vector2D;
import resource.DataLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A game object with a visual representation and physical properties.
 */
public class GameObject extends DynamicObject {

    private final BufferedImage image;

    private GameObject(Vector2D position, double mass, BufferedImage image) {
        super(position, mass);
        this.image = image != null ? image : DataLoad.BALL;
    }

    public GameObject(int x, int y, double mass) {
        this(new Vector2D(x, y), mass, DataLoad.BALL);
    }

    public GameObject(int x, int y) {
        this(new Vector2D(x, y), 0, DataLoad.BALL);
    }

    public GameObject(int x, int y, BufferedImage image, double mass) {
        this(new Vector2D(x, y), mass, image);
    }

    public GameObject(int x, int y, BufferedImage image) {
        this(new Vector2D(x, y), 0, image);
    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int) getPosition().getX(), (int) getPosition().getY(), null);
    }
}