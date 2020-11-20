package StackQueues_ElementarySorts.QueueStack.QueueWithTwoStacks;

public class QueueWithTwoStacks<Item> {

    StackList<Item> stackOrdered;
    StackList<Item> stackReverse;
    private int size = 0;

    public QueueWithTwoStacks() {
        stackOrdered = new StackList<>();
        stackReverse = new StackList<>();
    }

    public void enqueue(Item item) {
        if(!stackReverse.isEmpty()) fillUpOrderedStack();
        stackOrdered.push(item);
        size++;
    }

    public Item dequeue() {
        if(!stackOrdered.isEmpty()) fillUpReverseStack();
        size--;
        return stackReverse.pop();
    }

    private void fillUpOrderedStack() {
        for(int i = 0; i < stackReverse.size(); i++) {
            Item item = stackReverse.pop();
            stackOrdered.push(item);
        }
    }

    private void fillUpReverseStack() {
        int size = stackOrdered.size();
        for(int i = 0; i < size; i++) {
            Item item = stackOrdered.pop();
            stackReverse.push(item);
        }
    }

    public int size() {
        return size;
    }
}
