package ir.aut.ceit.app;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteOutput {
    ArrayList<Integer> A = new ArrayList<>();
    ArrayList<Integer> B = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();

    public WriteOutput(boolean[] visited) throws IOException {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == true) {
                A.add(i + 1);
            } else if (visited[i] == false) {
                B.add(i + 1);
            }
        }
        File file = new File("OutputFile");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        for (int i = 0; i < A.size(); i++) {
            stringBuilder.append(A.get(i));
            stringBuilder.append("\t" + "A" + "\n");
        }
        for (int i = 0; i < B.size(); i++) {
            stringBuilder.append(B.get(i));
            stringBuilder.append("\t" + "B" + "\n");
        }
        String finalResult = stringBuilder.toString();
        writer.write(finalResult);
        writer.close();
    }

}
