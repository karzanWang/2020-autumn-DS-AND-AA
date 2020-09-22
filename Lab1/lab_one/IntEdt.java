package lab_one;

class Item {
    private Item prev;
    private Item next;
    private int val;

    Item(int val) {
        this.val = val;
    }

    public Item getPrev() {
        return prev;
    }

    public void setPrev(Item prev) {
        this.prev = prev;
    }

    public Item getNext() {
        return next;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}

public class IntEdt {
    private Item intSeq;
    private Item cursor;

    public IntEdt(Item intSequence) {
        this.intSeq = intSequence;
        this.cursor = intSequence;
    }

    /**
     * insert x after the cursor.
     * For example, int sequence is 5 - 6 - 7,
     * the cursor is at 6.
     * Insert 8 and the sequence is 5 - 6 - 8 - 7.
     *
     * @param x val to be inserted
     */
    public void insert(int x) {

    }

    /**
     *Move the cursor left unless it’s at the first element
     */
    public void left() {

    }

    /**
     * Move the cursor right unless it’s at the last element
     */
    public void right() {

    }

    /**
     * Delete the element before the cursor
     */
    public void delete() {

    }

    /**
     * find the max S_i, where S_i = a_1 + ... + a_i, a_k is the cursor, 1 <= i <= k.
     * @return sum
     */
    public int query() {
        return 0;
    }

    /**
     * additional function
     * find the max S_i, where S_i = a_j + ... + a_i, a_k is the cursor, 1 <= j <= i <= k.
     * @return sum
     */
    public int additional() {
        return 0;
    }
}
