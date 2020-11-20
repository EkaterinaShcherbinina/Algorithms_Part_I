package StackQueues_ElementarySorts.QueueStack.SQ;

public class QueueArray<Item> {
    private Item[] queue;
    private int first, last = 0;

    public QueueArray() {
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return last - first == 0;
    }

    public void enqueue(Item item) {
        queue[last++] = item;
        if (queue.length == last) resize(queue.length * 2);
    }

    public void resize(int size) {
        Item[] newArray = (Item[]) new Object[size];
        for(int i = 0; i < (last - first); i++) {
            newArray[i] = queue[i + first];
        }
        queue = newArray;
        last = last - first;
        first = 0;
    }

    public Item dequeue() {
        Item item = queue[first];
        queue[first++] = null;

        if (queue.length / 4 == last - first) resize(queue.length / 2);
        return item;
    }
}
