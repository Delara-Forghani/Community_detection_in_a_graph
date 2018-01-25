package ir.aut.ceit.app;

import java.util.ArrayList;

public class OptimumSort {
    ArrayList<TriangleSpecifier> arrayToSort;
    String sortType;

    int bounderyNum;

    public OptimumSort(String sortType, int n) {
        bounderyNum = n;
        this.sortType = sortType;
    }

    public void swapIndexes(int i, int j) {
        TriangleSpecifier x;
        x = arrayToSort.get(i);
        arrayToSort.set(i, arrayToSort.get(j));
        arrayToSort.set(j, x);
    }

    public void setOptimumSort(ArrayList<TriangleSpecifier> array, int left, int right) {
        this.arrayToSort = array;
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
                swapIndexes(i, j);
                i++;
                j--;

            }
        }

        if (left < j) {
            if (j - left < bounderyNum) {
                if (sortType.equals("Insertion")) {
                    setInsertionSort(array, left, j);
                } else if (sortType.equals("Bubble")) {
                    setBubbleSort(array, left, j);
                }
            } else {
                setOptimumSort(array, left, j);
            }
        }
        if (i < right) {
            if (right - i < bounderyNum) {
                if (sortType.equals("Insertion")) {
                    setInsertionSort(array, i, right);
                } else if (sortType.equals("Bubble")) {
                    setBubbleSort(array, i, right);
                }
            } else {
                setOptimumSort(array, i, right);
            }
        }


    }

    public void setBubbleSort(ArrayList<TriangleSpecifier> arrayList, int left, int right) {

        int n = right - left + 1;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arrayList.get(j + left).edgePrivilege > arrayList.get(j + 1 + left).edgePrivilege) {
                    TriangleSpecifier temp = arrayList.get(j + left);
                    arrayList.set(j + left, arrayList.get(j + 1 + left));
                    arrayList.set(j + 1 + left, temp);

                }
    }

    public void setInsertionSort(ArrayList<TriangleSpecifier> arrayList, int left, int right) {
        int n = right - left + 1;
        for (int i = 1; i < n; ++i) {
            TriangleSpecifier temp = arrayList.get(i + left);
            double key = temp.edgePrivilege;
            int j = i - 1;

            while (j >= 0 && arrayList.get(j + left).edgePrivilege > key) {
                arrayList.set(j + 1 + left, arrayList.get(j + left));
                j = j - 1;
            }
            arrayList.set(j + 1 + left, temp);
        }
    }

}

