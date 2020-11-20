package StackQueues_ElementarySorts.QueueStack.SQ;

import java.util.Iterator;

public class StackArray<Item> implements Iterable<Item>{
    private int N = 0;
    private Item[] stack;

    public StackArray() {
        stack = (Item[]) new Object[1];
    }

    public Iterator<Item> iterator() {
        return new StackArrayIterator();
    }

    private class StackArrayIterator implements Iterator<Item> {

        int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return stack[--i];
        }
    }

    public void push(Item item) {
        if(N == stack.length) {
            resize(2 * stack.length);
        }
        stack[N++] = item;
    }

    private void resize(int size) {
        Item[] newStack = (Item[]) new Object[size];
        for(int i = 0; i < N; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    public Item pop() {
        Item item = stack[--N];
        stack[N] = null;
        if(N > 0 && N == stack.length / 4) resize(stack.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }
}
