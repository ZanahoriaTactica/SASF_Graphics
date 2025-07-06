package scene;

import components.Component;
import physics.PhysicsEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scene {


    private final List<Component> components = new ArrayList<>();


    private final PhysicsEngine physicsEngine;

    public Scene(PhysicsEngine physicsEngine) {
        this.physicsEngine = physicsEngine;
    }

    public void update(double deltaTime) {

        components.forEach(Component::update);


        if (physicsEngine != null) {
            physicsEngine.update(deltaTime);
        }
    }

    public void render(Graphics2D g) {
        if (g == null) {
            throw new NullPointerException("Graphics context cannot be null");
        }


        components.forEach(component -> component.render(g));


        if (physicsEngine != null) {
            physicsEngine.getGameObjects().forEach(gameObject -> {
                if (gameObject != null) {
                    gameObject.render(g);
                }
            });
        }
    }

    public void addComponent(Component component) {
        if (component != null) {
            components.add(component);
        }
    }

    public void removeComponent(Component component) {
        if (component != null) {
            components.remove(component);
        }
    }
}