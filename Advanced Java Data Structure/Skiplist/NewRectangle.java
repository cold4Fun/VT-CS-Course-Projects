
import java.awt.Rectangle;
/**
 * This is the NewRectangle class I implemented to help with my coding
 * And it extends from java library rectangle class
 * @author Weiting Li
 * @version 9/24/2021
 */
public class NewRectangle extends Rectangle {
    private int height;
    private int width;
    private int x;
    private int y;
    
    /**
     * This is the constructor  
     * @param x coor
     * @param y coor
     * @param w width
     * @param h height
     */
    
    public NewRectangle(int x, int y, int w, int h) {   
        this.height = h;
        this.width = w;
        this.x = x;
        this.y = y;
    } 
    
    /**
     * This is the getter for x
     * @return double x
     */
    @Override
    public  double getX() {
        return  x;
    }
    
    /**
     * This is the getter for y
     * @return double y
     */
    public  double getY() {
        return y;
    }
    
    /**
     * This is the getter for height
     * @return double height
     */
    public  double getHeight() {
        return height;
    }
    
    /**
     * This is the getter for width
     * @return double width
     */
    public  double getWidth() {
        return width;
    }

    /**
     * This is the euqals method
     * 
     * @return boolean T/F
     */   
    @Override
    public boolean equals(Object obj) {    
        return (this.x == ((NewRectangle)obj).x 
            && this.y == ((NewRectangle)obj).y 
            && this.width == ((NewRectangle)obj).getWidth() 
            && (this.height == ((NewRectangle)obj).getHeight()));
    }
    
    /**
     * This is the toString method
     * @return String 
     */ 
    @Override
    public String toString() {    
        return (int)getX() + ", " + (int)getY() + 
            ", " + (int)getWidth() + ", " + (int)getHeight();
    }
    
    /**
     * This is the overlap method
     * @param r
     *         A rectangle to check if overlap
     * @return boolean T/F
     */ 
    public  boolean doOverlap(NewRectangle r) {
        return x < r.x + r.width 
            && x + width > r.x 
            && y < r.y + r.height 
            && y + height > r.y;
    }
       
}