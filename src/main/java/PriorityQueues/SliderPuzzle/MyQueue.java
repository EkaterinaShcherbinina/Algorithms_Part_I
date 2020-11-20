package PriorityQueues.SliderPuzzle;

import java.util.TreeMap;

public class MyQueue {
    private int first = 0;
    private int last = 0;
    private int[] keys;

    private TreeMap<Integer, String> treeMap;

    public MyQueue() {
        treeMap = new TreeMap<>();
    }

    public boolean isEmpty() {
        return treeMap.size() == 0;
    }

    public void append(String item) {
        treeMap.put(last++, item);
    }

    public String remove() throws IllegalAccessException {
        if(isEmpty()) throw new IllegalAccessException();
        return treeMap.get(first++);
    }

    public String removeElement(int index) throws IllegalAccessException {
        if(isEmpty()) throw new IllegalAccessException();
        String res = treeMap.remove(keys[index]);
        deleteKey(index);
        return res;
    }

    public String returnElement(int index) throws IllegalAccessException {
        if(isEmpty()) throw new IllegalAccessException();
        return treeMap.get(keys[index]);
    }

    private void deleteKey(int index) {
        // some code for deleting and resizing array;
    }
}
