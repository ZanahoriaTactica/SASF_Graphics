package physics;

import core.Vector2D;
import resource.DataLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends DynamicObject {

    private final BufferedImage bfimg;

    public GameObject(int x, int y, double mass) {
        super(new Vector2D(x, y), mass);
        bfimg = DataLoad.loadImage("ball.png");
    }

    public GameObject(int x, int y) {
        super(new Vector2D(x, y), 0);
        bfimg = DataLoad.loadImage("ball.png");
    }

    public GameObject(int x, int y, BufferedImage bfimg, double mass) {
        super(new Vector2D(x, y), mass);
        this.bfimg = bfimg;
    }

    public GameObject(int x, int y, BufferedImage bfimg) {
        super(new Vector2D(x, y), 0);
        this.bfimg = bfimg;
    }

    public void render(Graphics2D g) {
        g.drawImage(bfimg, (int) getPosition().getX(), (int) getPosition().getY(), null);
    }

}
