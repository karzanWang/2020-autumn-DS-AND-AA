package lab_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    void top() throws MyQueueEmptyException {
        MyQueue<Integer> queue = new MyQueue<>();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertThrows(MyQueueEmptyException.class, queue::top);
        assertThrows(MyQueueEmptyException.class, queue::dequeue);

        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        assertEquals(3, queue.size());

        assertEquals(2, queue.top());
        assertEquals(2, queue.dequeue());

        assertEquals(4, queue.top());
        assertEquals(4, queue.dequeue());

        assertEquals(1, queue.top());
        assertEquals(1, queue.dequeue());

        assertThrows(MyQueueEmptyException.class, queue::top);
        assertThrows(MyQueueEmptyException.class, queue::dequeue);
        assertTrue(queue.isEmpty());

    }

    @Test
    public void String() {
        MyQueue<Integer> queue = new MyQueue<>();
        String s = queue.toString();
        assertEquals("", s);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(9);
        s = queue.toString();
        assertEquals("1, 4, 9", s);
    }
}