package components;

import core.Vector;
import input.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Component {

    private final BufferedImage defaultImage;   // Imagen por defecto (bf1)
    private BufferedImage hoverImage;           // Imagen al pasar el ratón por encima (bf2)
    private BufferedImage clickImage;           // Imagen al hacer clic (bf3)
    private ButtonType buttonType;              // Tipo de botón (cuántas imágenes tiene para sus estados)
    private boolean isMouseOverButton;          // true si el ratón está sobre el botón (anteriormente MIB)
    private boolean isLeftMouseButtonPressed;   // true si el botón izquierdo del ratón está presionado (anteriormente MLB)
    private boolean hasBeenClickedOrReleased;   // Evita múltiples acciones por un solo clic mantenido o suelta inicial
    private Label buttonLabel;                  // El Label que contendrá el texto del botón.

    // Constructor para botón con una imagen
    public Button(int x, int y, BufferedImage defaultImage) {
        super(x, y, defaultImage.getWidth(), defaultImage.getHeight());
        this.defaultImage = defaultImage;
        this.buttonType = ButtonType.ONE_IMAGE;
    }

    // Constructor para botón con dos imágenes (default, hover)
    public Button(int x, int y, BufferedImage defaultImage, BufferedImage hoverImage) {
        super(x, y, defaultImage.getWidth(), defaultImage.getHeight());
        this.defaultImage = defaultImage;
        this.hoverImage = hoverImage;
        this.buttonType = ButtonType.TWO_IMAGE;
    }

    // Constructor para botón con tres imágenes (default, hover, click)
    public Button(int x, int y, BufferedImage defaultImage, BufferedImage hoverImage, BufferedImage clickImage) {
        super(x, y, defaultImage.getWidth(), defaultImage.getHeight());
        this.defaultImage = defaultImage;
        this.hoverImage = hoverImage;
        this.clickImage = clickImage;
        this.buttonType = ButtonType.THREE_IMAGE;
    }

    @Override
    public void update() {
        isLeftMouseButtonPressed = Mouse.isMLB();
        isMouseOverButton = contains(Mouse.getX(), Mouse.getY());

        // Si el ratón sale del botón o se suelta el clic, restablece el estado para permitir un nuevo clic
        if (!isMouseOverButton && isLeftMouseButtonPressed) {
            hasBeenClickedOrReleased = true;
        }

        // Si el ratón está sobre el botón, se presiona el clic izquierdo y no se ha procesado ya
        if (isMouseOverButton && isLeftMouseButtonPressed && !hasBeenClickedOrReleased) {
            doAction(); // Ejecuta la acción del botón
            hasBeenClickedOrReleased = true; // Marca como procesado para evitar clics repetidos
        }

        // Cuando el botón del ratón se suelta, permite un nuevo ciclo de clic
        if (!isLeftMouseButtonPressed) {
            hasBeenClickedOrReleased = false;
        }

        // Actualiza el label interno si existe
        if (buttonLabel != null) {
            buttonLabel.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage imageToRender = getImageForCurrentState();
        Vector drawingCoordinates = getRenderDrawingCoordinates(); // Usa el método de Component

        g.drawImage(imageToRender, (int) drawingCoordinates.getX(), (int) drawingCoordinates.getY(), null);

        // Renderiza el label interno si existe
        if (buttonLabel != null) {
            buttonLabel.render(g);
        }
    }

    /**
     * Determina la imagen a renderizar basada en el estado actual del botón
     * (por defecto, hover, click).
     *
     * @return La BufferedImage correspondiente al estado actual.
     */
    private BufferedImage getImageForCurrentState() {
        return switch (buttonType) {
            case ONE_IMAGE -> defaultImage;
            case TWO_IMAGE -> {
                yield (isMouseOverButton) ? hoverImage : defaultImage;
            }
            case THREE_IMAGE -> {
                yield (isMouseOverButton && isLeftMouseButtonPressed) ? clickImage : (isMouseOverButton ? hoverImage : defaultImage);
            }
        };
    }

    /**
     * Establece el texto y la fuente para el botón, creando un Label interno si es necesario.
     * El label se configurará para centrarse dentro del botón.
     *
     * @param text El texto a mostrar en el botón.
     * @param font La fuente del texto.
     */
    public void setText(String text, Font font) {
        if (buttonLabel == null) {
            // Inicializa el Label con la misma posición base del botón.
            // La posición relativa y el anclaje se manejan a continuación.
            buttonLabel = new Label(
                    (int) getPosition().getX(),
                    (int) getPosition().getY(),
                    text,
                    font
            );
            buttonLabel.setAnchorComponent(this);       // Ancla el Label al botón
            buttonLabel.setRenderAnchor(AnchorPoint.CENTER); // Centra el Label dentro del botón
        }
        buttonLabel.setText(text);
        buttonLabel.setTextFont(font);
    }

    // Tipos de botones basados en el número de imágenes para sus estados.
    private enum ButtonType {
        ONE_IMAGE,
        TWO_IMAGE,
        THREE_IMAGE
    }
}