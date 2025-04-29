package dev.clerdmy.collections.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedArrayTest {

    @Test
    void createSinglyLinkedArray_sizeIsSetCorrectly() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>();
        assertEquals(0, singlyLinkedArray.size());
        assertTrue(singlyLinkedArray.isEmpty());
    }

    @Test
    void createWithArraySinglyLinkedArray_sizeIsSetCorrectly() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>(new Integer[]{1, 5, 10, 15});
        assertEquals(4, singlyLinkedArray.size());
        assertEquals(5, singlyLinkedArray.get(Integer.valueOf(5)));
        assertFalse(singlyLinkedArray.isEmpty());
    }

    @Test
    void add_valueIsAdded() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>();
        singlyLinkedArray.add(5);
        assertEquals(1, singlyLinkedArray.size());
        assertEquals(5, singlyLinkedArray.get(singlyLinkedArray.size() - 1));
    }

    @Test
    void set_valueIsSet() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>();
        singlyLinkedArray.add(10);
        singlyLinkedArray.set(0, 5);
        assertEquals(1, singlyLinkedArray.size());
        assertEquals(5, singlyLinkedArray.get(0));
    }

    @Test
    void insert_valueIsInserted() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>();
        singlyLinkedArray.add(10);
        singlyLinkedArray.add(5);
        singlyLinkedArray.add(3);
        singlyLinkedArray.insert(1, 1);
        assertEquals(4, singlyLinkedArray.size());
        assertEquals(1, singlyLinkedArray.get(1));
    }

    @Test
    void remove_valueIsRemoved() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>();
        singlyLinkedArray.add(10);
        singlyLinkedArray.add(5);
        singlyLinkedArray.add(3);
        singlyLinkedArray.remove(1);
        assertEquals(2, singlyLinkedArray.size());
        assertEquals(3, singlyLinkedArray.get(1));
    }

    @Test
    void pop_valueIsPopped() {
        SinglyLinkedArray<Integer> singlyLinkedArray = new SinglyLinkedArray<>();
        singlyLinkedArray.add(10);
        singlyLinkedArray.add(5);
        singlyLinkedArray.add(3);
        singlyLinkedArray.pop();
        assertEquals(2, singlyLinkedArray.size());
        assertEquals(5, singlyLinkedArray.get(singlyLinkedArray.size() - 1));
    }

}