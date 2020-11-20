package StackQueues_ElementarySorts.QueueStack.Permutation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue();

        Scanner in = new Scanner(System.in);
        System.out.println("Input a sequence: ");
        String sequence = in.nextLine();
        String[] array = sequence.split(" ");
        for (int i = 0; i < array.length; i++) {
            queue.enqueue(array[i]);
        }

        int k = -1;
        while (k < 0 || k > queue.size()) {
            System.out.print("Enter a permutation in the range from 0 to " + queue.size() + ": ");
            k = in.nextInt();
        }

        int count = 0;
        for (String val: queue) {
            if (k == count) break;
            System.out.println(val);
            count++;
        }
        in.close();
    }
}
