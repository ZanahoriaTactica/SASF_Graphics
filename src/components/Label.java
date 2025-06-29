package components;

import core.Vector2D;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

public class Label extends Component {

    // Contexto de renderizado de la fuente, puede ser una constante si siempre es el mismo
    private static final FontRenderContext DEFAULT_FONT_RENDER_CONTEXT = new FontRenderContext(null, true, true);
    private String text;
    private Color textColor = Color.YELLOW;
    private Font textFont;

    public Label(int x, int y, String text, Font font) {
        super(
                x,
                y,
                (int) Math.ceil(font.getStringBounds(text, DEFAULT_FONT_RENDER_CONTEXT).getWidth()),
                (int) Math.ceil(font.getLineMetrics(text, DEFAULT_FONT_RENDER_CONTEXT).getHeight())
        );
        this.text = text;
        this.textFont = font;
        // Por defecto, un Label interpreta su Y como el TOP_LEFT de su bounding box.
        // La compensación para drawString se hará en el método render.
        this.renderAnchor = AnchorPoint.TOP_LEFT;
    }

    @Override
    public void update() {
        // No hay lógica de actualización compleja para un Label simple.
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(textColor); // Usa textColor
        g.setFont(textFont);   // Usa textFont

        Vector2D drawingCoordinates = getRenderDrawingCoordinates(); // Obtiene las coordenadas de dibujo ajustadas

        // Obtiene las métricas de la fuente para calcular el ascenso (ascent).
        // El ascenso es la distancia desde la línea base hasta la parte superior de la mayoría de los caracteres.
        FontMetrics fontMetrics = g.getFontMetrics(textFont);
        double ascent = fontMetrics.getAscent();

        // drawString dibuja desde la línea base (BOTTOM_LEFT).
        // Para que nuestro Vector.y represente la esquina superior izquierda,
        // necesitamos añadir el ascenso a la coordenada Y para compensar.
        g.drawString(text, (int) drawingCoordinates.getX(), (int) (drawingCoordinates.getY() + ascent));
    }

    public String getText() {
        return text;
    }

    /**
     * Establece un nuevo texto para el label y recalcula sus dimensiones.
     *
     * @param text El nuevo texto.
     */
    public void setText(String text) {
        this.text = text;
        recalculateDimensions();
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Font getTextFont() {
        return textFont;
    }

    /**
     * Establece una nueva fuente para el label y recalcula sus dimensiones.
     *
     * @param font La nueva fuente.
     */
    public void setTextFont(Font font) {
        this.textFont = font;
        recalculateDimensions();
    }

    /**
     * Recalcula el ancho y alto del label basado en el texto y la fuente actuales.
     * Este método se llama cuando el texto o la fuente del label cambian.
     */
    private void recalculateDimensions() {
        if (textFont == null || text == null || text.isEmpty()) {
            setWidth(0);
            setHeight(0);
            return;
        }
        Rectangle2D bounds = textFont.getStringBounds(text, DEFAULT_FONT_RENDER_CONTEXT);
        LineMetrics metrics = textFont.getLineMetrics(text, DEFAULT_FONT_RENDER_CONTEXT);

        setWidth((int) Math.ceil(bounds.getWidth()));
        setHeight((int) Math.ceil(metrics.getHeight()));
    }
}