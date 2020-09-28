package lab_two;

import jdk.jfr.events.ThrowablesEvent;

class MyQueueEmptyException extends Exception {
    public MyQueueEmptyException(String message) {
        super(message);
    }
}

public class MyQueue<T> {
    private final MyLinkedList<T> queue;

    public MyQueue() {
        queue = new MyLinkedList<>();
    }

    /**
     * todo
     * add a value to the end of the queue
     *
     * @param val a value
     */
    public void enqueue(T val) {
        queue.addLast(val);
    }

    /**
     * todo
     * get the value at the top (beginning) of the queue, and remove it
     *
     * @return the value
     */
    public T dequeue() throws MyQueueEmptyException {
        try{
            return queue.rmFirst();
        }catch(MyLinkedListEmptyException e){
            throw new MyQueueEmptyException("queue List has no elements");
        }
    }

    /**
     * todo
     * get the value at the top (beginning) of the queue, but not remove it
     *
     * @return the value
     */
    public T top() throws MyQueueEmptyException {
        if(queue.iterator().first() == null){
            throw new MyQueueEmptyException("queue List has no elements");
        }else{
            return queue.iterator().first();
        }
    
    }

    /**
     * todo
     *
     * @return size of the queue
     */
    public int size() {
        return queue.getSize();
    }

    /**
     * todo
     *
     * @return whether the queue is empty or not
     */
    public boolean isEmpty() {
        return queue.getSize() == 0;
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
        MyIterator<T> iterator = queue.iterator();
        String result = "";
        while(iterator.returnPtr() != null){
            result += iterator.getPtr()+", ";
            iterator.next();
        }
        if(!result.equals("")) {
            result = result.substring(0, result.length() - 2);
        }

        return result;
    }
}
