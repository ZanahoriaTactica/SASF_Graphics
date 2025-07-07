package components;

import core.Vector2D;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 * Componente de interfaz de usuario que muestra texto.
 * Permite personalizar la fuente, el color y el tamaño del texto.
 */
public class Label extends Component {

    /** Contexto para el renderizado de fuentes */
    private static final FontRenderContext FONT_RENDER_CONTEXT = new FontRenderContext(null, true, true);

    /** Texto a mostrar en la etiqueta */
    private String text;
    
    /** Color del texto */
    private Color textColor;
    
    /** Fuente del texto */
    private Font textFont;

    /**
     * Crea una nueva etiqueta con la posición, texto y fuente especificados.
     *
     * @param position Posición de la etiqueta
     * @param text Texto a mostrar (puede ser null, se convertirá a cadena vacía)
     * @param font Fuente a utilizar (no puede ser null)
     * @throws IllegalArgumentException Si la fuente es null
     */
    public Label(Vector2D position, String text, Font font) {
        super(position, 0, 0);
        if (font == null) {
            throw new IllegalArgumentException("Font cannot be null");
        }
        this.text = text != null ? text : "";
        this.textColor = Color.WHITE;
        this.textFont = font;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
        recalculateDimensions();
    }

    /**
     * Actualiza el estado de la etiqueta.
     * No realiza ninguna operación ya que la etiqueta es estática.
     */
    @Override
    public void update() {
        // No se requiere lógica de actualización para una etiqueta estática
    }

    /**
     * Renderiza la etiqueta en el contexto gráfico proporcionado.
     *
     * @param g Contexto gráfico donde se dibujará la etiqueta
     * @throws NullPointerException Si el contexto gráfico es nulo
     */
    @Override
    public void render(Graphics2D g) {
        if (g == null) {
            throw new NullPointerException("Graphics context cannot be null");
        }

        g.setColor(textColor);
        g.setFont(textFont);

        Vector2D drawPosition = getRenderDrawingCoordinates();
        FontMetrics metrics = g.getFontMetrics(textFont);
        int ascent = metrics.getAscent();

        g.drawString(text, (int) drawPosition.getX(), (int) (drawPosition.getY() + ascent));
    }

    /**
     * Obtiene el texto actual de la etiqueta.
     *
     * @return El texto de la etiqueta (nunca null)
     */
    public String getText() {
        return text;
    }

    /**
     * Establece el texto de la etiqueta.
     * Si se proporciona null, se usará una cadena vacía.
     *
     * @param text Nuevo texto para la etiqueta
     */
    public void setText(String text) {
        this.text = text != null ? text : "";
        recalculateDimensions();
    }

    /**
     * Obtiene el color actual del texto.
     *
     * @return Color del texto (nunca null)
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Establece el color del texto.
     * Si se proporciona null, se usará Color.YELLOW por defecto.
     *
     * @param textColor Nuevo color del texto
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor != null ? textColor : Color.YELLOW;
    }

    /**
     * Obtiene la fuente actual del texto.
     *
     * @return Fuente del texto (nunca null)
     */
    public Font getTextFont() {
        return textFont;
    }

    /**
     * Establece la fuente del texto.
     *
     * @param font Nueva fuente a utilizar (no puede ser null)
     * @throws IllegalArgumentException Si la fuente es null
     */
    public void setTextFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Font cannot be null");
        }
        this.textFont = font;
        recalculateDimensions();
    }

    /**
     * Recalcula las dimensiones de la etiqueta según el texto y la fuente actuales.
     * Se llama automáticamente cuando cambia el texto o la fuente.
     */
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