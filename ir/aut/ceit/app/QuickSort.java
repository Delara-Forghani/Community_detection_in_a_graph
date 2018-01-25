package ir.aut.ceit.app;

import java.util.ArrayList;

public class QuickSort {

    private ArrayList<TriangleSpecifier> array;

    public void swap(int i, int j) {
        TriangleSpecifier x;
        x = array.get(i);
        array.set(i, array.get(j));
        array.set(j, x);
    }

    public void sort(ArrayList<TriangleSpecifier> array, int left, int right) {
        this.array = array;
        double pivot = array.get(left + (right - left) / 2).edgePrivilege;
        int i = left;
        int j = right;
        while (i <= j) {
            while (array.get(i).edgePrivilege < pivot) {
                i++;
            }
            while (array.get(j).edgePrivilege > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (left < j) {
            sort(array, left, j);
        }
        if (i < right) {
            sort(array, i, right);
        }

    }
}