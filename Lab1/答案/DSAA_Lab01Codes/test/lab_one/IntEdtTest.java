package lab_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntEdtTest {

    @Test
    void insert() {
        IntEdt intEdt = new IntEdt(null);
        intEdt.insert(7);
        intEdt.insert(6);
        intEdt.insert(5);
        Item intSeq = intEdt.getIntSeq();

        Item expected = new Item(7);
        Item item5 = new Item(5);
        Item item6 = new Item(6);
        item6.setNext(item5);
        expected.setNext(item6);
        assertEquals(expected, intSeq);
    }

    @Test
    void delete() {
        IntEdt intEdt = new IntEdt(null);
        intEdt.insert(7);
        intEdt.insert(6);
        intEdt.insert(5);
        intEdt.insert(4);
        intEdt.delete();
        Item intSeq = intEdt.getIntSeq();

        Item expected = new Item(7);
        Item item5 = new Item(5);
        Item item6 = new Item(6);
        item6.setNext(item5);
        expected.setNext(item6);
        assertEquals(expected, intSeq);
    }

    @Test
    void deleteEmpty() {
        IntEdt intEdt = new IntEdt(null);
        assertNull(intEdt.getIntSeq());
        /*
         * may throw an Exception if possible
         * to provide more information, or exit
         */
        intEdt.delete();
        assertNull(intEdt.getIntSeq());
    }

    @Test
    void query() {
        IntEdt intEdt = new IntEdt(null);
        intEdt.insert(1);
        intEdt.insert(-2);
        intEdt.insert(3);
        intEdt.insert(-4);

        Item minus4 = new Item(-4);
        Item plus3 = new Item(3);
        plus3.setNext(minus4);
        Item minus2 = new Item(-2);
        minus2.setNext(plus3);
        Item plus1 = new Item(1);
        plus1.setNext(minus2);

        int result = intEdt.query();
        assertEquals(2, result);
        assertEquals(minus4, intEdt.getCursor());
    }

    @Test
    void additional() {
        IntEdt intEdt = new IntEdt(null);
        intEdt.insert(1);
        intEdt.insert(-2);
        intEdt.insert(3);
        intEdt.insert(-4);

        int result = intEdt.additional();
        assertEquals(3, result);
    }
}