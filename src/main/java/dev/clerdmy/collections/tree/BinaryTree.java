package dev.clerdmy.collections.tree;

import dev.clerdmy.collections.BinaryNode;
import dev.clerdmy.collections.Set;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> implements Set<T> {

    private BinaryNode<T> root;
    private int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private BinaryNode<T> findNode(T item) {
        BinaryNode<T> current = root;
        while (current != null) {
            int comparisonResult = item.compareTo(current.getData());
            if (comparisonResult == 0) {
                return current;
            } else if (comparisonResult < 0) {
                current = current.getLeft();
            } else if (comparisonResult > 0) {
                current = current.getRight();
            }
        }
        return null;
    }

    private BinaryNode<T> findParent(T item) {
        BinaryNode<T> current = root;
        BinaryNode<T> parent = null;

        while (current != null) {
            int comparisonResult = item.compareTo(current.getData());

            if (comparisonResult == 0) {
                return null;
            }

            parent = current;

            if (comparisonResult < 0) {
                current = current.getLeft();
            } else if (comparisonResult > 0) {
                current = current.getRight();
            }
        }

        return parent;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        BinaryNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    @Override
    public boolean contains(T item) {
        if (root == null) return false;
        return findNode(item) == null;
    }

    @Override
    public boolean add(T item) {
        if (root == null) {
            root = new BinaryNode<>(item, null, null);
            size++;
            return true;
        }

        BinaryNode<T> parent = findParent(item);

        if (parent == null) {
            return false;
        }

        int comparisonResult = item.compareTo(parent.getData());
        BinaryNode<T> newNode = new BinaryNode<>(item, null, null);
        if (comparisonResult < 0) {
            parent.setLeft(newNode);
        } else if (comparisonResult > 0) {
            parent.setRight(newNode);
        }

        size++;
        return true;
    }

    private boolean removeHelper(T item) {
        BinaryNode<T> toRemove = findNode(item);
        if (toRemove == null) {
            return false;
        }
        BinaryNode<T> parent = findParent(item);

        if (toRemove.getLeft() == null && toRemove.getRight() == null) { // Leaf
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == toRemove) {
                parent.setLeft(null);
            } else if (parent.getRight() == toRemove) {
                parent.setRight(null);
            }
        } else if (toRemove.getLeft() == null) { // One right
            if (parent == null) {
                root = toRemove.getRight();
            } else if (parent.getLeft() == toRemove) {
                parent.setLeft(toRemove.getRight());
            } else {
                parent.setRight(toRemove.getRight());
            }
        } else if (toRemove.getRight() == null) { // One left
            if (parent == null) {
                root = toRemove.getLeft();
            } else if (parent.getLeft() == toRemove) {
                parent.setLeft(toRemove.getLeft());
            } else {
                parent.setRight(toRemove.getLeft());
            }
        } else { // Both
            BinaryNode<T> successor = findMin(toRemove.getRight());
            T successorData = successor.getData();
            removeHelper(successorData);
            toRemove.setData(successorData);
        }

        return true;
    }

    @Override
    public boolean remove(T item) {
        boolean isRemoved =  removeHelper(item);
        if (isRemoved) {
            size--;
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> stack = new Stack<>();
        private BinaryNode<T> current;

        public BinaryTreeIterator() {
            current = root;
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BinaryNode<T> node = stack.pop();
            T value = node.getData();

            if (node.getRight() != null) {
                current = node.getRight();
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }
            }

            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Set.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Set.super.spliterator();
    }

}
