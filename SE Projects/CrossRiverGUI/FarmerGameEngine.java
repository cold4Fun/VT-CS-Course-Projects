package river;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FarmerGameEngine extends AbstractGameEngine {


    private final static Item BEANS = Item.ITEM_0;
    private final static Item GOOSE = Item.ITEM_1;
    private final static Item WOLF = Item.ITEM_2;
    private final static Item FARMER = Item.ITEM_3;

    private Location boatLocation;
    private int numOnBoat;

    public FarmerGameEngine() {
        map = new HashMap<>();
        GameObject wolf = new GameObject("W", Location.START, Color.CYAN);
        GameObject goose = new GameObject("G", Location.START, Color.CYAN);
        GameObject beans = new GameObject("B", Location.START, Color.CYAN);
        GameObject farmer = new GameObject("F", Location.START, Color.MAGENTA);

        map.put(WOLF, wolf);
        map.put(GOOSE, goose);
        map.put(BEANS, beans);
        map.put(FARMER, farmer);
        boatLocation = Location.START;
        numOnBoat = 0;
    }

    @Override
    public int numberOfItems() {
        return 4;
    }

    public void setItemLocation(Item item, Location location) {
        map.get(item).setLocation(location);
    }

    public String getItemLabel(Item id) {
        return map.get(id).getLabel();

    }

    public Location getItemLocation(Item id) {
        return map.get(id).getLocation();

    }

    public Color getItemColor(Item id) {
        return map.get(id).getColor();

    }

    public Location getBoatLocation() {
        return boatLocation;
    }

    public void loadBoat(Item id) {
        int maxCapacity = 2;
        if (map.get(id).getLocation() == getBoatLocation() && numOnBoat < maxCapacity) {
            map.get(id).setLocation(Location.BOAT);
            numOnBoat++;

        }

    }

    public void unloadBoat(Item id) {
        if (map.get(id).getLocation() == Location.BOAT) {
            map.get(id).setLocation(boatLocation);
            numOnBoat--;
        }

    }

    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (numOnBoat == 0) return;
        if (map.get(FARMER).getLocation() != Location.BOAT) return;
        if (boatLocation == Location.START) {
            boatLocation = Location.FINISH;
        } else {
            boatLocation = Location.START;
        }
    }

    public boolean gameIsWon() {

        return getItemLocation(WOLF) == Location.FINISH && getItemLocation(GOOSE) == Location.FINISH
                && getItemLocation(BEANS) == Location.FINISH && getItemLocation(FARMER) == Location.FINISH;
    }

    @Override
    public boolean gameIsLost() {
        if (getItemLocation(GOOSE) == Location.BOAT) {
            return false;
        }
        if (getItemLocation(GOOSE) == getItemLocation(FARMER)) {
            return false;
        }
        if (getItemLocation(GOOSE) == boatLocation) {
            return false;
        }
        if (getItemLocation(GOOSE) == getItemLocation(WOLF)) {
            return true;
        }
        return getItemLocation(GOOSE) == getItemLocation(BEANS);
    }

    public void resetGame() {

        map.forEach((k, v) -> {
            map.get(k).setLocation(Location.START);
        });
        boatLocation = Location.START;
        numOnBoat = 0;
    }

}
