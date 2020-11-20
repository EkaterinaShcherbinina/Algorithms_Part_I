package StackQueues_ElementarySorts.ElementarySorts.IntersectionOfTwoSets;

import java.util.HashSet;

class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if(this.x == o.x && this.y == o.y) return 0;
        else if(this.x < o.x) return -1;
        else return 1;
    }
}

public class IntersectionTwoSets {
    public static int getCountIntersection(Comparable[] firstSet, Comparable[] secondSet) {
        Comparable[] arrayFirst = firstSet.length >= secondSet.length ? firstSet : secondSet;
        Comparable[] arraySecond = firstSet.length < secondSet.length ? firstSet : secondSet;

        HashSet<Comparable> set = new HashSet<>();
        for(int i = 0; i < arrayFirst.length; i++) {
            set.add(arrayFirst[i]);
        }

        int count = 0;
        for(Comparable val: set) {
            for(int j = 0; j < arraySecond.length; j++) {
                if(val.compareTo(arraySecond[j]) == 0) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Integer[] array1 = {1, 6, 3, 8, 0, 2, 4, 7, 9, 5};
        Integer[] array2 = {0, 4, 4, 6, 6, 3, 3, 8, 3, 1, 44, 22, 77};

        System.out.println(IntersectionTwoSets.getCountIntersection(array1, array2));
    }
}
