

public class LinkedScroll<E> extends AbstractScroll<E> {

    public class Node {
        E contents;
        Node next;
        Node prev;

        public Node(E contents) {
            this.contents = contents;
// next = (Node) Array.newInstance()
        }
    }

    private Node guard;
    private Node cursor;

    public LinkedScroll(int max) {
        super(max);
        guard = new Node(null);
        cursor = new Node(null);
        cursor.prev = guard;
        cursor.next = guard;
    }


    @Override
    public void insert(E elem) {
        
        if(this.leftLength() + this.rightLength() > this.capacity()) {
            throw new IllegalStateException();
        }
        Node newNode = new Node(elem);

        newNode.next = cursor.next;
        newNode.prev = cursor.prev;

        cursor.next.prev = newNode;
        cursor.prev.next = newNode;

        cursor.next = newNode;

    }


    @Override
    public E delete() {
        // if no elements throw exception
        if (guard.next == null) {
            throw new IllegalStateException();
        }
        if (cursor.next == null) {
            throw new IllegalStateException();
        }
        
        @SuppressWarnings("unchecked")
        E temp = (E)cursor.next.contents;
        cursor.prev.next = cursor.next.next;
        cursor.next.next.prev = cursor.prev;

        cursor.next = cursor.next.next;

        return temp;
    }


    @Override
    public void advance() throws IllegalStateException {
        // need to add exception
        // https://piazza.com/class/kyhvhmv3ssjaj?cid=30
        // we can NOT set cursor to an actual node, has to be between nodes
// if (cursor.next.next==null){
// cursor.next.next=guard;
// }
// if (rightLength() == 0) {
// throw new IllegalStateException();
//// cursor.next=guard;
// }
        cursor.prev = cursor.next;
        cursor.next = cursor.next.next;

    }


    @Override
    public void retreat() {
        // cursor between the nodes
        cursor.next = cursor.prev;
        cursor.prev = cursor.prev.prev;
    }


    @Override
    public void reset() {
// guard.next =cursor;
        cursor.prev = guard;
        cursor.next = guard.next;
    }


    @Override
    public void advanceToEnd() {
        // should do in constanttime
        // next will be guard
        // cursor previous will be guard previous, get out pencil and paper and
        // draw it out
        cursor.next = guard;
        cursor.prev = guard.prev;
    }


    @Override
    public int leftLength() {
        Node temp = new Node(null);

        temp.next = cursor.next;
        temp.prev = cursor.prev;

        int i = 0;
        while (temp.prev != guard) {
            temp.prev = temp.prev.prev;
            i++;
        }
        return i;
    }


    @Override
    public int rightLength() {
        Node temp = new Node(null);
        temp.next = cursor.next;
        temp.prev = cursor.prev;
// this.reset();

        int i = 0;
        while (temp.next != guard) {
            temp.next = temp.next.next;
            i++;
        }

        return i;
    }


    @Override
    public Scroll<E> newInstance() {
        int size = this.capacity();
        Scroll<E> newScroll = new LinkedScroll<E>(size);
        return newScroll;
    }


    @Override
    public void swapRights(Scroll<E> that) {
        if (!(that instanceof LinkedScroll)) {
            super.swapRights(that);
            return;
        }

        if ((this.leftLength() + that.rightLength() > this.capacity()) || (that
            .leftLength() + this.rightLength() > that.capacity()))
            throw new IllegalStateException();

        int thisRL = this.rightLength();

        int thatRL = that.rightLength();

        LinkedScroll<E> thatScroll = (LinkedScroll<E>)that;

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
