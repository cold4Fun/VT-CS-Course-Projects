package river;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGameEngine implements GameEngine {

    protected Map<Item, GameObject> map;
    protected Location boatLocation;
    protected int numOnBoat;

    public AbstractGameEngine(){
        map = new HashMap<>();
        boatLocation = Location.START;
        numOnBoat = 0;
    }
    @Override
    public abstract int numberOfItems();

    @Override
    public String getItemLabel(Item item) {
        return map.get(item).getLabel();
    }

    @Override
    public Color getItemColor(Item item) {
        return map.get(item).getColor();
    }

    @Override
    public Location getItemLocation(Item item) {
        return map.get(item).getLocation();
    }

    @Override
    public void setItemLocation(Item item, Location location) {
        map.get(item).setLocation(location);
    }

    @Override
    public Location getBoatLocation() {
        return boatLocation;
    }

    @Override
    public void loadBoat(Item item) {
        int maxCapacity = 2;
        if(map.get(item).getLocation() == getBoatLocation() && numOnBoat < maxCapacity){
            map.get(item).setLocation(Location.BOAT);
            numOnBoat ++;
        }
    }

    @Override
    public void unloadBoat(Item item) {
        if(map.get(item).getLocation() == Location.BOAT){
            map.get(item).setLocation(boatLocation);
            numOnBoat --;
        }
    }

    @Override
    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (boatLocation == Location.START) {
            boatLocation = Location.FINISH;
        } else {
            boatLocation = Location.START;
        }
    }

    @Override
    public boolean gameIsWon() {
        for(Map.Entry<Item, GameObject> kvp: map.entrySet()){
            if(getItemLocation(kvp.getKey()) != Location.FINISH)
                return false;
        }
        return true;
    }

    @Override
    public boolean gameIsLost() {
        return false;
    }

    @Override
    public void resetGame() {
        map.forEach((k, v)->{
            map.get(k).setLocation(Location.START);
        });
        boatLocation = Location.START;
        numOnBoat = 0;
    }
}
