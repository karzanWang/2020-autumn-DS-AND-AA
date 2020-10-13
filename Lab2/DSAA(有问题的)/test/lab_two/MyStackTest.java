package lab_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    @Test
    public void top() throws MyStackEmptyException {
        MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(MyStackEmptyException.class, stack::top);
        assertThrows(MyStackEmptyException.class, stack::pop);

        stack.push(1);
        stack.push(3);
        assertFalse(stack.isEmpty());

        assertEquals(3, stack.top());

        assertEquals(2, stack.size());

        assertEquals(3, stack.pop());

        assertEquals(1, stack.size());

        assertEquals(1, stack.top());


    }

    @Test
    public void String() {
        MyStack<Integer> stack = new MyStack<>();
        String s = stack.toString();
        assertEquals("", s);
        stack.push(4);
        stack.push(8);
        stack.push(0);
        s = stack.toString();
        assertEquals("0, 8, 4", s);

    }

}