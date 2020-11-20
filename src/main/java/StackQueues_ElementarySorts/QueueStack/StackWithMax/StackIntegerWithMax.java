package StackQueues_ElementarySorts.QueueStack.StackWithMax;

public class StackIntegerWithMax {
    private StackArray<Integer> stack;
    private int maxOperation = 0;

    public StackIntegerWithMax() {
        stack = new StackArray<>();
    }

    public void push(Integer item) {
        stack.push(item);
        maxOperation = item > maxOperation ? item : maxOperation;
    }

    public Integer pop() {
        Integer num = stack.pop();
        if(num == maxOperation) {
            maxOperation = findMax();
        }
        return num;
    }

    public Integer maxOperation() {
        return maxOperation;
    }

    private int findMax() {
        int max = 0;
        for(int num: stack) {
            max = num > max ? num : max;
        }
        return max;
    }
}
