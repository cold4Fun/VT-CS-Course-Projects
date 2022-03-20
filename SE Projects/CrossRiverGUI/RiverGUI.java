package river;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphical interface for the River application
 * 
 * @author Gregory Kulczycki
 */
public class RiverGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================



    private final Rectangle restartButtonRect = new Rectangle(350, 110, 100, 30);
    private final Rectangle farmerButtonRect = new Rectangle(200, 140, 100, 30);
    private final Rectangle monsterButtonRect = new Rectangle(500, 140, 100, 30);

    private final int startBaseX = 20;
    private final int startBoatX = 140;
    private final int finishBoatX = 550;
    private final int finishBaseX = 670;

    private final int itemWidth = 50;
    private final int itemHeight = 50;

    private final int baseY = 275;
    private final int boatY = 335;
    private final int boatWidth = 110;
    private final int boatHeight = 50;
    // ==========================================================
    // Private Fields
    // ==========================================================
    private Graphics g;
    private GameEngine engine; // Model
    private boolean restart = false;
    private int[] dx = { 0, 60, 0, 60, 0, 60};
    private int[] dy = { 0, 0, -60, -60, -120, -120};
    private int[] passengeOff = {0, 60};
    private Item passenger1;
    private Item passenger2;
    private Map<Item, Rectangle> guiMap;
    private Rectangle boatRectangle;
    // ==========================================================
    // Constructor
    // ==========================================================

    public RiverGUI() {

        engine = new FarmerGameEngine();
        guiMap = new HashMap<>();
        passenger1 = null;
        passenger2 = null;
        //guiMap.put()
        addMouseListener(this);
    }

    // ==========================================================
    // Paint Methods (View)
    // ==========================================================

    @Override
    public void paintComponent(Graphics g1) {

        this.g = g1;

        // update position to paint
        refreshItemRectangle();
        refreshBoatRectangle();

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Item item : Item.values()) {
            if (!(item.ordinal() < engine.numberOfItems())) break;
            paintRectangle(engine.getItemLabel(item), engine.getItemColor(item), guiMap.get(item));
        }
        paintRectangle("", Color.ORANGE, boatRectangle);

        String message = "";
        if (engine.gameIsLost()) {
            message = "You Lost!";
            restart = true;
        }
        if (engine.gameIsWon()) {
            message = "You Won!";
            restart = true;
        }
        paintMessage(message, g);
        if (restart) {
            paintButtons();
        }

    }
    private void refreshItemRectangle() {
        for(Item item: Item.values()){
            if (!(item.ordinal()<engine.numberOfItems()))break;
            // IF item is on the left shore THEN figure out x,y coord values
            if (engine.getItemLocation(item) == Location.START){
                guiMap.put(item, new Rectangle(startBaseX + dx[item.ordinal()], baseY + dy[item.ordinal()], itemWidth, itemHeight));
            }else if (engine.getItemLocation(item) == Location.FINISH){
                guiMap.put(item, new Rectangle(finishBaseX + dx[item.ordinal()], baseY + dy[item.ordinal()], itemWidth, itemHeight));
            }else if (engine.getItemLocation(item) == Location.BOAT){

                if(engine.getBoatLocation() == Location.START){

                    if(item == passenger1) {

                        guiMap.put(item, new Rectangle(startBoatX + passengeOff[0], boatY - 60, itemWidth, itemHeight));
                    }else if(item == passenger2){

                        guiMap.put(item, new Rectangle(startBoatX + passengeOff[1], boatY - 60, itemWidth, itemHeight));
                    }
                }else{
                    if(item == passenger1) {
                        guiMap.put(item, new Rectangle(finishBoatX + passengeOff[0], boatY - 60, itemWidth, itemHeight));
                    }else if(item == passenger2){

                        guiMap.put(item, new Rectangle(finishBoatX + passengeOff[1], boatY - 60, itemWidth, itemHeight));
                    }
                }
            }

        }

    }

    private void refreshBoatRectangle(){
        if(engine.getBoatLocation() == Location.START){
            boatRectangle = new Rectangle(startBoatX, boatY, boatWidth, boatHeight);
        }else{
            boatRectangle = new Rectangle(finishBoatX, boatY, boatWidth, boatHeight);
        }
    }


    public void paintMessage(String message, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = 400 - fm.stringWidth(message) / 2;
        int strYCoord = 100;
        g.drawString(message, strXCoord, strYCoord);
    }

    public void paintButtons() {
        g.setColor(Color.BLACK);
        paintBorder(restartButtonRect, 3);
        paintBorder(farmerButtonRect, 3);
        paintBorder(monsterButtonRect, 3);
        g.setColor(Color.PINK);
        paintRectangle("Restart", Color.PINK,restartButtonRect);
        paintRectangle("Farmer", Color.PINK,farmerButtonRect);
        paintRectangle("Monster", Color.PINK,monsterButtonRect);

        //paintRectangle(restartButtonRect, g);
        //paintStringInRectangle("Restart", restartButtonRect.x, restartButtonRect.y, restartButtonRect.width,
        //restartButtonRect.height, g);
    }

    public void paintBorder(Rectangle r, int thickness) {
        g.fillRect(r.x - thickness, r.y - thickness, r.width + (2 * thickness), r.height + (2 * thickness));
    }

    public void paintRectangle(String str, Color color, Rectangle r) {
        g.setColor(color);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        int fontSize = (r.height >= 40) ? 36 : 18;
        g.setFont(new Font("Verdana", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = r.x + r.width / 2 - fm.stringWidth(str) / 2;
        int strYCoord = r.y + r.height / 2 + fontSize / 2 - 4;
        g.drawString(str, strXCoord, strYCoord);
    }

    // ==========================================================
    // Startup Methods
    // ==========================================================

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        RiverGUI newContentPane = new RiverGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // Display the window
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RiverGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods (Controller)
    // ==========================================================

    @Override
    public void mouseClicked(MouseEvent e) {

        if (restart) {
            if (this.restartButtonRect.contains(e.getPoint())) {
                engine.resetGame();

                passenger1 = null;
                passenger2 = null;
                restart = false;
                repaint();
            }else if(this.farmerButtonRect.contains(e.getPoint())){
                engine = new FarmerGameEngine();
                engine.resetGame();
                passenger1 = null;
                passenger2 = null;
                restart = false;
                repaint();
            }else if(this.monsterButtonRect.contains(e.getPoint())){
                engine = new MonsterGameEngine();
                engine.resetGame();
                passenger1 = null;
                passenger2 = null;
                restart = false;
                repaint();
            }
            return;
        }


        for (Item i: Item.values()){
            if (!(i.ordinal() < engine.numberOfItems())) break;
            if(guiMap.get(i).contains(e.getPoint())) {
                if (engine.getItemLocation(i) == Location.BOAT) {
                    engine.unloadBoat(i);
                    if (passenger1 == i && passenger2 != i) {
                        passenger1 = null;
                    } else if (passenger2 == i && passenger1 != i) {
                        passenger2 = null;
                    }
                } else if (engine.getItemLocation(i) == Location.START || engine.getItemLocation(i) == Location.FINISH) {
                    engine.loadBoat(i);
                    if (passenger1 == null ) {
                        passenger1 = i;
                    } else if (passenger2 == null) {
                        passenger2 = i;
                    }
                }
            }
        }

        if(boatRectangle.contains(e.getPoint())){
            engine.rowBoat();
        }
        repaint();
    }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
