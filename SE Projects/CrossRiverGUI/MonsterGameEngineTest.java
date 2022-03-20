package river;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class MonsterGameEngineTest extends TestCase {
    private GameEngine engine;
    private final static Item MON1 = Item.ITEM_0;
    private final static Item MON2 = Item.ITEM_2;
    private final static Item MON3 = Item.ITEM_4;
    private final static Item CHK1 = Item.ITEM_1;
    private final static Item CHK2 = Item.ITEM_3;
    private final static Item CHK3 = Item.ITEM_5;



    @Before
    public void setUp() throws Exception {
        engine = new MonsterGameEngine();
    }


    private void transport(Item a){
        engine.loadBoat(a);
        engine.rowBoat();
        engine.unloadBoat(a);
    }


    @Test
    public void testNumberOfItems() {
        assertEquals(engine.numberOfItems(),6);
    }

   /* public void testSetItemLocation() {
        assertEquals();
    }*/
    @Test
    public void testCondition() {
        Assert.assertEquals("M", engine.getItemLabel(MON1));
        Assert.assertEquals(Location.START, engine.getItemLocation(MON1));
        Assert.assertEquals(Color.RED, engine.getItemColor(MON1));
        /* TODO Check getters for wolf, goose, and beans */
        Assert.assertEquals("M", engine.getItemLabel(MON2));
        Assert.assertEquals(Location.START, engine.getItemLocation(MON2));
        Assert.assertEquals(Color.RED, engine.getItemColor(MON2));

        Assert.assertEquals("M", engine.getItemLabel(MON3));
        Assert.assertEquals(Location.START, engine.getItemLocation(MON3));
        Assert.assertEquals(Color.RED, engine.getItemColor(MON3));

        Assert.assertEquals("Mk", engine.getItemLabel(CHK1));
        Assert.assertEquals(Location.START, engine.getItemLocation(CHK1));
        Assert.assertEquals(Color.green, engine.getItemColor(CHK1));

    }
    @Test
    public void testGetBoatLocation() {
        assertEquals(engine.getBoatLocation(), Location.START);
        transport(MON1);
        assertEquals(engine.getBoatLocation(), Location.FINISH);
    }

    @Test
    public void testGameIsWon() {
        engine.setItemLocation(MON1, Location.FINISH);
        assertFalse(engine.gameIsWon());
        engine.setItemLocation(MON2, Location.FINISH);
        assertFalse(engine.gameIsWon());
        engine.setItemLocation(MON3, Location.FINISH);
        assertFalse(engine.gameIsWon());
        engine.setItemLocation(CHK1, Location.FINISH);
        assertFalse(engine.gameIsWon());
        engine.setItemLocation(CHK2, Location.FINISH);
        assertFalse(engine.gameIsWon());
        engine.setItemLocation(CHK3, Location.FINISH);
        assertTrue(engine.gameIsWon());
    }

    @Test
    public void testGameIsLost() {
        transport(CHK3);
        assertTrue(engine.gameIsLost());
        engine.setItemLocation(CHK3, Location.START);
        assertFalse(engine.gameIsLost());
        engine.resetGame();



    }
}