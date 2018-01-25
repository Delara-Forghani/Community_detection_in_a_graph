package ir.aut.ceit.app;

import java.util.ArrayList;

class InsertionSort {

    void sort(ArrayList<TriangleSpecifier> arr) {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            TriangleSpecifier temp = arr.get(i);
            double key = temp.edgePrivilege;
            int j = i - 1;


            while (j >= 0 && arr.get(j).edgePrivilege > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, temp);
        }
    }
}