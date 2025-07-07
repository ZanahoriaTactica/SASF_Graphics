package core;

import input.KeyBoard;
import input.Mouse;
import physics.PhysicsEngine;
import scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Clase principal del motor gráfico que maneja el bucle de juego, renderizado y actualización.
 * Extiende JFrame para proporcionar la ventana de la aplicación e implementa Runnable para el bucle principal.
 */
public class Core extends JFrame implements Runnable {

    /** Instancia estática del ratón accesible globalmente */
    public static Mouse mouse;

    /** Manejador de entrada del teclado */
    public static KeyBoard keyboard;

    /** Ancho de la ventana en píxeles */
    private final int width;
    
    /** Alto de la ventana en píxeles */
    private final int height;
    
    /** Fotogramas por segundo objetivo */
    private final double fps;
    
    /** Nanosegundos por fotograma, calculado a partir de los FPS */
    private final double NS_PER_FRAME;
    
    /** Lienzo donde se renderiza el juego */
    private Canvas canvas;
    
    /** Hilo principal del juego */
    private Thread thread;
    
    /** Estrategia de búfer para el renderizado */
    private BufferStrategy bufferStrategy;
    
    /** Contexto gráfico para dibujar en el lienzo */
    private Graphics2D graphics;
    
    /** Bandera que indica si el juego está en ejecución */
    private boolean running = false;
    
    /** Escena actual del juego */
    private Scene scene;
    
    /** Motor de física del juego */
    private PhysicsEngine physicsEngine;

    /**
     * Crea una nueva instancia del motor gráfico.
     *
     * @param width  Ancho de la ventana en píxeles
     * @param height Alto de la ventana en píxeles
     * @param fps    Fotogramas por segundo objetivo
     * @throws IllegalArgumentException Si width, height o fps son menores o iguales a 0
     */
    public Core(int width, int height, double fps) {
        if (width <= 0 || height <= 0 || fps <= 0) {
            throw new IllegalArgumentException("Width, height y fps deben ser mayores a 0");
        }
        this.width = width;
        this.height = height;
        this.fps = fps;
        this.NS_PER_FRAME = 1_000_000_000.0 / fps;
        this.keyboard = new KeyBoard();
        mouse = new Mouse();
        initializeWindow();
        initializeCanvas();
    }

    /**
     * Configura la ventana principal de la aplicación.
     * Establece el tamaño, posición y comportamiento de cierre.
     */
    private void initializeWindow() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Configura el lienzo de dibujo.
     * Hace el lienzo enfocable y añade los listeners de entrada.
     */
    private void initializeCanvas() {
        canvas = new Canvas();
        canvas.setFocusable(true);
        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        add(canvas);
    }

    /**
     * Actualiza la lógica del juego.
     *
     * @param deltaTime Tiempo transcurrido desde la última actualización en segundos
     */
    private void update(double deltaTime) {
        if (scene != null) {
            scene.update(deltaTime);
        }
    }

    /**
     * Renderiza el estado actual del juego.
     * Limpia la pantalla y llama al método render de la escena actual.
     */
    private void render() {
        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            bufferStrategy = canvas.getBufferStrategy();
        }
        graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);

        if (scene != null) {
            scene.render(graphics);
        }

        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * Bucle principal del juego.
     * Maneja la sincronización de fotogramas y las actualizaciones.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;

        running = true;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / NS_PER_FRAME;
            lastTime = now;
            while (delta >= 1) {
                update(1 / fps);
                render();
                delta--;
            }
        }
    }

    /**
     * Inicia el bucle principal del juego.
     *
     * @throws IllegalStateException Si no se ha establecido una escena antes de iniciar
     */
    public void init() {
        if (scene == null) {
            throw new IllegalStateException("Scene must be set before initializing");
        }
        if (!running) {
            setVisible(true);
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Detiene el bucle del juego de manera segura.
     * Espera a que el hilo termine su ejecución actual.
     */
    public void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Establece la escena actual del juego.
     *
     * @param scene La nueva escena a establecer
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Establece el motor de física para el juego.
     *
     * @param physicsEngine Instancia del motor de física
     */
    public void setPhysicsEngine(PhysicsEngine physicsEngine) {
        this.physicsEngine = physicsEngine;
    }
}