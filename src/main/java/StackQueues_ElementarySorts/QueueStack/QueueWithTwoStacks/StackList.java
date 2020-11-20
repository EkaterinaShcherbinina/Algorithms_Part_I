package StackQueues_ElementarySorts.QueueStack.QueueWithTwoStacks;

import java.util.Iterator;

public class StackList<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private int size;

    public Iterator<Item> iterator() {
        return new StackListIterator();
    }

    private class StackListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            boolean isNext = current != null;
            return isNext;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
        size++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public int size() {
        return size;
    }
}

