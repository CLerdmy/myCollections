package dev.clerdmy.util;

import dev.clerdmy.collections.Array;
import dev.clerdmy.collections.arrays.DynamicArray;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsUtilTest {

    @Test
    void areEqual_equalObjects_returnsTrue() {
        assertTrue(CollectionsUtil.areEqual("hi", "hi"));
        assertTrue(CollectionsUtil.areEqual(Integer.valueOf(5), Integer.valueOf(5)));
    }

    @Test
    void areEqual_unequalObjects_returnsFalse() {
        assertFalse(CollectionsUtil.areEqual("hi", "hey"));
        assertFalse(CollectionsUtil.areEqual(Integer.valueOf(5), Integer.valueOf(10)));
    }

    @Test
    void areEqual_oneObjectIsNull_returnsFalse() {
        assertFalse(CollectionsUtil.areEqual("hi", null));
        assertFalse(CollectionsUtil.areEqual(null, "hi"));
    }

    @Test
    void areEqual_bothObjectsAreNull_returnsTrue() {
        assertTrue(CollectionsUtil.areEqual(null, null));
    }

    @Test
    void areEqual_differentTypes_returnsFalse() {
        assertFalse(CollectionsUtil.areEqual("5", 5));
    }

    @Test
    void binarySearchRecursion() {
        Array<Integer> array = new DynamicArray<>(new Integer[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91});
        Comparator<Integer> comparator = Integer::compare;
        int key = 23;
        assertEquals(5, CollectionsUtil.binarySearchRecursion(key, array, comparator));
        key = 72;
        assertEquals(8, CollectionsUtil.binarySearchRecursion(key, array, comparator));
        key = 3;
        assertEquals(-1, CollectionsUtil.binarySearchRecursion(key, array, comparator));
        key = 100;
        assertEquals(-1, CollectionsUtil.binarySearchRecursion(key, array, comparator));
    }

    @Test
    void binarySearch() {
        Array<Integer> array = new DynamicArray<>(new Integer[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91});
        Comparator<Integer> comparator = Integer::compare;
        int key = 23;
        assertEquals(5, CollectionsUtil.binarySearch(key, array, comparator));
        key = 72;
        assertEquals(8, CollectionsUtil.binarySearch(key, array, comparator));
        key = 3;
        assertEquals(-1, CollectionsUtil.binarySearch(key, array, comparator));
        key = 100;
        assertEquals(-1, CollectionsUtil.binarySearch(key, array, comparator));
    }

    @Test
    void lowerBoundRecursion() {
        Array<Integer> array = new DynamicArray<>(new Integer[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91});
        Comparator<Integer> comparator = Integer::compare;
        int key = 23;
        assertEquals(5, CollectionsUtil.lowerBoundRecursion(key, array, comparator));
        key = 72;
        assertEquals(8, CollectionsUtil.lowerBoundRecursion(key, array, comparator));
        key = 3;
        assertEquals(1, CollectionsUtil.lowerBoundRecursion(key, array, comparator));
        key = 100;
        assertEquals(Integer.MAX_VALUE, CollectionsUtil.lowerBoundRecursion(key, array, comparator));
    }

    @Test
    void lowerBound() {
        Array<Integer> array = new DynamicArray<>(new Integer[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91});
        Comparator<Integer> comparator = Integer::compare;
        int key = 23;
        assertEquals(5, CollectionsUtil.lowerBound(key, array, comparator));
        key = 72;
        assertEquals(8, CollectionsUtil.lowerBound(key, array, comparator));
        key = 3;
        assertEquals(1, CollectionsUtil.lowerBound(key, array, comparator));
        key = 100;
        assertEquals(-1, CollectionsUtil.lowerBound(key, array, comparator));
    }

    @Test
    void upperBoundRecursion() {
        Array<Integer> array = new DynamicArray<>(new Integer[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91});
        Comparator<Integer> comparator = Integer::compare;
        int key = 23;
        assertEquals(6, CollectionsUtil.upperBoundRecursion(key, array, comparator));
        key = 72;
        assertEquals(9, CollectionsUtil.upperBoundRecursion(key, array, comparator));
        key = 3;
        assertEquals(1, CollectionsUtil.upperBoundRecursion(key, array, comparator));
        key = 100;
        assertEquals(Integer.MAX_VALUE, CollectionsUtil.upperBoundRecursion(key, array, comparator));
    }

    @Test
    void upperBound() {
        Array<Integer> array = new DynamicArray<>(new Integer[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91});
        Comparator<Integer> comparator = Integer::compare;
        int key = 23;
        assertEquals(6, CollectionsUtil.upperBound(key, array, comparator));
        key = 72;
        assertEquals(9, CollectionsUtil.upperBound(key, array, comparator));
        key = 3;
        assertEquals(1, CollectionsUtil.upperBound(key, array, comparator));
        key = 100;
        assertEquals(-1, CollectionsUtil.upperBound(key, array, comparator));
    }

}