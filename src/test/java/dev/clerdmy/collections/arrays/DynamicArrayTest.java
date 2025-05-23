package dev.clerdmy.collections.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void createDynamicArray_sizeAndCapacityAreSetCorrectly() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        assertEquals(0, dynamicArray.size());
        assertEquals(10, dynamicArray.capacity());
        assertTrue(dynamicArray.isEmpty());
    }

    @Test
    void createWithArrayDynamicArray_sizeAndCapacityAreSetCorrectly() {
        DynamicArray<Integer> dynamicArray1 = new DynamicArray<>(new Integer[]{1, 5, 10, 15});
        assertEquals(4, dynamicArray1.size());
        assertEquals(10, dynamicArray1.capacity());
        assertEquals(5, dynamicArray1.get(1));
        DynamicArray<Integer> dynamicArray2 = new DynamicArray<>(new Integer[]{1, 5, 10, 15, 1, 1, 1, 1, 1, 1, 1});
        assertEquals(11, dynamicArray2.size());
        assertEquals(20, dynamicArray2.capacity());
        assertEquals(5, dynamicArray2.get(1));
    }

    @Test
    void add_valueIsAdded() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(5);
        assertEquals(1, dynamicArray.size());
        assertEquals(5, dynamicArray.get(dynamicArray.size() - 1));
    }

    @Test
    void set_valueIsSet() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(10);
        dynamicArray.set(0, 5);
        assertEquals(1, dynamicArray.size());
        assertEquals(5, dynamicArray.get(0));
    }

    @Test
    void insert_valueIsInserted() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(10);
        dynamicArray.add(5);
        dynamicArray.add(3);
        dynamicArray.insert(1, 1);
        assertEquals(4, dynamicArray.size());
        assertEquals(1, dynamicArray.get(1));
    }

    @Test
    void remove_valueIsRemoved() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(10);
        dynamicArray.add(5);
        dynamicArray.add(3);
        dynamicArray.remove(1);
        assertEquals(2, dynamicArray.size());
        assertEquals(3, dynamicArray.get(1));
    }

    @Test
    void pop_valueIsPopped() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(10);
        dynamicArray.add(5);
        dynamicArray.add(3);
        dynamicArray.pop();
        assertEquals(2, dynamicArray.size());
        assertEquals(5, dynamicArray.get(dynamicArray.size() - 1));
    }

}