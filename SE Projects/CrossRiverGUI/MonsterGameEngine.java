package river;

import java.awt.*;

import java.util.HashMap;


public class MonsterGameEngine extends AbstractGameEngine {
    private final static Item MON1 = Item.ITEM_0;
    private final static Item MON2 = Item.ITEM_2;
    private final static Item MON3 = Item.ITEM_4;
    private final static Item CHK1 = Item.ITEM_1;
    private final static Item CHK2 = Item.ITEM_3;
    private final static Item CHK3 = Item.ITEM_5;

    private Location boatLocation;
    private int numOnBoat;


    public MonsterGameEngine() {
        map = new HashMap<>();
        GameObject mon1 = new GameObject("M", Location.START, Color.RED);
        GameObject mon2 = new GameObject("M", Location.START, Color.RED);
        GameObject mon3 = new GameObject("M", Location.START, Color.RED);
        GameObject chk1 = new GameObject("Mk", Location.START, Color.green);
        GameObject chk2 = new GameObject("Mk", Location.START, Color.green);
        GameObject chk3 = new GameObject("Mk", Location.START, Color.green);

        map.put(MON1, mon1);
        map.put(MON2, mon2);
        map.put(MON3, mon3);
        map.put(CHK1, chk1);
        map.put(CHK2, chk2);
        map.put(CHK3, chk3);

        boatLocation = Location.START;
        numOnBoat = 0;
    }

    @Override
    public int numberOfItems() {
        return 6;
    }

    public void setItemLocation(Item item, Location location){
        map.get(item).setLocation(location);
    }

    public String getItemLabel(Item id) {
        return map.get(id).getLabel();

    }

    private boolean leftMoreMonsters(){
        int LMons = 0;
        int LMuns = 0;
        for (Item item : Item.values()) {
            if (item.ordinal()%2 == 0 && getItemLocation(item) == Location.START){
                LMons ++;
            }
            else if(item.ordinal()%2 == 0 && getItemLocation(item) == Location.BOAT){
                if(boatLocation == Location.START){
                    LMons++;
                }
            }
            else if(item.ordinal()%2 == 1 && getItemLocation(item) == Location.START){
                LMuns ++;
            }
            else if(item.ordinal()%2 == 1 && getItemLocation(item) == Location.BOAT){
                if(boatLocation == Location.START){
                    LMuns ++;
                }
            }
        }
        if(LMons > LMuns && LMuns != 0 && LMons != 0){
            return true;
        }else{
            return false;
        }

    }



    private boolean rightMoreMonsters(){
        int RMons = 0;
        int RMuns = 0;
        for (Item item : Item.values()) {
            if (item.ordinal()%2 == 0 && getItemLocation(item) == Location.FINISH){
                RMons ++;
            }
            else if(item.ordinal()%2 == 0 && getItemLocation(item) == Location.BOAT){
                if(boatLocation == Location.FINISH){
                    RMons++;
                }
            }
            else if(item.ordinal()%2 == 1 && getItemLocation(item) == Location.FINISH){
                RMuns ++;
            }
            else if(item.ordinal()%2 == 1 && getItemLocation(item) == Location.BOAT){
                if(boatLocation == Location.FINISH){
                    RMuns ++;
                }
            }
        }
        if(RMons > RMuns && RMuns != 0 && RMons != 0){
            return true;
        }else{
            return false;
        }
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
        if(map.get(id).getLocation() == getBoatLocation() && numOnBoat < maxCapacity){
            map.get(id).setLocation(Location.BOAT);
            numOnBoat ++;

        }

    }

    public void unloadBoat(Item id) {
        if(map.get(id).getLocation() == Location.BOAT){
            map.get(id).setLocation(boatLocation);
            numOnBoat --;
        }

    }

    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (numOnBoat == 0) return;
        if (boatLocation == Location.START) {
            boatLocation = Location.FINISH;
        } else {
            boatLocation = Location.START;
        }
    }

    public boolean gameIsWon() {

        return getItemLocation(MON1) == Location.FINISH && getItemLocation(MON2) == Location.FINISH
                && getItemLocation(MON3) == Location.FINISH && getItemLocation(CHK1) == Location.FINISH
        && getItemLocation(CHK2) == Location.FINISH && getItemLocation(CHK3) == Location.FINISH;
    }

    @Override
    public boolean gameIsLost() {
        if(leftMoreMonsters()){
            return true;
        }else if(rightMoreMonsters()){
            return true;
        }else{
            return false;
        }
    }

    public void resetGame() {

        map.forEach((k, v)->{
            map.get(k).setLocation(Location.START);
        });
        boatLocation = Location.START;
        numOnBoat = 0;
    }
}
