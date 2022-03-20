package river;

import java.awt.*;

public class GameObject {

    private String label;
    private Location location;
    private Color color;

    public GameObject(String name, Location location, Color col){
        this.label = name;
        this.location= location;
        this.color = col;
    }
    public String getLabel() {
        return label;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public Color getColor() {
        return color;
    }
}
