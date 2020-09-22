package lab_one;

import java.util.Objects;

/**
 * @author 19302010068 Xinyun Wan
 */
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

    /**
     * This is a doubly linked list
     * Therefore, prev.next should be linked to this
     * @param prev an item
     */
    public void setPrev(Item prev) {
        this.prev = prev;
        prev.next = this;
    }

    public Item getNext() {
        return next;
    }

    /**
     * This is a doubly linked list
     * Therefore, next.prev should be linked to this
     * @param next an item
     */
    public void setNext(Item next) {
        this.next = next;
        next.prev = this;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        for (Item i = item, j = this; i != null && j != null; i = i.next, j = j.next) {
            if (i.val != j.val) {
                return false;
            }
        }
        for (Item i = item, j = this; i != null && j != null; i = i.prev, j = j.prev) {
            if (i.val != j.val) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prev, next, val);
    }
}

public class IntEdt {
    private Item intSeq;
    private Item cursor;

    public Item getIntSeq() {
        return intSeq;
    }

    public Item getCursor() {
        return cursor;
    }
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        IntEdt intEdt = new IntEdt(null);
//        while (true) {
//            intEdt.print();
//            String line = input.nextLine().toUpperCase();
//            switch (line) {
//                case "EXIT":
//                    return;
//                case "L":
//                    intEdt.left();
//                    break;
//                case "R":
//                    intEdt.right();
//                    break;
//                case "D":
//                    intEdt.delete();
//                    break;
//                case "Q":
//                    System.out.println(intEdt.query());
//                    break;
//                case "A":
//                    System.out.println(intEdt.additional());
//                    break;
//                default:
//                    if (line.startsWith("I ")) {
//                        try {
//                            intEdt.insert(Integer.parseInt(line.substring(1).trim()));
//                        } catch (NumberFormatException ex) {
//                            System.out.println("Invalid input");
//                        }
//                    } else
//                        System.out.println("Invalid input");
//            }
//        }
//    }

    public IntEdt(Item intSequence) {
        this.intSeq = intSequence;
        this.cursor = intSequence;
    }

    private static void link(Item a, Item b) {
        a.setNext(b);
        b.setPrev(a);
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
        Item item = new Item(x);
        if (intSeq == null)
            intSeq = cursor = item;
        else if (cursor == null) {
            link(item, intSeq);
            intSeq = cursor = item;
        } else {
            Item prev = cursor;
            Item next = cursor.getNext();
            link(prev, item);
            if (next != null)
                link(item, next);
            cursor = item;
        }
    }

    /**
     * Move the cursor left unless it’s at the first element
     */
    public void left() {
        if (cursor != null)
            cursor = cursor.getPrev();
    }

    /**
     * Move the cursor right unless it’s at the last element
     */
    public void right() {
        if (cursor == null)
            cursor = intSeq;
        else if (cursor.getNext() != null)
            cursor = cursor.getNext();
    }

    /**
     * Delete the element before the cursor
     */
    public void delete() {
        if (cursor == null)
            return;

        Item prev = cursor.getPrev();
        Item next = cursor.getNext();
        cursor = prev;
        if (prev == null) {
            intSeq = next;
            if (next != null)
                next.setPrev(null);
        } else if (next == null)
            prev.setNext(null);
        else
            link(prev, next);
    }

    /**
     * find the max S_i, where S_i = a_1 + ... + a_i, a_k is the cursor, 1 <= i <= k.
     *
     * @return sum
     */
    public int query() {
        int s = 0;
        int sMax = 0;
        Item cur = intSeq;
        while (cur != null) {
            s += cur.getVal();
            if (s > sMax)
                sMax = s;
            if (cur == cursor)
                break;
            cur = cur.getNext();
        }
        return sMax;
    }

    /**
     * additional function
     * find the max S_i, where S_i = a_j + ... + a_i, a_k is the cursor, 1 <= j <= i <= k.
     *
     * @return sum
     */
    public int additional() {
        int s = 0;
        int sMax = 0;
        Item cur = intSeq;
        while (cur != null) {
            s += cur.getVal();
            if (s > sMax)
                sMax = s;
            else if (s < 0)
                s = 0;
            if (cur == cursor)
                break;
            cur = cur.getNext();
        }
        return sMax;
    }

    public void print() {
        StringBuilder s = new StringBuilder();
        int c = 0;

        if (intSeq != null) {
            s.append(intSeq.getVal());
            if (cursor == intSeq)
                c = s.length();
            Item cur = intSeq.getNext();
            while (cur != null) {
                if (cur.getVal() >= 0)
                    s.append('+');
                s.append(cur.getVal());
                if (cursor == cur)
                    c = s.length();
                cur = cur.getNext();
            }
        }

        System.out.println(s);
        System.out.println(" ".repeat(c) + "^");
        System.out.print("> ");
    }
}