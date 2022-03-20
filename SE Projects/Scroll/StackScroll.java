
import java.util.Stack;

public class StackScroll<E> extends AbstractScroll<E> {
    Stack<E> left, right;

    public StackScroll(int max) {
        super(max);
        left = new Stack<>();
        right = new Stack<>();
    }


    // left is bottom most, top is right most
    // abstraction: scroll = [A, B, C] [D,E,F]:10
// stack based rep
// left = [A, B, C]
// right= [F, E, D]
// capacity=10
// from lecture!!!
    @Override
    public void insert(E elem) {
        // check if I need to throw any exceptions
        if (elem == null)
            throw new IllegalArgumentException();
        if (left.size() + right.size() == capacity())
            throw new IllegalStateException();
        // everything is okay, now implement the method
        right.push(elem);
    }


    @Override
    public E delete() {
        if (right.isEmpty())
            throw new IllegalStateException();
        return right.pop();
    }


    @Override
    public void advance() throws IllegalStateException {
        if (rightLength() == 0)
            throw new IllegalStateException();
        left.push(right.pop());
    }
// public void advance() {
// if (right.isEmpty()) throw new IllegalStateException();
// left.push(right.pop());
// }


    @Override
    public void retreat() {
        if (left.isEmpty())
            throw new IllegalStateException();
        right.push(left.pop());
    }


    @Override
    public void reset() {
        // could put ISE here if you wanter
        // this will be linear
        while (leftLength() != 0) {
            retreat();
// right.push(left.pop());
        }
    }


    @Override
    public void advanceToEnd() {
        while (!right.empty()) {
            left.push(right.pop());
        }
    }


    @Override
    public int leftLength() {
        return left.size();
    }


    @Override
    public int rightLength() {
        return right.size();
    }


    public Scroll<E> newInstance() {
        int size = this.capacity();
        Scroll<E> newScroll = new StackScroll<E>(size);
        return newScroll;
    }


    @Override
    public void swapRights(Scroll<E> that) {

        if (that instanceof StackScroll) {
            if ((this.leftLength() + that.rightLength() > this.capacity())
                || (that.leftLength() + this.rightLength() > that.capacity()))
                throw new IllegalStateException();
            StackScroll<E> stackScrollThat = (StackScroll<E>)that;
            Stack<E> temp = this.right;
            this.right = stackScrollThat.right;
            stackScrollThat.right = temp;
        }
        else {
            super.swapRights(that);
        }

    }
}
