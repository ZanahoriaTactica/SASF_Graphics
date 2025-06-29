package scene;

import components.Component;
import physics.GameObject;
import physics.PhysicsEngine;

import java.awt.*;
import java.util.ArrayList;

public class Scene {

    private final ArrayList<Component> components;
    private final PhysicsEngine physicsEngine;

    public Scene(PhysicsEngine physicsEngine) {
        components = new ArrayList<>();
        this.physicsEngine = physicsEngine;
    }

    public void update(double deltaTime) {
        for (Component component : components) {
            component.update();
        }
        if (physicsEngine != null)
            physicsEngine.update(deltaTime);
    }

    public void render(Graphics2D g) {
        for (Component component : components) {
            component.render(g);
        }
        if (physicsEngine != null) {
            for (GameObject gameObject : physicsEngine.getGameObjects()) {
                gameObject.render(g);
            }
        }
    }

    public void addComponent(Component component) {
        if (component != null) components.add(component);
    }

    public void removeComponent(Component component) {
        if (component != null) components.remove(component);

    }
}
