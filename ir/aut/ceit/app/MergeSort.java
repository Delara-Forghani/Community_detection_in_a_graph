package ir.aut.ceit.app;

import java.util.ArrayList;


class MergeSort {

    void merge(ArrayList<TriangleSpecifier> arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;


        TriangleSpecifier L[] = new TriangleSpecifier[n1];
        TriangleSpecifier R[] = new TriangleSpecifier[n2];


        for (int i = 0; i < n1; ++i)
            L[i] = arr.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.get(m + 1 + j);





        int i = 0, j = 0;


        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].edgePrivilege <= R[j].edgePrivilege) {
                arr.set(k, L[i]); //[] = L[i];
                i++;
            } else {
                arr.set(k, R[j]);//arr[k] = R[j];
                j++;
            }
            k++;
        }


        while (i < n1) {
            arr.set(k, L[i]); //[k] =L[i];
            i++;
            k++;
        }


        while (j < n2) {
            arr.set(k, R[j]); //[k] = R[j];
            j++;
            k++;
        }
    }


    void sort(ArrayList<TriangleSpecifier> arr, int l, int r) {
        if (l < r) {

            int m = (l + r) / 2;


            sort(arr, l, m);
            sort(arr, m + 1, r);


            merge(arr, l, m, r);
        }
    }
}