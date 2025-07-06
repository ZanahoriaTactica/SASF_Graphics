package resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class DataLoad {

    public static final BufferedImage BALL = loadImage("ball1.png");
    public static final BufferedImage BUTTON_GRAY = loadImage("btn1Gris.png");
    public static final BufferedImage BUTTON_BLUE = loadImage("btn1Azul.png");

    private DataLoad() {
        throw new AssertionError("Utility class, not instantiable");
    }

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(DataLoad.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }
}