package river;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class FarmerGameEngineTest {

    private GameEngine engine;
    private final static Item BEANS = Item.ITEM_0;
    private final static Item GOOSE = Item.ITEM_1;
    private final static Item WOLF  = Item.ITEM_2;
    private final static Item FARMER = Item.ITEM_3;

    @Before
    public void setUp() throws Exception {
        engine = new FarmerGameEngine();
    }

    private void transport(Item a){
        engine.loadBoat(a);
        engine.rowBoat();
        engine.unloadBoat(a);
    }

    @Test
    public void testObjectCallThroughs() {

        Assert.assertEquals("F", engine.getItemLabel(FARMER));
        Assert.assertEquals(Location.START, engine.getItemLocation(FARMER));
        Assert.assertEquals(Color.MAGENTA, engine.getItemColor(FARMER));
        /* TODO Check getters for wolf, goose, and beans */
        Assert.assertEquals("W",engine.getItemLabel(WOLF));
        Assert.assertEquals(Location.START, engine.getItemLocation(WOLF));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(WOLF));

        Assert.assertEquals("G",engine.getItemLabel(GOOSE));
        Assert.assertEquals(Location.START, engine.getItemLocation(GOOSE));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(GOOSE));

        Assert.assertEquals("B",engine.getItemLabel(BEANS));
        Assert.assertEquals(Location.START, engine.getItemLocation(BEANS));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(BEANS));

        Assert.assertEquals(engine.numberOfItems(),4);
    }

    @Test
    public void testMidTransport() {

        Assert.assertEquals(Location.START, engine.getItemLocation(GOOSE));

        /*
         * TODO Transport he goose to the other side, unload it, and check that it has
         * the appropriate location
         */
        engine.loadBoat(FARMER);
        engine.loadBoat(GOOSE);
        engine.rowBoat();
        engine.unloadBoat(GOOSE);
        Assert.assertEquals(Location.FINISH, engine.getItemLocation(GOOSE));
        engine.setItemLocation(FARMER, Location.START);
        engine.setItemLocation(GOOSE,Location.START);
        Assert.assertEquals(engine.getItemLocation(GOOSE),Location.START);
        Assert.assertEquals(engine.getItemLocation(FARMER),Location.START);
        engine.loadBoat(WOLF);
        Assert.assertEquals(engine.getItemLocation(WOLF),Location.START);
    }

    @Test
    public void testOther(){
        engine.rowBoat();
        Assert.assertEquals(engine.getItemLocation(FARMER), Location.START);
        engine.loadBoat(GOOSE);
        engine.loadBoat(WOLF);
        engine.rowBoat();
        Assert.assertEquals(engine.getItemLocation(GOOSE), Location.BOAT);
        Assert.assertEquals(engine.getItemLocation(WOLF), Location.BOAT);

    }
    @Test
    public void testWinningGame() {



        // transport the goose
        engine.loadBoat(GOOSE);
        engine.loadBoat(FARMER);
        engine.rowBoat();
        engine.unloadBoat(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        /*
         * TODO The steps above are the first two steps in a winning game, complete the
         * steps and check that the game is won.
         */
        // transport the goose
        engine.loadBoat(WOLF);

        engine.rowBoat();
        engine.unloadBoat(WOLF);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.loadBoat(GOOSE);
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        engine.unloadBoat(GOOSE);

        engine.loadBoat(BEANS);
        engine.rowBoat();
        engine.unloadBoat(BEANS);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());


        engine.loadBoat(GOOSE);
        engine.rowBoat();
        engine.unloadBoat(GOOSE);
        engine.unloadBoat(FARMER);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());


    }

   @Test
    public void testLosingGame() {



        // transport the goose
        engine.loadBoat(GOOSE);
        engine.loadBoat(FARMER);
        engine.rowBoat();
        engine.unloadBoat(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        engine.rowBoat();


        engine.loadBoat(WOLF);
        engine.rowBoat();
        engine.unloadBoat(WOLF);
        engine.rowBoat();

        Assert.assertTrue(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        engine.resetGame();

        engine.setItemLocation(GOOSE, Location.FINISH);
        engine.setItemLocation(BEANS, Location.FINISH);
       Assert.assertTrue(engine.gameIsLost());
    }

  @Test
    public void testError() {

        engine.resetGame();
        // transport the goose
        engine.loadBoat(GOOSE);
        engine.loadBoat(FARMER);
        engine.rowBoat();
        engine.unloadBoat(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // save the state
        Location topLoc = engine.getItemLocation(WOLF);
        Location midLoc = engine.getItemLocation(GOOSE);
        Location bottomLoc = engine.getItemLocation(BEANS);
        Location playerLoc = engine.getItemLocation(FARMER);

        // This action should do nothing since the wolf is not on the same shore as the
        // boat
        engine.loadBoat(Item.ITEM_2);

        // check that the state has not changed
        Assert.assertEquals(topLoc, engine.getItemLocation(WOLF));
        Assert.assertEquals(midLoc, engine.getItemLocation(GOOSE));
        Assert.assertEquals(bottomLoc, engine.getItemLocation(BEANS));
        Assert.assertEquals(playerLoc, engine.getItemLocation(FARMER));
    }
}
