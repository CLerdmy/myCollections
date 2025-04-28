package dev.clerdmy.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void createPair_valuesAreSetCorrectly() {
        Pair<String, Integer> pair = new Pair<>("hi", 5);
        assertEquals("hi", pair.getFirst());
        assertEquals(5, pair.getSecond());
    }

    @Test
    void setFirst_valueIsUpdated() {
        Pair<String, Integer> pair = new Pair<>("hi", 5);
        pair.setFirst("hey");
        assertEquals("hey", pair.getFirst());
    }

    @Test
    void setSecond_valueIsUpdated() {
        Pair<String, Integer> pair = new Pair<>("hi", 5);
        pair.setSecond(10);
        assertEquals(10, pair.getSecond());
    }

    @Test
    void toString_returnsCorrectFormat() {
        Pair<String, Integer> pair = new Pair<>("hi", 5);
        assertEquals("(hi, 5)", pair.toString());
    }

    @Test
    void equals_equalPairs_returnsTrue() {
        Pair<String, Integer> pair1 = new Pair<>("hi", 5);
        Pair<String, Integer> pair2 = new Pair<>("hi", 5);
        assertEquals(pair1, pair2);
    }

    @Test
    void equals_unequalPairs_returnsFalse() {
        Pair<String, Integer> pair1 = new Pair<>("hi", 5);
        Pair<String, Integer> pair2 = new Pair<>("hey", 10);
        assertNotEquals(pair1, pair2);
    }

    @Test
    void hashCode_equalPairs_haveSameHashCode() {
        Pair<String, Integer> pair1 = new Pair<>("hi", 5);
        Pair<String, Integer> pair2 = new Pair<>("hi", 5);
        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

}