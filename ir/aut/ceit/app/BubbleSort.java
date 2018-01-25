package ir.aut.ceit.app;

import java.util.ArrayList;


class BubbleSort {

    void sort(ArrayList<TriangleSpecifier> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr.get(j).edgePrivilege > arr.get(j + 1).edgePrivilege) {

                    TriangleSpecifier temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
    }
}
