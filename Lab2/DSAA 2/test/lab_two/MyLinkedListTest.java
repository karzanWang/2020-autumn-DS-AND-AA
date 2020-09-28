package lab_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Use JDK 11
 */
class MyLinkedListTest {

    @Test
    void atException() {
        MyLinkedList<Integer> mll = new MyLinkedList<>();
        assertThrows(MyLinkedListIndexException.class, () -> mll.at(1));
    }

    @Test
    void at() throws MyLinkedListEmptyException {

        MyLinkedList<Integer> mll = new MyLinkedList<>();
        mll.addLast(3);
        mll.addLast(8);
        try {
            assertEquals(8, mll.at(1));
            assertEquals(3, mll.at(0));
        } catch (MyLinkedListIndexException e) {
            e.printStackTrace();
        }

        mll.rmLast();
        try {
            assertEquals(3, mll.at(0));
        } catch (MyLinkedListIndexException e) {
            e.printStackTrace();
        }
        assertThrows(MyLinkedListIndexException.class, () -> mll.at(1));
        mll.rmLast();
        assertThrows(MyLinkedListEmptyException.class, mll::rmLast);

    }

    @Test
    public void iterator() {
        MyLinkedList<Integer> mll = new MyLinkedList<>();
        mll.addLast(2);
        mll.addLast(7);
        mll.addLast(9);
        mll.addLast(3);
        MyIterator<Integer> iterator = mll.iterator();
        assertEquals(2, iterator.first());
        assertEquals(3, iterator.last());
        assertEquals(7, iterator.next());
        assertEquals(9, iterator.next());
        assertEquals(3, iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void isEnd4() {
        MyLinkedList<Double> mll = new MyLinkedList<>();
        MyIterator<Double> iter1 = mll.iterator();
        // zero element
        assertTrue(iter1.isEnd4First());
        assertTrue(iter1.isEnd4Last());
        // two elements
        mll.addLast(1.0);
        mll.addLast(2.0);
        MyIterator<Double> iterator = mll.iterator();
        int cnt = 0;
        for (Double d = iterator.first(); !iterator.isEnd4First(); d = iterator.next()) {
            cnt += 1;
            assertEquals(cnt, d);
        }
        assertEquals(2, cnt);
        // do not have to set the pointer to the first element
        assertTrue(iterator.isEnd4First());

        // MyIterator<Double> reverse = mll.iterator();
        // cnt = 0;
        // for (Double d = reverse.last(); !reverse.isEnd4Last(); d = reverse.prev()) {
        //     assertEquals(2.0 - cnt, d);
        //     cnt += 1;
        // }
        // assertEquals(2, cnt);
        // // do not have to set the pointer to the last element
        // assertTrue(reverse.isEnd4Last());
    }

    @Test
    public void first() throws MyLinkedListIndexException, MyLinkedListEmptyException {
        MyLinkedList<Integer> mll = new MyLinkedList<>();
        mll.addFirst(1);
        mll.addFirst(3);
        assertEquals(1, mll.at(1));
        mll.rmFirst();
        assertThrows(MyLinkedListIndexException.class, () -> mll.at(1));
        assertEquals(1, mll.at(0));
    }

}