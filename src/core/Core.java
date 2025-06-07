package core;

import input.KeyBoard;
import input.Mouse;
import scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Core extends JFrame implements Runnable {

    private Canvas canvas;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics2D g2d;

    private int width;
    private int height;
    private double fps;
    private double TIME_MEDIUM_OF_ACTUALIZATION;

    private KeyBoard keyBoard;
    private Mouse mouse;

    private boolean running = false;

    private Scene scene;

    public Core(int width, int height, double fps) {
        // Configuration of Core
        this.fps = fps;
        TIME_MEDIUM_OF_ACTUALIZATION = 1000000000 / fps;
        keyBoard = new KeyBoard();
        mouse = new Mouse();
        // Configuration of windows
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Configuration of Canvas
        canvas = new Canvas();
        canvas.setFocusable(true);
        canvas.addKeyListener(keyBoard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        this.add(canvas);
    }

    private void update() {
        scene.update();
    }

    private void render() {
        if ((bs = canvas.getBufferStrategy()) == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        g2d = (Graphics2D) bs.getDrawGraphics();
        g2d.clearRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        scene.render(g2d);

        g2d.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        running = true;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / TIME_MEDIUM_OF_ACTUALIZATION;
            lastTime = now;
            if (delta >= 1) {
                update();
                render();
                delta--;
            }
        }
    }

    public void init() {
        if (scene != null) {
            this.setVisible(true);
            (thread = new Thread(this)).start();
        }
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
