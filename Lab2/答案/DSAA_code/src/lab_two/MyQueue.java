package lab_two;

class MyQueueEmptyException extends Exception {
    public MyQueueEmptyException(String message) {
        super(message);
    }
}

public class MyQueue<T> {
    private final MyLinkedList<T> queue;
    private int size = 0;

    public MyQueue() {
        queue = new MyLinkedList<>();
    }

    /**
     * add a value to the end of the queue
     *
     * @param val a value
     */
    public void enqueue(T val) {
        this.queue.addLast(val);
        this.size++;
    }

    /**
     * get the value at the top (beginning) of the queue, and remove it
     *
     * @return the value
     */
    public T dequeue() throws MyQueueEmptyException {
        try {
            T v = this.queue.rmFirst();
            this.size--;
            return v;
        } catch (MyLinkedListEmptyException e) {
            throw new MyQueueEmptyException("");
        }
    }

    /**
     * get the value at the top (beginning) of the queue, but not remove it
     *
     * @return the value
     */
    public T top() throws MyQueueEmptyException {
        try {
            return this.queue.at(0);
        } catch (MyLinkedListIndexException e) {
            throw new MyQueueEmptyException("");
        }
    }

    /**
     * @return size of the queue
     */
    public int size() {
        return this.size;
    }

    /**
     * @return whether the queue is empty or not
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * todo
     * Elements will be printed in order
     * May simply call T.toString() method
     *
     * @return the elements in the queue
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return "";
        }
        MyIterator<T> iter = this.queue.iterator();
        StringBuilder sb = new StringBuilder("");
        for (T t = iter.first(); !iter.isEnd4First(); t = iter.next()) {
            sb.append(t.toString()).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.toString().length());
        return sb.toString();
    }
}
