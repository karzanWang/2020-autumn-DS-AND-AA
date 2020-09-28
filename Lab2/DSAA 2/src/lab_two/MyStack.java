package lab_two;

class MyStackEmptyException extends Exception {
    public MyStackEmptyException(String message) {
        super(message);
    }
}

public class MyStack<T> {

    private final MyLinkedList<T> stack;

    public MyStack() {
        this.stack = new MyLinkedList<>();
    }

    /**
     * todo
     * add a value to the top of the stack
     * @param val value to be added into the stack
     */
    public void push(T val) {
        stack.addFirst(val);
    }

    /**
     * todo
     * get the value at the top of the stack, and remove it
     * @return the last value added into the stack
     */
    public T pop() throws MyStackEmptyException {
        try{
            return stack.rmFirst();
        }catch(MyLinkedListEmptyException e){
            throw new MyStackEmptyException("stack List has no elements");
        }
    }

    /**
     * todo
     * get the value at the top of the stack, but not remove it
     *
     * @return the last value added into the stack
     */
    public T top() throws MyStackEmptyException {
        MyIterator<T> myIterator = stack.iterator();
        if(myIterator.first() == null){
            throw new MyStackEmptyException("stack List has no elements");
        }else{
            return myIterator.first();
        }
    }

    /**
     * todo
     *
     * @return whether the stack contains any elements or not
     */
    public boolean isEmpty() {
        return stack.getSize() == 0;
    }

    /**
     * todo
     * size of the stack
     *
     * @return size
     */
    public int size() {
        return stack.getSize();
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
        String result = "";
        int index = 0;
        while(index < stack.getSize()){
            try {
                result += stack.at(index)+", ";
            } catch (MyLinkedListIndexException e) {
                e.printStackTrace();
            }
           index++;
        }
        if(!result.equals("")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }
}
