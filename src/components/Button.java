package components;

import core.Core;
import core.Vector2D;
import resource.DataLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Component {


    private final BufferedImage defaultImage;


    private final BufferedImage hoverImage;


    private final BufferedImage clickImage;


    private final ButtonType buttonType;


    private boolean isHovered;


    private boolean isPressed;


    private boolean hasBeenClicked;


    private Label buttonLabel;

    private Button(Vector2D position, BufferedImage defaultImage, BufferedImage hoverImage,
                   BufferedImage clickImage, ButtonType buttonType) {
        super(position, defaultImage != null ? defaultImage.getWidth() : 0,
                defaultImage != null ? defaultImage.getHeight() : 0);
        if (defaultImage == null) {
            throw new IllegalArgumentException("Default image cannot be null");
        }
        this.defaultImage = defaultImage;
        this.hoverImage = hoverImage;
        this.clickImage = clickImage;
        this.buttonType = buttonType;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
    }

    public Button(Vector2D position) {
        this(position, DataLoad.BUTTON_BLUE, null, null, ButtonType.ONE_IMAGE);
    }

    public Button(Vector2D position, BufferedImage defaultImage) {
        this(position, defaultImage, null, null, ButtonType.ONE_IMAGE);
    }

    public Button(Vector2D position, BufferedImage defaultImage, BufferedImage hoverImage) {
        this(position, defaultImage, hoverImage, null, ButtonType.TWO_IMAGE);
    }

    public Button(Vector2D position, BufferedImage defaultImage,
                  BufferedImage hoverImage, BufferedImage clickImage) {
        this(position, defaultImage, hoverImage, clickImage, ButtonType.THREE_IMAGE);
    }

    @Override
    public void update() {

        isPressed = Core.mouse.isLeftButtonPressed();
        isHovered = containsPoint(Core.mouse.getX(), Core.mouse.getY());


        if (!isHovered && isPressed) {
            hasBeenClicked = true;
        }


        if (isHovered && isPressed && !hasBeenClicked) {
            executeAction();
            hasBeenClicked = true;
        }


        if (!isPressed) {
            hasBeenClicked = false;
        }


        if (buttonLabel != null) {
            buttonLabel.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (g == null) {
            throw new NullPointerException("Graphics context cannot be null");
        }


        BufferedImage image = getImageForCurrentState();
        Vector2D drawPosition = getRenderDrawingCoordinates();


        g.drawImage(image, (int) drawPosition.getX(), (int) drawPosition.getY(), null);


        if (buttonLabel != null) {
            buttonLabel.render(g);
        }
    }

    private BufferedImage getImageForCurrentState() {

        if (buttonType == ButtonType.THREE_IMAGE && isHovered && isPressed) {
            return clickImage != null ? clickImage : defaultImage;
        }


        if (buttonType != ButtonType.ONE_IMAGE && isHovered) {
            return hoverImage != null ? hoverImage : defaultImage;
        }


        return defaultImage;
    }

    public void setText(String text, Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Font cannot be null");
        }

        if (buttonLabel == null) {

            buttonLabel = new Label(getPosition(), text, font);
            buttonLabel.setAnchorComponent(this);
            buttonLabel.setRenderAnchor(AnchorPoint.CENTER);
        } else {

            buttonLabel.setText(text);
            buttonLabel.setTextFont(font);
        }
    }

    private enum ButtonType {

        ONE_IMAGE,


        TWO_IMAGE,


        THREE_IMAGE
    }
}