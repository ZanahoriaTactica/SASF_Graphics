package components;

import core.Vector2D;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 * A component that displays text with a specified font and color.
 */
public class Label extends Component {

    private static final FontRenderContext FONT_RENDER_CONTEXT = new FontRenderContext(null, true, true);
    private String text;
    private Color textColor;
    private Font textFont;

    public Label(int x, int y, String text, Font font) {
        super(x, y, 0, 0);
        if (font == null) {
            throw new IllegalArgumentException("Font cannot be null");
        }
        this.text = text != null ? text : "";
        this.textColor = Color.YELLOW;
        this.textFont = font;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
        recalculateDimensions();
    }

    @Override
    public void update() {
        // No complex update logic required for a simple label.
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(textColor);
        g.setFont(textFont);

        Vector2D drawPosition = getRenderDrawingCoordinates();
        FontMetrics metrics = g.getFontMetrics(textFont);
        int ascent = metrics.getAscent();

        // Draw text from baseline, adjusting Y to align with top-left anchor
        g.drawString(text, (int) drawPosition.getX(), (int) (drawPosition.getY() + ascent));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text != null ? text : "";
        recalculateDimensions();
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor != null ? textColor : Color.YELLOW;
    }

    public Font getTextFont() {
        return textFont;
    }

    public void setTextFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Font cannot be null");
        }
        this.textFont = font;
        recalculateDimensions();
    }

    private void recalculateDimensions() {
        if (text.isEmpty() || textFont == null) {
            setWidth(0);
            setHeight(0);
            return;
        }
        Rectangle2D textBounds = textFont.getStringBounds(text, FONT_RENDER_CONTEXT);
        LineMetrics textMetrics = textFont.getLineMetrics(text, FONT_RENDER_CONTEXT);
        setWidth((int) Math.ceil(textBounds.getWidth()));
        setHeight((int) Math.ceil(textMetrics.getHeight()));
    }
}