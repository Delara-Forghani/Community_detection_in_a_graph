package ir.aut.ceit.app;


import java.util.ArrayList;

public class InputSortForMatrix {
    public void mergeSubLists(ArrayList<Tuple> list, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;


        Tuple L[] = new Tuple[n1];
        Tuple R[] = new Tuple[n2];


        for (int i = 0; i < n1; ++i)
            L[i] = list.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = list.get(m + 1 + j);





        int i = 0, j = 0;


        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].vertex1 <= R[j].vertex1) {
                list.set(k, L[i]); //[] = L[i];
                i++;
            } else {
                list.set(k, R[j]);//arr[k] = R[j];
                j++;
            }
            k++;
        }


        while (i < n1) {
            list.set(k, L[i]); //[k] =L[i];
            i++;
            k++;
        }


        while (j < n2) {
            list.set(k, R[j]); //[k] = R[j];
            j++;
            k++;
        }
    }


    public void sortList(ArrayList<Tuple> list, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sortList(list, l, m);
            sortList(list, m + 1, r);
            mergeSubLists(list, l, m, r);
        }
    }

}
