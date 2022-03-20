import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.crypto.Data;
import java.awt.Rectangle;
/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, NewRectangle> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, NewRectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String,NewRectangle> pair) {
        
       // set up every parameters
        int x =  (int)pair.getValue().getX(); 
        int y =  (int)pair.getValue().getY(); 
        int w =  (int)pair.getValue().getWidth(); 
        int h =  (int)pair.getValue().getHeight(); 
        
        //print header
        //System.out.println("insert "+pair.getKey()+" "+ x +" "+ y + " "+ w + " "+ h);
        
        if (!Character.isLetter(pair.getKey().charAt(0))) {
            System.out.println("Rectangle rejected: (" + pair.getKey() +", "+pair.getValue().toString()+")");
        }else if(x >= 0 && y >= 0 && w >0 && h>0 && ((x + w) <= 1024) && ((y + h) <=1024)) {
            list.insert(pair);
            System.out.println("Rectangle inserted: (" + pair.getKey() +", "+pair.getValue().toString()+")");
        }else {
            System.out.println("Rectangle rejected: (" + pair.getKey() +", "+pair.getValue().toString()+")");
        }   

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, NewRectangle> temp = list.remove(name);
        if(temp== null) {
            System.out.println( "Rectangle not removed: ("+name+")");}
        else {
            System.out.println("Rectangle removed: ("+name+", "+temp.getValue().toString()+")");
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        if(x< 0 || y <0 || w<=0 || h <= 0 ||x+w >= 1024 || y+h >=1024) {
            System.out.println("Rectangle rejected: ("+x+", "+y+","+w+", "+h+")");
        }else{
        NewRectangle rec1 = new NewRectangle(x,y,w,h);
        //System.out.println(x + " " + y);
        KVPair<String, NewRectangle> temp = list.removeByValue(rec1);
        //System.out.println(temp.getValue().x + " " + temp.getValue().y);
        if(temp== null) {
            System.out.println( "Rectangle not removed: ("+rec1.toString()+")");}
        else {
            System.out.println("Rectangle removed: ("+temp.getKey()+", "+temp.getValue().toString()+")");
            }
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList Iterator for this 
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        if( w >0 && h>0) {
            NewRectangle rec1 = new NewRectangle(x,y,w,h);
            System.out.println("Rectangles intersecting region ("+x+", "+y+", "+w+", "+h+"):");
            Iterator<KVPair<String, NewRectangle>> a = list.iterator();
            while(a.hasNext()) {
                KVPair<String, NewRectangle> kvp1 = a.next();
                if(kvp1.getValue().doOverlap(rec1)) {
                    System.out.println("("+kvp1.getKey()+ ", "
                        +(int)kvp1.getValue().getX()+", "
                        +(int)kvp1.getValue().getY()+", "
                        +(int)kvp1.getValue().getWidth()+", "
                        +(int)kvp1.getValue().getHeight()+")");
                }
            }
        
        }else {
            System.out.println("Rectangle rejected: ("+x+", "+y+", "+w+", "+h+"):");
        }
            
        
    }
  



    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList Iterators for this
     */
    public void intersections() {
        Iterator<KVPair<String, NewRectangle>> a = list.iterator();
        
        //KVPair<String, NewRectangle> kvp1;
        //KVPair<String, NewRectangle> kvp2;
                                                                            //SkipListIterator b = new SkipListIterator();
                                                                                        //System.out.print(kvp1);
                                                                                    System.out.print("Intersections pairs:\n");
       while(a.hasNext()) {
                                                                                           //kvp1= a.next();
           KVPair<String, NewRectangle> kvp1 = a.next();
                                                                                           //System.out.println(kvp1);
           Iterator<KVPair<String, NewRectangle>> b = list.iterator();
           //System.out.println(kvp1.getValue().getX() + " " +kvp1.getValue().getY() + " " + kvp1.getValue().getWidth() + " " +kvp1.getValue().getHeight() );
           while(b.hasNext()) {
               KVPair<String, NewRectangle> kvp2 = b.next();
              
               
               if(!kvp2.equals(kvp1)) {
                   
                   if(kvp1.getValue().doOverlap(kvp2.getValue())) {
                                                                                       //System.out.println(kvp1.getValue().intersects(kvp2.getValue()));
                                                                                       //System.out.println("2: "+kvp2);
                       System.out.println("("+kvp1.getKey()+ ", "
                   +(int)kvp1.getValue().getX()+", "
                   +(int)kvp1.getValue().getY()+", "
                   +(int)kvp1.getValue().getWidth()+", "
                   +(int)kvp1.getValue().getHeight()+" | "
                   +kvp2.getKey()+ ", "
                   +(int)kvp2.getValue().getX()+", "
                   +(int)kvp2.getValue().getY()+", "
                   +(int)kvp2.getValue().getWidth()+", "
                   +(int)kvp2.getValue().getHeight()+")");//intersect
                   
                   }
               
               }
           }
       }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        //System.out.println("search "+name);
        ArrayList<KVPair<String, NewRectangle>> a = list.search(name);
        if(a.isEmpty()) {
            System.out.println("Rectangle not found: "+name);
        }else if(!Character.isLetter(name.charAt(0))){
            
        }else {
            System.out.println("Rectangles found:");
            for(int i=0; i<a.size();i++) {
                
                System.out.println(a.get(i));
            }
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
