package ir.aut.ceit.app;

import java.io.IOException;
import java.sql.Time;

public class Main {

    public static void main(String[] args) throws IOException {
        Time time1 = new Time(System.currentTimeMillis());
        InputGraph graph = new InputGraph();
        graph.getGraphData();
        Time time2 = new Time(System.currentTimeMillis());
        System.out.printf("Project Start time:  " + time1.toString() + "\t" + "Project end Time :  " + time2.toString()  + "\n");
    }
}
