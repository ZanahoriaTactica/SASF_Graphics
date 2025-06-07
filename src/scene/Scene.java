package scene;

import components.Component;

import java.awt.*;
import java.util.ArrayList;

public class Scene {

    private ArrayList<Component> components = new ArrayList<>();

    public void update() {
        for (Component component : components) {
            component.update();
        }
    }

    public void render(Graphics2D g) {
        for (Component component : components) {
            component.render(g);
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
