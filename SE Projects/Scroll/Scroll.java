
import java.util.ListIterator;

// TODO Add javadocs
// TODO Add exceptions to signatures

public interface Scroll<E> extends Iterable<E> {
    public void insert(E elem);

    public E delete();

    public void advance();

    public void retreat();

    public void reset();

    public void advanceToEnd();

    public void swapRights(Scroll<E> that);

    public int leftLength();

    public int rightLength();

    public Scroll<E> newInstance();

    public int capacity();

    public ListIterator<E> listIterator();

    public E getNext();

    public E getPrevious();

    public E replace(E element);

    public void splice(Scroll<E> that);

    public void reverse();

    public boolean equals(Object o);

    public int hashCode();

    public String toString();
}

//package boundedscroll;
//
//import java.util.ListIterator;
//
//// TODO Add javadocs
//// TODO Add exceptions to signatures
//
//public interface Scroll<E> extends Iterable<E> {
//    public void insert(E elem);
//
//    public E delete();
//
//    public void advance();
//
//    public void retreat();
//
//    public void reset();
//
//    public void advanceToEnd();
//
//    public void swapRights(Scroll<E> that);
//
//    public int leftLength();
//
//    public int rightLength();
//
//    public Scroll<E> newInstance();
//
//    public int capacity();
//
//    public ListIterator<E> listIterator();
//
//    public E getNext();
//
//    public E getPrevious();
//
//    public E replace(E element);
//
//    public void splice(Scroll<E> that);
//
//    public void reverse();
//
//    public boolean equals(Object o);
//
//    public int hashCode();
//
//    public String toString();
//}