package core;

import input.KeyBoard;
import input.Mouse;
import physics.PhysicsEngine;
import scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Core extends JFrame implements Runnable {


    public static Mouse mouse;


    private final int width;


    private final int height;


    private final double fps;


    private final double NS_PER_FRAME;


    private final KeyBoard keyboard;


    private Canvas canvas;


    private Thread thread;


    private BufferStrategy bufferStrategy;


    private Graphics2D graphics;


    private boolean running = false;


    private Scene scene;


    private PhysicsEngine physicsEngine;

    public Core(int width, int height, double fps) {
        this.width = width;
        this.height = height;
        this.fps = fps;
        this.NS_PER_FRAME = 1_000_000_000.0 / fps;
        this.keyboard = new KeyBoard();
        mouse = new Mouse();
        initializeWindow();
        initializeCanvas();
    }

    private void initializeWindow() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initializeCanvas() {
        canvas = new Canvas();
        canvas.setFocusable(true);
        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        add(canvas);
    }

    private void update(double deltaTime) {
        if (scene != null) {
            scene.update(deltaTime);
        }
    }

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

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPhysicsEngine(PhysicsEngine physicsEngine) {
        this.physicsEngine = physicsEngine;
    }
}