package resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DataLoad {

    public static BufferedImage loadImage(String ruta) {
        try {
            return ImageIO.read(DataLoad.class.getResource(ruta));
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
