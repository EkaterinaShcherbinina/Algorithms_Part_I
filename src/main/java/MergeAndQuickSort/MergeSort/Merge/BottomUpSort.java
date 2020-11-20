package MergeAndQuickSort.MergeSort.Merge;

import java.util.Comparator;
import java.util.Random;

class Student {
    private String name;
    private int id;
    public static Comparator<Student> comparatorByName = new CompareByName();
    public static Comparator<Student> comparatorById = new CompareById();

    private static class CompareByName implements Comparator<Student> {

        @Override
        public int compare(Student student, Student t1) {
            if(student.getName().charAt(0) < t1.getName().charAt(0)) return -1;
            else if(student.getName().charAt(0) > t1.getName().charAt(0)) return 1;
            else return 0;
        }
    }

    private static class CompareById implements Comparator<Student> {

        @Override
        public int compare(Student student, Student t1) {
            if(student.getId() < t1.getId()) return -1;
            else if(student.getId() > t1.getId()) return 1;
            else return 0;
        }
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

public class BottomUpSort {
    private static Comparator comparator;
    public void sort(Object[] a, Comparator comparator) {
        this.comparator = comparator;
        int n = a.length;
        Object[] aux = new Object[n];

        for(int sz = 1; sz < n; sz += sz) {
            for(int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    private static void merge(Object[] a, Object[] aux, int lo, int mid, int hi) {
        int n = a.length;
        for(int i = 0; i < n; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(comparator.compare(aux[i], aux[j]) == -1) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

    }
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    public static void main(String[] args) {
        Random random = new Random();
        String[] names = {"Ariela", "Maxim", "Polina", "Anatoliy", "Kate", "Ilia", "Inna", "Elena", "Marina",
                "Kristina", "Mia", "Vera", "Lubov", "Kirill", "Vasiliy", "Renat", "Vyacheslav", "Nikolay", "Valentina", "Viktor"};

        Student[] students = new Student[20];
        for(int i = 0; i < 20; i++) {
            students[i] = new Student(names[random.nextInt(names.length)], random.nextInt(20));
        }

        BottomUpSort bottomUpSort = new BottomUpSort();
        //Integer[] array = {3, 5, 3, 7, 8, 55, 7, 33, 47, 14, 90, 0, 59, 21, 73, 56, 72, 84, 30, 79, 2, 66};
        bottomUpSort.sort(students, Student.comparatorById);
        for(Student st: students) {
            System.out.print(st.getName() + ": " + st.getId());
            System.out.println("");
        }
    }
}
