package lab_two;

class MyStackEmptyException extends Exception {
    public MyStackEmptyException(String message) {
        super(message);
    }
}

public class MyStack<T> {

    private final MyLinkedList<T> stack;
    private int size = 0;

    public MyStack() {
        this.stack = new MyLinkedList<>();
    }

    /**
     * add a value to the top of the stack
     *
     * @param val value to be added into the stack
     */
    public void push(T val) {
        this.stack.addLast(val);
        this.size++;
    }

    /**
     * get the value at the top of the stack, and remove it
     *
     * @return the last value added into the stack
     */
    public T pop() throws MyStackEmptyException {
        try {
            T v = this.stack.rmLast();
            this.size--;
            return v;
        } catch (MyLinkedListEmptyException e) {
            throw new MyStackEmptyException("");
        }
    }

    /**
     * get the value at the top of the stack, but not remove it
     *
     * @return the last value added into the stack
     */
    public T top() throws MyStackEmptyException {
        if (this.size == 0) {
            throw new MyStackEmptyException("");
        }
        try {
            return this.stack.at(this.size - 1);
        } catch (MyLinkedListIndexException e) {
            return null;
        }

    }

    /**
     * @return whether the stack contains any elements or not
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * todo
     * size of the stack
     *
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * todo
     * Elements will be printed in order
     * May simply call T.toString() method
     *
     * @return all elements in the stack
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return "";
        }
        MyIterator<T> iter = this.stack.iterator();
        StringBuilder sb = new StringBuilder("");
        for (T t = iter.last(); !iter.isEnd4Last(); t = iter.prev()) {
            sb.append(t.toString()).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.toString().length());
        return sb.toString();
    }
}
