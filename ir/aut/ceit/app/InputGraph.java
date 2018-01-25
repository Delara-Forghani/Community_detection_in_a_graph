package ir.aut.ceit.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class InputGraph {
    public int indexStart[];
    public int[] degrees;
    public ArrayList<AdjacentList> adjacentLists = new ArrayList<>();
    public ArrayList<Tuple> edges = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    public InputSort inputSort;
    public int MAX;
    public InputSortForMatrix inputSortForMatrix;
    public EdgeIterator edgeIterator;
    public EdgeIterator matrixIterator;
    public QuickSort quickSort;
    public InsertionSort insertionSort;
    public MergeSort mergeSort;
    public BubbleSort bubbleSort;
    public OptimumSort optimumSort;
    private String filePath;
    private String line = "";
    private int vertex1;
    private int vertex2;
    boolean checked = false;
    public WriteOutput output;

    public void getGraphData() throws IOException {
        System.out.println("please give us the text file path: ");
        filePath = input.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        System.out.println("please tell us which data structure you want to use for storing the graph and which algorithm you want to deploy: ");
        String command = input.nextLine();
        String commandParse[] = command.split(" ");
        if (commandParse[1].equals("Matrix")) {
            try {
                Time time1 = new Time(System.currentTimeMillis());
                while ((line = reader.readLine()) != null) {
                    String[] vertices = line.split(",");
                    vertex1 = Integer.parseInt(vertices[0]);
                    vertex2 = Integer.parseInt(vertices[1]);
                    edges.add(new Tuple(vertex1, vertex2));
                }
                Time time2 = new Time(System.currentTimeMillis());
                System.out.printf("The beginning of data storage :  " + time1.toString() + "\t" + "The end of data storage :  " + time2.toString() + "\n");
                inputSortForMatrix = new InputSortForMatrix();
                inputSortForMatrix.sortList(edges, 0, edges.size() - 1);
                MAX = edges.get(edges.size() - 1).vertex1;
                indexStart = new int[MAX + 1];
                degrees = new int[MAX + 1];
                for (int i = 0; i < edges.size(); i++) {
                    degrees[edges.get(i).vertex1]++;
                }
                for (int i = 0; i < edges.size(); ) {
                    int firstIndex = edges.get(i).vertex1;
                    indexStart[firstIndex] = i;
                    while (edges.get(i).vertex1 == firstIndex) {
                        if (i + 1 < edges.size()) {
                            i++;
                        } else {
                            i++;
                            break;
                        }
                    }
                }
                Time time3 = new Time(System.currentTimeMillis());
                do {
                    matrixIterator = new EdgeIterator(edges, indexStart, MAX, degrees);
                    matrixIterator.findTrianglesinMatrix();
//                    System.out.println("%%%%%%%%%%%%%");
//                    for (int i = 0; i < matrixIterator.triangleSpecifiers.size(); i++) {
//                        System.out.printf("  %d  %d  %d  %f  \n", matrixIterator.triangleSpecifiers.get(i).vertex1, matrixIterator.triangleSpecifiers.get(i).vertex2, matrixIterator.triangleSpecifiers.get(i).triangleCounter, matrixIterator.triangleSpecifiers.get(i).edgePrivilege);
//                    }
//                    System.out.println("%%%%%%%%%%%%%");
                    if (commandParse[2].equals("Quick")) {
                        quickSort = new QuickSort();
                        quickSort.sort(matrixIterator.triangleSpecifiers, 0, matrixIterator.triangleSpecifiers.size() - 1);
                    } else if (commandParse[2].equals("Insertion")) {
                        insertionSort = new InsertionSort();
                        insertionSort.sort(matrixIterator.triangleSpecifiers);
                    } else if (commandParse[2].equals("Merge")) {
                        mergeSort = new MergeSort();
                        mergeSort.sort(matrixIterator.triangleSpecifiers, 0, matrixIterator.triangleSpecifiers.size() - 1);
                    } else if (commandParse[2].equals("Bubble")) {
                        bubbleSort = new BubbleSort();
                        bubbleSort.sort(matrixIterator.triangleSpecifiers);
                    } else if (commandParse[2].equals("Optimum")) {
                        optimumSort = new OptimumSort(commandParse[3], Integer.parseInt(commandParse[4]));
                        optimumSort.setOptimumSort(matrixIterator.triangleSpecifiers, 0, matrixIterator.triangleSpecifiers.size() - 1);
                    }
//                    System.out.println("***********");
//                    for (int i = 0; i < matrixIterator.triangleSpecifiers.size(); i++) {
//                        System.out.printf("  %d  %d  %d  %f  \n", matrixIterator.triangleSpecifiers.get(i).vertex1, matrixIterator.triangleSpecifiers.get(i).vertex2, matrixIterator.triangleSpecifiers.get(i).triangleCounter, matrixIterator.triangleSpecifiers.get(i).edgePrivilege);
//                    }
//                    System.out.println("***********");

                    matrixIterator.removeLeastSignicantNodeMat(matrixIterator.triangleSpecifiers.get(0));
                } while (matrixIterator.connectedCheck() && matrixIterator.triangleSpecifiers.size() != 0);
                Time time4 = new Time(System.currentTimeMillis());
                System.out.printf("The beginning of running algorithm :  " + time3.toString() + "\t" + "The end of running algorithm :  " + time4.toString() + "\n");
                output = new WriteOutput(matrixIterator.visited);
            } catch (FileNotFoundException ex) {
                System.err.println("The file couldn't be found");
            } catch (IOException e) {
                System.err.println("IO exception while reading from file");
            }
        } else if (commandParse[1].equals("LinkedList")) {
            try {
                Time time1 = new Time(System.currentTimeMillis());
                while ((line = reader.readLine()) != null) {
                    checked = false;
                    String[] vertices = line.split(",");
                    vertex1 = Integer.parseInt(vertices[0]);
                    vertex2 = Integer.parseInt(vertices[1]);
                    edges.add(new Tuple(vertex1, vertex2));


                    if (adjacentLists.size() == 0) {
                        AdjacentList newList = new AdjacentList();
                        newList.addNode(vertex1);
                        newList.addNode(vertex2);
                        adjacentLists.add(newList);
                        checked = true;
                    } else {
                        for (int i = 0; i < adjacentLists.size(); i++) {
                            if (adjacentLists.get(i).head.vertexValue == vertex1) {
                                adjacentLists.get(i).addNode(vertex2);
                                checked = true;
                            }
                        }

                        if (!checked) {
                            AdjacentList newList = new AdjacentList();
                            newList.addNode(vertex1);
                            newList.addNode(vertex2);
                            adjacentLists.add(newList);
                        }

                    }

                }
                Time time2 = new Time(System.currentTimeMillis());
                System.out.printf("The beginning of data storage :  " + time1.toString() + "\t" + "The end of data storage :  " + time2.toString() + "\n");

            } catch (FileNotFoundException ex) {
                System.err.println("The file couldn't be found");
            } catch (IOException e) {
                System.err.println("IO exception while reading from file");
            }
            inputSort = new InputSort();
            inputSort.sortList(adjacentLists, 0, adjacentLists.size() - 1);
            Time time3 = new Time(System.currentTimeMillis());
            do {
                edgeIterator = new EdgeIterator(adjacentLists, edges);
                edgeIterator.findoutTriangles();
                if (commandParse[2].equals("Quick")) {
                    quickSort = new QuickSort();
                    quickSort.sort(edgeIterator.triangleSpecifiers, 0, edgeIterator.triangleSpecifiers.size() - 1);

                } else if (commandParse[2].equals("Insertion")) {
                    insertionSort = new InsertionSort();
                    insertionSort.sort(edgeIterator.triangleSpecifiers);

                } else if (commandParse[2].equals("Merge")) {
                    mergeSort = new MergeSort();
                    mergeSort.sort(edgeIterator.triangleSpecifiers, 0, edgeIterator.triangleSpecifiers.size() - 1);
                } else if (commandParse[2].equals("Bubble")) {
                    bubbleSort = new BubbleSort();
                    bubbleSort.sort(edgeIterator.triangleSpecifiers);

                } else if (commandParse[2].equals("Optimum")) {
                    optimumSort = new OptimumSort(commandParse[3], Integer.parseInt(commandParse[4]));
                    optimumSort.setOptimumSort(edgeIterator.triangleSpecifiers, 0, edgeIterator.triangleSpecifiers.size() - 1);

                }
//                System.out.println("***********");
//                for (int i = 0; i < edgeIterator.triangleSpecifiers.size(); i++) {
//                    System.out.printf("  %d  %d  %d  %f  \n", edgeIterator.triangleSpecifiers.get(i).vertex1, edgeIterator.triangleSpecifiers.get(i).vertex2, edgeIterator.triangleSpecifiers.get(i).triangleCounter, edgeIterator.triangleSpecifiers.get(i).edgePrivilege);
//                }
//                System.out.println("***********");
                edgeIterator.removeLeastSignicantNode(edgeIterator.triangleSpecifiers.get(0));
            } while (edgeIterator.connectedCheck() && edgeIterator.triangleSpecifiers.size() != 0);
            Time time4 = new Time(System.currentTimeMillis());
            System.out.printf("The beginning of running algorithm :  " + time3.toString() + "\t" + "The end of data running algorithm :  " + time4.toString() + "\n");
            output = new WriteOutput(edgeIterator.visited);
        }

    }
}





