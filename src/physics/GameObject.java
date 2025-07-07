package physics;

import components.Action;
import core.Vector2D;
import resource.DataLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase que representa un objeto de juego con representación visual en pantalla.
 * Extiende DynamicObject para incluir funcionalidad de física y añade renderizado de imagen.
 */
public class GameObject extends DynamicObject {

    /** Imagen que representa visualmente al objeto en pantalla */
    private final BufferedImage image;

    /** Acción asociada al objeto */
    private Action action;

    /**
     * Constructor principal de la clase GameObject.
     *
     * @param position Posición inicial del objeto en el espacio 2D
     * @param mass Masa del objeto. Si es 0, el objeto es estático
     * @param image Imagen que representa al objeto. Si es nula, se usará una por defecto
     */
    private GameObject(Vector2D position, double mass, BufferedImage image) {
        super(position, mass);
        this.image = image != null ? image : DataLoad.BALL;
    }

    /**
     * Crea un nuevo GameObject con posición y masa específicas.
     * Usa una imagen por defecto para representación visual.
     *
     * @param position Posición inicial del objeto
     * @param mass Masa del objeto. Si es 0, el objeto es estático
     */
    public GameObject(Vector2D position, double mass) {
        this(position, mass, DataLoad.BALL);
    }

    /**
     * Crea un nuevo GameObject en la posición especificada con masa 0 (estático).
     * Usa una imagen por defecto para representación visual.
     *
     * @param position Posición inicial del objeto
     */
    public GameObject(Vector2D position) {
        this(position, 0, DataLoad.BALL);
    }

    /**
     * Crea un nuevo GameObject con posición, imagen y masa específicas.
     *
     * @param position Posición inicial del objeto
     * @param image Imagen que representa al objeto. Si es nula, se usará una por defecto
     * @param mass Masa del objeto. Si es 0, el objeto es estático
     */
    public GameObject(Vector2D position, BufferedImage image, double mass) {
        this(position, mass, image);
    }

    /**
     * Crea un nuevo GameObject con posición e imagen específicas, y masa 0 (estático).
     *
     * @param position Posición inicial del objeto
     * @param image Imagen que representa al objeto. Si es nula, se usará una por defecto
     */
    public GameObject(Vector2D position, BufferedImage image) {
        this(position, 0, image);
    }

    /**
     * Renderiza el objeto en el contexto gráfico proporcionado.
     * La posición del objeto se redondea al píxel más cercano para un renderizado más limpio.
     *
     * @param g Contexto gráfico donde se dibujará el objeto
     * @throws IllegalArgumentException Si el contexto gráfico es nulo
     */
    public void render(Graphics2D g) {
        if (g == null) {
            throw new IllegalArgumentException("El contexto gráfico no puede ser nulo");
        }
        if(action != null) {
            action.execute();
        }
        g.drawImage(image, (int) Math.round(getPosition().getX()), (int) Math.round(getPosition().getY()), null);
    }

    public void setAction(Action action){
        this.action = action;
    }

    public Action getAction(){
        return action;
    }
}