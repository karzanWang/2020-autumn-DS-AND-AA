package lab_three;

class HeapOverflowException extends Exception {
    public HeapOverflowException(String message) {
        super(message);
    }
}

class HeapEmptyException extends Exception {
    public HeapEmptyException(String message) {
        super(message);
    }
}

/**
 * You may use {@link java.util.Queue}.
 */
public class MyHeap {
    private static final int MAX_CAPACITY = 10_000_001;
    private int currentSize;//堆中的元素个数
    private int capacity;
    private int[] array;


    /**
     * todo: init
     *
     * @param capacity capacity
     */
    public MyHeap(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        array = new int[capacity+1];
    }

    /**
     * todo: capacity of the heap
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * todo: insert an item into the heap
     *
     * @param x item
     * @throws HeapOverflowException too many items
     */
    public void insert(int x) throws HeapOverflowException {
        if (capacity == currentSize){
            throw new HeapOverflowException("插入失败：堆已满");
        }
        int hole = ++currentSize;
        for (array[0] = x;x<array[hole/2];hole/=2)
            array[hole] = array[hole/2];
        array[hole] = x;
    }

    /**
     * todo: find min value
     *
     * @return min value
     * @throws HeapEmptyException heap may be empty
     */
    public int findMin() throws HeapEmptyException {
        if (isEmpty()) {
            throw new HeapEmptyException("无法找出最小值：堆为空");
        }
        return array[1];
    }

    /**
     * todo: find min value, and remove it
     *
     * @return deleted min value
     * @throws HeapEmptyException heap may be empty
     */
    public int deleteMin() throws HeapEmptyException {
        if (isEmpty()) {
            throw new HeapEmptyException("无法删除最小值：堆为空");
        }

        int minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    private void percolateDown(int hole) {
        int child;
        int tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1]<array[child]) {
                child++;
            }
            if (array[child]<tmp) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;

    }

    /**
     * todo: isEmpty
     *
     * @return whether the heap is empty
     */
    public boolean isEmpty() {
        return currentSize ==0;
    }


    /**
     * todo: remove all items in the heap
     */
    public void makeEmpty() {
        array  = new int[capacity+1];
        currentSize = 0;
    }


}
