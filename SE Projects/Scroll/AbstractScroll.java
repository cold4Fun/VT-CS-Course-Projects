
import java.util.Iterator;
import java.util.ListIterator;

public abstract class AbstractScroll<E> implements Scroll<E> {

    private final int capacity;

    public int getCapacity() {
        return capacity;
    }


    public AbstractScroll(int max) {
        capacity = max;
    }


    @Override
    public void swapRights(Scroll<E> that) {

        int thisRL = this.rightLength();

        int thatRL = that.rightLength();

        if ((this.leftLength() + that.rightLength() > this.capacity()) || (that
            .leftLength() + this.rightLength() > that.capacity()))
            throw new IllegalStateException();

        Scroll<E> temp = this.newInstance();

        Scroll<E> temp1 = that.newInstance();

        for (int i = 0; i < thisRL; i++) {
            temp.insert(this.delete()); // [C]

        }

        for (int i = 0; i < thatRL; i++) {
            temp1.insert(that.delete());

        }

        for (int i = 0; i < thatRL; i++) {
            this.insert(temp1.delete());
        }

        for (int i = 0; i < thisRL; i++) {
            that.insert(temp.delete());
        }

    }


    @Override
    public int capacity() {
        return capacity;
    }


    @Override
    public ListIterator<E> listIterator() {
        return new ScrollIterator<E>(this);
    }


    // PRE: scroll [A, B, C] [D,E,F]
    // stmt: x = scroll.getNext()
    // post: scroll=[A, B, C] [D,E,F] and x=D
    // dont want to advance the cursor, cursor position equals
    // thing after the cursor position is x
    @Override
    public E getNext() {
        // exception here..
        E result = delete();
        // D disappears and is stored in result
        insert(result);
// advance();
// why does this work??? Aliasing, access same object from 2 diff variables
        return result;
    }


    @Override
    public E getPrevious() {
        // may have to retreat and then do something like getNext
        retreat();
        E result = delete();
        // D disappears and is stored in result
        insert(result);
        advance();
        return result;
    }


    @Override
    public E replace(E element) {
        E ori = this.delete();
        this.insert(element);
        return ori;
    }


    @Override
    public void splice(Scroll<E> that) {
        if (that.leftLength() != 0) {
            throw new IllegalStateException();
        }
        if (that.rightLength() == 0) {
            return;
        }

        E ele = that.delete();

        insertToLeft(ele);

        this.splice(that);

    }


    private void insertToLeft(E element) {

        this.insert(element);
        this.advance();
    }


    @Override
    public void reverse() {
        if (this.leftLength() != 0) {
            throw new IllegalStateException();
        }
        if (this.rightLength() != 0) {
            Scroll<E> tempScroll = this.newInstance();

            int l = this.rightLength();
            for (int i = 0; i < l; i++) {
                tempScroll.insert(this.delete());
            }
            this.reset();
            this.swapRights(tempScroll);
            this.advanceToEnd();
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new ScrollIterator<E>(this);
    }


// TODO equals
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Scroll)) {
            return false;
        }
        Scroll<?> that = (Scroll<?>)obj;

        if (this.capacity() != that.capacity()) {
            return false;
        }
        else if (this.leftLength() != that.leftLength() || this
            .rightLength() != that.rightLength()) {
            return false;
        }

        int thisL = this.leftLength();
        int thatL = that.leftLength();
        this.reset();
        that.reset();

        Iterator<E> thisIter = this.iterator();

        Iterator<?> thatIter = that.iterator();

        while (thisIter.hasNext()) {
            E elem = thisIter.next();
            Object o = thatIter.next();
            if (!elem.equals(o)) {
                return false;
            }
        }

        this.reset();
        that.reset();

        for (int i = 0; i < thisL; i++) {
            this.advance();
        }
        for (int i = 0; i < thatL; i++) {
            that.advance();
        }

        return true;

    }


    @Override
    public int hashCode() {

        int num = 17;
        int LL = this.leftLength();
        this.reset();
        Iterator<E> iter = this.iterator();

        while (iter.hasNext()) {
            num = 31 * num + iter.next().hashCode();
        }
        num = 31 * num + capacity();

        this.reset();
        for (int i = 0; i < LL; i++) {
            advance();
        }
        return num;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // [..][..]: cap
        // just iterate over things and put them in there
        int Llength = leftLength();
        reset();
        sb.append("[");

        for (int i = 0; i < Llength; i++) {
            E elem = getNext();
            sb.append(elem);

            if (i < Llength - 1) {
                // have to reverse the right stack
                sb.append(" ");
            }
            advance();// if left length have to reset
            // do the right pas and the capacity
        }
        sb.append("]");

        int Rlength = rightLength();

        sb.append("[");

        for (int i = 0; i < Rlength; i++) {
            E elem = getNext();
            sb.append(elem);

            if (i < Rlength - 1) {
                // have to reverse the right stack
                sb.append(" ");
            }
            advance();// if left length have to reset
            // do the right pas and the capacity
        }
        sb.append("]");

        reset();
        for (int i = 0; i < Llength; i++) {
            advance();
        }

        return sb.toString();

// public Scroll
    }
}
