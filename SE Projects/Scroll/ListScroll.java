
import java.util.ArrayList;
import java.util.List;


public class ListScroll<E> extends AbstractScroll<E> {
    private List<E> elements;
    private int pos;

    public ListScroll(int max) {
        super(max);
        elements = new ArrayList<>();
        pos = 0;

    }


    /**
     * Adds the specified element to the right of the cursor in this scroll.
     *
     * @param elem
     *            element to be added to this scroll
     * @throws IllegalArgumentException
     *             if the specified element is null
     */
    @Override
    public void insert(E elem) {
        if (elem == null)
            throw new IllegalArgumentException();
        if (this.elements.size() == capacity())
            throw new IllegalStateException();
// elements.add(elem);
        this.elements.add(pos, elem);

    }


    @Override
    public E delete() {
        if (this.elements.isEmpty())
            throw new IllegalArgumentException();
// E subj = elements.remove(pos);
        E subj = this.elements.remove(this.pos);
// return elements.remove(pos);

        return subj;
//
    }


    @Override
    public void advance() {
// public void advance() throws IllegalStateException {
// if (rightLength() == 0) {
// throw new IllegalStateException();
// }

        pos++;
    }


    @Override
    public void retreat() {
        if (elements.isEmpty())
            throw new IllegalStateException();
        pos--;
    }


    @Override
    public void reset() {
        while (pos != 0) {
            retreat();
        }
    }


    @Override
    public void advanceToEnd() {
        while (pos < elements.size()) {
            pos++;
        }
    }


    @Override
    public int leftLength() {
        // returns cursor position
        return pos;
    }


    @Override
    public int rightLength() {
        int size = elements.size();
        int res = size - this.leftLength();
        return res;
    }


    @Override
    public Scroll<E> newInstance() {
// int size = elements.size();
        int size = this.capacity();
        Scroll<E> newScroll = new ListScroll<E>(size);

        return newScroll;
    }


    // from specs in project
    @Override
    public void swapRights(Scroll<E> that) {
        // will only work when argument is list based scroll

        if (!(that instanceof ListScroll)) {
            super.swapRights(that);
            return;
        }

        int thisRL = this.rightLength();

        int thatRL = that.rightLength();

        if ((this.leftLength() + that.rightLength() > this.capacity()) || (that
            .leftLength() + this.rightLength() > that.capacity()))
            throw new IllegalStateException();

        ListScroll<E> thatScroll = (ListScroll<E>)that;

        Scroll<E> temp = this.newInstance();

        Scroll<E> temp1 = that.newInstance();

        for (int i = 0; i < thisRL; i++) {
            temp.insert(this.delete()); // [C]

        }

        for (int i = 0; i < thatRL; i++) {
            temp1.insert(thatScroll.delete());

        }

        for (int i = 0; i < thatRL; i++) {
            this.insert(temp1.delete());
        }

        for (int i = 0; i < thisRL; i++) {
            thatScroll.insert(temp.delete());
        }
    }

}
