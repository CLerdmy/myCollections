package dev.clerdmy.util;

public class CollectionsUtil {

    public static <T> boolean areEqual(T a, T b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

}