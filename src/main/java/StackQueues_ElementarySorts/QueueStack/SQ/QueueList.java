package StackQueues_ElementarySorts.QueueStack.SQ;

public class QueueList<Item> {
    private Node first, last;

    private class Node {
        public Item item;
        public Node next;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;

        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
