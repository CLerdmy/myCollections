package dev.clerdmy.util;

import dev.clerdmy.collections.Array;

import java.util.Comparator;

public class CollectionsUtil {

    public static <T> boolean areEqual(T a, T b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    public static <T> int binarySearch(T key, Array<T> array, Comparator<T> comparator) {
        return binarySearchIterative(key, array, comparator);
    }

    public static <T> int binarySearch(T key, Array<T> array, Comparator<T> comparator, boolean isRecursive) {
        if (isRecursive) return binarySearchRecursion(key, array, comparator);
        return binarySearchIterative(key, array, comparator);
    }

    private static <T> int binarySearchRecursion(T key, Array<T> array, Comparator<T> comparator) {
        return binarySearchRecursionHelper(comparator, key, array, 0, array.size() - 1);
    }

    private static <T> int binarySearchRecursionHelper(Comparator<T> comparator, T key, Array<T> array, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        int comparisonResult = comparator.compare(array.get(mid), key);
        if (comparisonResult == 0) return mid;
        if (comparisonResult < 0) return binarySearchRecursionHelper(comparator, key, array, mid + 1, high);
        return binarySearchRecursionHelper(comparator, key, array, low, mid - 1);
    }

    private static <T> int binarySearchIterative(T key, Array<T> array, Comparator<T> comparator) {
        int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = comparator.compare(array.get(mid), key);
            if (comparisonResult == 0) {
                return mid;
            } else if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static <T> int lowerBound(T key, Array<T> array, Comparator<T> comparator) {
        return lowerBoundIterative(key, array, comparator);
    }

    public static <T> int lowerBound(T key, Array<T> array, Comparator<T> comparator, boolean isRecursive) {
        if (isRecursive) return lowerBoundRecursion(key, array, comparator);
        return lowerBoundIterative(key, array, comparator);
    }

    private static <T> int lowerBoundRecursion(T key, Array<T> array, Comparator<T> comparator) {
        return lowerBoundRecursionHelper(comparator, key, array, 0, array.size() - 1);
    }

    private static <T> int lowerBoundRecursionHelper(Comparator<T> comparator, T key, Array<T> array, int low, int high) {
        if (low > high) return Integer.MAX_VALUE;
        int mid = low + (high - low) / 2;
        int comparisonResult = comparator.compare(array.get(mid), key);
        if (comparisonResult < 0) return lowerBoundRecursionHelper(comparator, key, array, mid + 1, high);
        return Math.min(mid, lowerBoundRecursionHelper(comparator, key, array, low, mid - 1));
    }

    private static <T> int lowerBoundIterative(T key, Array<T> array, Comparator<T> comparator) {
        int low = 0;
        int high = array.size() - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = comparator.compare(array.get(mid), key);
            if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }
        return result;
    }

    public static <T> int upperBound(T key, Array<T> array, Comparator<T> comparator) {
        return upperBoundIterative(key, array, comparator);
    }

    public static <T> int upperBound(T key, Array<T> array, Comparator<T> comparator, boolean isRecursive) {
        if (isRecursive) return upperBoundRecursion(key, array, comparator);
        return upperBoundIterative(key, array, comparator);
    }

    private static <T> int upperBoundRecursion(T key, Array<T> array, Comparator<T> comparator) {
        return upperBoundRecursionHelper(comparator, key, array, 0, array.size() - 1);
    }

    private static <T> int upperBoundRecursionHelper(Comparator<T> comparator, T key, Array<T> array, int low, int high) {
        if (low > high) return Integer.MAX_VALUE;
        int mid = low + (high - low) / 2;
        int comparisonResult = comparator.compare(array.get(mid), key);
        if (comparisonResult <= 0) return upperBoundRecursionHelper(comparator, key, array, mid + 1, high);
        return Math.min(mid, upperBoundRecursionHelper(comparator, key, array, low, mid - 1));
    }

    private static <T> int upperBoundIterative(T key, Array<T> array, Comparator<T> comparator) {
        int low = 0;
        int high = array.size() - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = comparator.compare(array.get(mid), key);
            if (comparisonResult <= 0) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }
        return result;
    }

    // With Comparable

    public static <T extends Comparable<T>> int binarySearch(T key, Array<T> array) {
        return binarySearchIterative(key, array);
    }

    public static <T extends Comparable<T>> int binarySearch(T key, Array<T> array, boolean isRecursive) {
        if (isRecursive) return binarySearchRecursion(key, array);
        return binarySearchIterative(key, array);
    }

    private static <T extends Comparable<T>> int binarySearchRecursion(T key, Array<T> array) {
        return binarySearchRecursionHelper(key, array, 0, array.size() - 1);
    }

    private static <T extends Comparable<T>> int binarySearchRecursionHelper(T key, Array<T> array, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        int comparisonResult = key.compareTo(array.get(mid));
        if (comparisonResult == 0) return mid;
        if (comparisonResult < 0) return binarySearchRecursionHelper(key, array, mid + 1, high);
        return binarySearchRecursionHelper(key, array, low, mid - 1);
    }

    private static <T extends Comparable<T>> int binarySearchIterative(T key, Array<T> array) {
        int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = key.compareTo(array.get(mid));
            if (comparisonResult == 0) {
                return mid;
            } else if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int lowerBound(T key, Array<T> array) {
        return lowerBoundIterative(key, array);
    }

    public static <T extends Comparable<T>> int lowerBound(T key, Array<T> array, boolean isRecursive) {
        if (isRecursive) return lowerBoundRecursion(key, array);
        return lowerBoundIterative(key, array);
    }

    private static <T extends Comparable<T>> int lowerBoundRecursion(T key, Array<T> array) {
        return lowerBoundRecursionHelper(key, array, 0, array.size() - 1);
    }

    private static <T extends Comparable<T>> int lowerBoundRecursionHelper(T key, Array<T> array, int low, int high) {
        if (low > high) return Integer.MAX_VALUE;
        int mid = low + (high - low) / 2;
        int comparisonResult = key.compareTo(array.get(mid));
        if (comparisonResult < 0) return lowerBoundRecursionHelper(key, array, mid + 1, high);
        return Math.min(mid, lowerBoundRecursionHelper(key, array, low, mid - 1));
    }

    private static <T extends Comparable<T>> int lowerBoundIterative(T key, Array<T> array) {
        int low = 0;
        int high = array.size() - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = key.compareTo(array.get(mid));
            if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }
        return result;
    }

    public static <T extends Comparable<T>> int upperBound(T key, Array<T> array) {
        return upperBoundIterative(key, array);
    }

    public static <T extends Comparable<T>> int upperBound(T key, Array<T> array, boolean isRecursive) {
        if (isRecursive) return upperBoundRecursion(key, array);
        return upperBoundIterative(key, array);
    }

    private static <T extends Comparable<T>> int upperBoundRecursion(T key, Array<T> array) {
        return upperBoundRecursionHelper(key, array, 0, array.size() - 1);
    }

    private static <T extends Comparable<T>> int upperBoundRecursionHelper(T key, Array<T> array, int low, int high) {
        if (low > high) return Integer.MAX_VALUE;
        int mid = low + (high - low) / 2;
        int comparisonResult = key.compareTo(array.get(mid));
        if (comparisonResult <= 0) return upperBoundRecursionHelper(key, array, mid + 1, high);
        return Math.min(mid, upperBoundRecursionHelper(key, array, low, mid - 1));
    }

    private static <T extends Comparable<T>> int upperBoundIterative(T key, Array<T> array) {
        int low = 0;
        int high = array.size() - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = key.compareTo(array.get(mid));
            if (comparisonResult <= 0) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }
        return result;
    }


    //sorting

}