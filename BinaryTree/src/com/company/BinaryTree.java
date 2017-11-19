package com.company;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T key = (T) o;
        int prevSize = size;
        if (root == null)
            return false;
        root = remove(root, key);
        if (prevSize == size)
            return false;
        return true;
    }

    private Node<T> min(Node<T> tree) {
        if (tree.left == null) {
            return tree;
        }
        return min(tree.left);
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<T> node) {
        int heightLeft = 0;
        int heightRight = 0;
        if (node == null)
            return 0;
        if (node.left != null) {
            heightLeft = getHeight(node.left);
        }
        if (node.right != null) {
            heightRight = getHeight(node.right);
        }
        return (Math.max(heightLeft, heightRight) + 1);
    }

    private Node<T> remove(Node<T> start, T value) {
        if (start == null)
            return null;
        int comparison = value.compareTo(start.value);
        if (comparison < 0) {
            start.left = remove(start.left, value);
        }
        else if (comparison > 0) {
            start.right = remove(start.right, value);
        } else if (start.left != null && start.right != null) {
            Node<T> node = start;
            start = min(start.right);
            remove(node, start.value);
            start.left = node.left;
            start.right = node.right;
        } else {
            if (start.left != null) {
                --size;
                return start.left;
            } else {
                --size;
                return start.right;
            }
        }
        return start;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null; //TODO=null????

        private BinaryTreeIterator() {
            current = new Node<>(null);
            current.right = root;
        }

        private Node<T> findNext() {
            if (current.right != null) {
                Node<T> next = current.right;
                while (next.left != null) {
                    next = next.left;
                }
                return next;
            }
            Node<T> next = null;
            Node<T> t = root;
            while (t != null) {
                int comparison = current.value.compareTo(t.value);
                if (comparison == 0) {
                    break;
                }
                else if (comparison < 0) {
                    next = t;
                    t = t.left;
                }
                else {
                    t = t.right;
                }
            }
            return next;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
