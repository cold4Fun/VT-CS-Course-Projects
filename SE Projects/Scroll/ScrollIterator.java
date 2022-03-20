
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ScrollIterator<E> implements ListIterator<E> {

    Scroll<E> scroll;

    // NO TYPE IN CONSTRUCTOR
    public ScrollIterator(Scroll<E> scroll) {
        this.scroll = scroll;
    }


    @Override
    public boolean hasNext() {
        // how to check if scroll has next element?
        // scroll has element after cursor
        return scroll.rightLength() != 0;
// return false;
    }


    @Override
    public E next() {
        // if no next element throw no such element excep
        if (!hasNext())
            throw new NoSuchElementException();
        E ne = scroll.getNext();
        scroll.advance();
        return ne;
    }


    @Override
    public boolean hasPrevious() {
        // right length not equal to zero
        return scroll.leftLength() != 0;
    }


    @Override
    public E previous() {
        if (!hasPrevious())
            throw new NoSuchElementException();
        return scroll.getPrevious();
    }


    // [A, B, C] [D,E,F]
    // cursor is between index 2 nd 3
    // get 3 using the left length
    @Override
    public int nextIndex() {
        return scroll.leftLength();
    }


    @Override
    public int previousIndex() {

        return scroll.leftLength() - 1;
    }


    // SKIP remove, set, and add. Diagram says we dont need it.
    @Override
    public void remove() {

    }


    @Override
    public void set(E e) {

    }


    @Override
    public void add(E e) {

    }
}
