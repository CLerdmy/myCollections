package dev.clerdmy.util;

import org.junit.jupiter.api.Test;

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

}