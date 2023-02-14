package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<T>(value, null);
        } else {
            Node<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
        size++;
        modCount++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> deletedHead = head;
        T deletedValue = deletedHead.item;
        head = deletedHead.next;
        deletedHead.item = null;
        deletedHead.next = null;
        size--;
        modCount++;
        return deletedValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cacheModCount = modCount;
            ForwardLinked.Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                if (cacheModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T val = currentNode.item;
                currentNode = currentNode.next;
                return val;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
