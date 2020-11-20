package StackQueues_ElementarySorts.QueueStack.Permutation;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    Item[] randomizedQueue;
    private int N = 0;
    private int size;
    Random randomDequeue;

    private class RandomizedIterator implements Iterator<Item> {
        int[] randomAccess;
        Random random;
        int sizeRandom;

        public RandomizedIterator(int N) {
            random = new Random();
            randomAccess = new int[N];
            int j = 0;
            for (int i = 0; i < N; i++) {
                if (randomizedQueue[i] != null) {
                    randomAccess[j++] = i;
                }
            }
            for (int i = j; i < randomAccess.length; i++) {
                randomAccess[i] = -1;
            }
            sizeRandom = j;
        }

        @Override
        public boolean hasNext() {
            return randomAccess.length != 0;
        }

        @Override
        public Item next() {
            if (randomAccess.length == 0) throw new NoSuchElementException();

            int randomValue = findRandom();
            return randomizedQueue[randomValue];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private int findRandom() {
            int r = random.nextInt(randomAccess.length);
            if (randomAccess[r] >= 0) {
                int res = randomAccess[r];
                randomAccess[r] = -1;
                sizeRandom--;
                if (sizeRandom <= randomAccess.length / 2) resizeRandom(randomAccess.length / 2);
                return res;
            }
            else return findRandom();
        }

        private void resizeRandom(int size) {
            int[] newArray =  new int[size];
            int j = 0;
            for (int i = 0; i < randomAccess.length; i++) {
                if (randomAccess[i] >= 0) {
                    newArray[j++] = randomAccess[i];
                }
            }
            randomAccess = newArray;
            for (int i = j; i < randomAccess.length; i++) {
                randomAccess[i] = -1;
            }
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        randomizedQueue = (Item[]) new Object[1];
        randomDequeue = new Random();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (N == randomizedQueue.length) {
            resize(randomizedQueue.length * 2);
        }
        randomizedQueue[N++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = findRandomIndex();
        Item item = randomizedQueue[index];
        randomizedQueue[index] = null;
        size--;
        if (size < randomizedQueue.length / 4) resize(randomizedQueue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = findRandomIndex();
        return randomizedQueue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator(N);
    }

    private void resize(int n) {
        Item[] newQueue = (Item[]) new Object[n];
        for (int i = 0, j = 0; i < N; i++) {
            if (randomizedQueue[i] != null) {
                newQueue[j++] = randomizedQueue[i];
            }
        }
        randomizedQueue = newQueue;
        N = size;
    }

    private int findRandomIndex() {
        int randomIndex = randomDequeue.nextInt(N);
        if (randomizedQueue[randomIndex] != null) return randomIndex;
        else return findRandomIndex();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.sample());
        System.out.println(queue.size());
        for (Integer val: queue) {
            System.out.println(val);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }

}