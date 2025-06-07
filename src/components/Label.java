package components;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class Label extends Component {

    private String text;
    private Color color = Color.BLACK;
    private Font font;

    public Label(int x, int y, String text, Font font) {
        super(x, y,
                (int) Math.ceil(font.getStringBounds(text, new FontRenderContext(null, true, true)).getWidth()),
                (int) Math.ceil(font.getLineMetrics(text, new FontRenderContext(null, true, true)).getHeight())
        );
        this.text = text;
        this.font = font;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);

        g.drawString(text, getX(), getY());
    }

    public void serText(String text) {
        setWidth((int) Math.ceil(font.getStringBounds(text, new FontRenderContext(null, true, true)).getWidth()));
        setHeight((int) Math.ceil(font.getLineMetrics(text, new FontRenderContext(null, true, true)).getHeight()));
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        setWidth((int) Math.ceil(font.getStringBounds(text, new FontRenderContext(null, true, true)).getWidth()));
        setHeight((int) Math.ceil(font.getLineMetrics(text, new FontRenderContext(null, true, true)).getHeight()));
        this.font = font;
    }

}
