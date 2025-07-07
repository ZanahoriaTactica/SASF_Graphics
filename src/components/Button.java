package components;

import core.Core;
import core.Vector2D;
import resource.DataLoad;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Componente de botón interactivo que puede tener diferentes estados visuales.
 * Soporta imágenes para los estados normal, hover y click.
 */
public class Button extends Component {

    /** Imagen por defecto del botón (obligatoria) */
    private final BufferedImage defaultImage;
    
    /** Imagen que se muestra al pasar el ratón por encima (opcional) */
    private final BufferedImage hoverImage;
    
    /** Imagen que se muestra al hacer clic (opcional) */
    private final BufferedImage clickImage;
    
    /** Tipo de botón según las imágenes disponibles */
    private final ButtonType buttonType;
    
    /** Indica si el ratón está sobre el botón */
    private boolean isHovered;
    
    /** Indica si el botón está siendo presionado */
    private boolean isPressed;
    
    /** Control interno para manejar el evento de clic */
    private boolean hasBeenClicked;
    
    /** Etiqueta de texto opcional del botón */
    private Label buttonLabel;

    /**
     * Constructor privado para crear un botón con las imágenes especificadas.
     *
     * @param position Posición del botón
     * @param defaultImage Imagen por defecto (obligatoria)
     * @param hoverImage Imagen para el estado hover (puede ser null)
     * @param clickImage Imagen para el estado click (puede ser null)
     * @param buttonType Tipo de botón según las imágenes proporcionadas
     * @throws IllegalArgumentException Si defaultImage es null
     */
    private Button(Vector2D position, BufferedImage defaultImage, BufferedImage hoverImage,
                  BufferedImage clickImage, ButtonType buttonType) {
        super(position,
                defaultImage != null ? defaultImage.getWidth() : 0,
                defaultImage != null ? defaultImage.getHeight() : 0);

        if (defaultImage == null) {
            throw new IllegalArgumentException("La imagen por defecto no puede ser nula");
        }

        this.defaultImage = defaultImage;
        this.hoverImage = hoverImage;
        this.clickImage = clickImage;
        this.buttonType = buttonType;
        this.renderAnchor = AnchorPoint.TOP_LEFT;
    }

    /**
     * Crea un botón con la imagen por defecto del sistema.
     *
     * @param position Posición del botón
     */
    public Button(Vector2D position) {
        this(position, DataLoad.BUTTON_BLUE, null, null, ButtonType.ONE_IMAGE);
    }

    /**
     * Crea un botón con una sola imagen (sin estados hover o click).
     *
     * @param position Posición del botón
     * @param defaultImage Imagen del botón
     * @throws IllegalArgumentException Si defaultImage es null
     */
    public Button(Vector2D position, BufferedImage defaultImage) {
        this(position, defaultImage, null, null, ButtonType.ONE_IMAGE);
    }

    /**
     * Crea un botón con imágenes para los estados normal y hover.
     *
     * @param position Posición del botón
     * @param defaultImage Imagen por defecto
     * @param hoverImage Imagen para el estado hover
     * @throws IllegalArgumentException Si defaultImage es null
     */
    public Button(Vector2D position, BufferedImage defaultImage, BufferedImage hoverImage) {
        this(position, defaultImage, hoverImage, null, ButtonType.TWO_IMAGE);
    }

    /**
     * Crea un botón con imágenes para todos los estados (normal, hover y click).
     *
     * @param position Posición del botón
     * @param defaultImage Imagen por defecto
     * @param hoverImage Imagen para el estado hover
     * @param clickImage Imagen para el estado click
     * @throws IllegalArgumentException Si defaultImage es null
     */
    public Button(Vector2D position, BufferedImage defaultImage,
                 BufferedImage hoverImage, BufferedImage clickImage) {
        this(position, defaultImage, hoverImage, clickImage, ButtonType.THREE_IMAGE);
    }

    /**
     * Actualiza el estado del botón.
     * Verifica la interacción del ratón y maneja los eventos de clic.
     */
    @Override
    public void update() {
        // Actualizar el estado del botón
        isPressed = Core.mouse.isLeftButtonPressed();
        isHovered = containsPoint(Core.mouse.getX(), Core.mouse.getY());

        // Manejar clics
        if (!isHovered && isPressed) {
            hasBeenClicked = true;
        }

        // Ejecutar acción si se hace clic
        if (isHovered && isPressed && !hasBeenClicked) {
            executeAction();
            hasBeenClicked = true;
        }

        // Restablecer estado de clic
        if (!isPressed) {
            hasBeenClicked = false;
        }

        // Actualizar etiqueta si existe
        if (buttonLabel != null) {
            buttonLabel.update();
        }
    }

    /**
     * Renderiza el botón en el contexto gráfico proporcionado.
     *
     * @param g Contexto gráfico donde se dibujará el botón
     * @throws NullPointerException Si el contexto gráfico es nulo
     */
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

    /**
     * Obtiene la imagen correspondiente al estado actual del botón.
     *
     * @return La imagen a mostrar según el estado actual
     */
    private BufferedImage getImageForCurrentState() {
        if (buttonType == ButtonType.THREE_IMAGE && isHovered && isPressed) {
            return clickImage != null ? clickImage : defaultImage;
        }
        if (buttonType != ButtonType.ONE_IMAGE && isHovered) {
            return hoverImage != null ? hoverImage : defaultImage;
        }
        return defaultImage;
    }

    /**
     * Establece el texto del botón con la fuente especificada.
     * Si no existe una etiqueta, crea una nueva centrada en el botón.
     *
     * @param text Texto a mostrar en el botón
     * @param font Fuente a utilizar para el texto
     * @throws IllegalArgumentException Si la fuente es nula
     */
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

    /**
     * Enumeración que define los tipos de botón según las imágenes disponibles.
     */
    /**
     * Enumeración que define los tipos de botón según las imágenes disponibles.
     */
    private enum ButtonType {
        /** Botón con una sola imagen para todos los estados */
        ONE_IMAGE,
        
        /** Botón con imágenes para los estados normal y hover */
        TWO_IMAGE,
        
        /** Botón con imágenes para todos los estados (normal, hover y click) */
        THREE_IMAGE
    }
}