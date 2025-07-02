package components;

import core.Vector2D;
import input.Mouse;
import resource.DataLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A clickable button component with support for different states (default, hover, click).
 */
public class Button extends Component {

    private final BufferedImage defaultImage;
    private final BufferedImage hoverImage;
    private final BufferedImage clickImage;
    private final ButtonType buttonType;
    private boolean isHovered;
    private boolean isPressed;
    private boolean hasBeenClicked;
    private Label buttonLabel;

    private Button(int x, int y, BufferedImage defaultImage, BufferedImage hoverImage, BufferedImage clickImage, ButtonType buttonType) {
        super(x, y, defaultImage.getWidth(), defaultImage.getHeight());
        this.defaultImage = defaultImage;
        this.hoverImage = hoverImage;
        this.clickImage = clickImage;
        this.buttonType = buttonType;
    }

    public Button(int x, int y) {
        this(x, y, DataLoad.BUTTON_BLUE, null, null, ButtonType.ONE_IMAGE);
    }

    public Button(int x, int y, BufferedImage defaultImage) {
        this(x, y, defaultImage, null, null, ButtonType.ONE_IMAGE);
    }

    public Button(int x, int y, BufferedImage defaultImage, BufferedImage hoverImage) {
        this(x, y, defaultImage, hoverImage, null, ButtonType.TWO_IMAGE);
    }

    public Button(int x, int y, BufferedImage defaultImage, BufferedImage hoverImage, BufferedImage clickImage) {
        this(x, y, defaultImage, hoverImage, clickImage, ButtonType.THREE_IMAGE);
    }

    @Override
    public void update() {
        isPressed = Mouse.isLeftButtonPressed();
        isHovered = containsPoint(Mouse.getX(), Mouse.getY());

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
        BufferedImage image = getImageForCurrentState();
        Vector2D drawPosition = getRenderDrawingCoordinates();
        g.drawImage(image, (int) drawPosition.getX(), (int) drawPosition.getY(), null);

        if (buttonLabel != null) {
            buttonLabel.render(g);
        }
    }

    private BufferedImage getImageForCurrentState() {
        if (buttonType == ButtonType.THREE_IMAGE && isHovered && isPressed) {
            return clickImage;
        }
        if (buttonType != ButtonType.ONE_IMAGE && isHovered) {
            return hoverImage;
        }
        return defaultImage;
    }

    public void setText(String text, Font font) {
        if (buttonLabel == null) {
            buttonLabel = new Label((int) getX(), (int) getY(), text, font);
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