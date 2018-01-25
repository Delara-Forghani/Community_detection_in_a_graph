package ir.aut.ceit.app;

import java.util.ArrayList;

public class AdjacentList {

    public AdjacentNode head;

    public AdjacentNode temp;

    public ArrayList<Integer> values;


    public AdjacentList() {
        head = null;
        values = new ArrayList<>();
    }

    public void addNode(int vertex) {
        AdjacentNode newNode = new AdjacentNode(vertex);
        if (head == null) {
            head = newNode;
        } else {
            temp = head;
            while (temp.link != null) {
                temp = temp.link;
            }
            temp.link = newNode;
            values.add(vertex);
        }

    }

    public AdjacentNode searchList(AdjacentNode firstNode, int specifiedValue) {
        if (firstNode == null) {
            System.out.println("The list is empty");
            return null;
        } else {
            temp = firstNode;
            while (temp.link != null) {
                if (temp.vertexValue == specifiedValue) {
                    return temp;
                }
                temp = temp.link;
            }
            if (temp.vertexValue == specifiedValue) {
                return temp;
            }
        }
        return null;
    }

}
