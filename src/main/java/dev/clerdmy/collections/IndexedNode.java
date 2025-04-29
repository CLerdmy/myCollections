package dev.clerdmy.collections;

import java.util.Objects;

public class IndexedNode<T> extends Node<T> {

    private int index;

    public IndexedNode(T data, Node<T> next, int index) {
        super(data, next);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        IndexedNode<?> that = (IndexedNode<?>) object;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), index);
    }

}