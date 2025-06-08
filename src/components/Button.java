package components;

import input.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Component {

    private final BufferedImage bf1;
    private BufferedImage bf2;
    private BufferedImage bf3;
    private Type type;
    private boolean MIB; // mouse in button
    private boolean MLB; // mouse left button
    private boolean alreadyClicked = false;
    private Label label;

    public Button(int x, int y, BufferedImage bf1) {
        super(x, y, bf1.getWidth(), bf1.getHeight());
        this.bf1 = bf1;
        type = Type.ONE_IMAGE;
    }

    public Button(int x, int y, BufferedImage bf1, BufferedImage bf2) {
        super(x, y, bf1.getWidth(), bf1.getHeight());
        this.bf1 = bf1;
        this.bf2 = bf2;
        type = Type.TOW_IMAGE;
    }

    public Button(int x, int y, BufferedImage bf1, BufferedImage bf2, BufferedImage bf3) {
        super(x, y, bf1.getWidth(), bf1.getHeight());
        this.bf1 = bf1;
        this.bf2 = bf2;
        this.bf3 = bf3;
        type = Type.THREE_IMAGE;
    }

    @Override
    public void update() {
        MLB = Mouse.isMLB();
        MIB = contains(Mouse.getX(), Mouse.getY());
        if (!MIB && MLB) {
            alreadyClicked = true;
        }
        if (MIB && MLB && !alreadyClicked) {
            doAction();
            alreadyClicked = true;
        }
        if (!MLB) {
            alreadyClicked = false;
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage bf = imageRender();
        g.drawImage(bf, (int) getVector().getX(), (int) getVector().getY(), null);
    }

    private BufferedImage imageRender() {
        return switch (type) {
            case ONE_IMAGE -> bf1;
            case TOW_IMAGE -> {
                if (MIB) {
                    yield bf2;
                } else {
                    yield bf1;
                }
            }
            case THREE_IMAGE -> {
                if (MIB && MLB) {
                    yield bf3;
                } else if (MIB) {
                    yield bf2;
                } else {
                    yield bf1;
                }
            }
            default -> bf1;
        };
    }

    public void setText(String text, Font font) {
        label = new Label((int) getVector().getX(), (int) getVector().getY(), text, font);
        label.setPosition(Position.CENTER);
    }

    private enum Type {
        ONE_IMAGE, TOW_IMAGE, THREE_IMAGE
    }
}
