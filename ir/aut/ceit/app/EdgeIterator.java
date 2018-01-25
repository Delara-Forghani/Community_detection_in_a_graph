package ir.aut.ceit.app;

import java.util.ArrayList;

public class EdgeIterator {
    public ArrayList<AdjacentList> listOfNodes = new ArrayList<>();
    public ArrayList<Tuple> edgesTransferred = new ArrayList<>();
    public ArrayList<TriangleSpecifier> triangleSpecifiers;
    public int[] startIndexes;
    public int[] nodeDegrees;
    public int MAXAssign;
    public boolean visited[];
    Queue queue;

    public EdgeIterator(ArrayList<Tuple> edges, int[] start, int max, int[] degrees) {
        triangleSpecifiers = new ArrayList<>();
        edgesTransferred = edges;
        startIndexes = start;
        MAXAssign = max;
        nodeDegrees = degrees;
        visited = new boolean[MAXAssign];
        queue = new Queue(MAXAssign);
    }

    public EdgeIterator(ArrayList<AdjacentList> listOfNodesAssign, ArrayList<Tuple> edges) {
        triangleSpecifiers = new ArrayList<>();
        listOfNodes = listOfNodesAssign;
        edgesTransferred = edges;
        visited = new boolean[listOfNodes.size()];
        queue = new Queue(listOfNodes.size());

    }

    public void findTrianglesinMatrix() {
        int count;
        boolean exist;
        for (int i = 0; i < edgesTransferred.size(); i++) {
            count = 0;
            exist = false;
            for (int j = 0; j < triangleSpecifiers.size(); j++) {
                if (edgesTransferred.get(i).vertex1 == triangleSpecifiers.get(j).vertex2 && edgesTransferred.get(i).vertex2 == triangleSpecifiers.get(j).vertex1) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                int fistVertexStart = startIndexes[edgesTransferred.get(i).vertex1];
                int secondVertexStart = startIndexes[edgesTransferred.get(i).vertex2];
                while (edgesTransferred.get(fistVertexStart).vertex1 == edgesTransferred.get(i).vertex1 && edgesTransferred.get(secondVertexStart).vertex1 == edgesTransferred.get(i).vertex2) {
                    if (edgesTransferred.get(fistVertexStart).vertex2 == edgesTransferred.get(secondVertexStart).vertex2) {
                        if (edgesTransferred.get(fistVertexStart).vertex2 != 0) {
                            count++;
                        }
                        if (fistVertexStart + 1 < edgesTransferred.size() && secondVertexStart + 1 < edgesTransferred.size()) {
                            fistVertexStart++;
                            secondVertexStart++;
                        } else {
                            break;
                        }
                    } else if (edgesTransferred.get(fistVertexStart).vertex2 < edgesTransferred.get(secondVertexStart).vertex2) {
                        if (fistVertexStart + 1 < edgesTransferred.size()) {
                            fistVertexStart++;
                        } else {
                            break;
                        }
                    } else if (edgesTransferred.get(fistVertexStart).vertex2 > edgesTransferred.get(secondVertexStart).vertex2) {
                        if (secondVertexStart + 1 < edgesTransferred.size()) {
                            secondVertexStart++;
                        } else {
                            break;
                        }
                    }
                }
                if (edgesTransferred.get(i).vertex1 != 0 && edgesTransferred.get(i).vertex2 != 0) {
                    triangleSpecifiers.add(new TriangleSpecifier(edgesTransferred.get(i).vertex1, edgesTransferred.get(i).vertex2, count));
                }
            }
        }
        for (int i = 0; i < triangleSpecifiers.size(); i++) {
            if (nodeDegrees[triangleSpecifiers.get(i).vertex1] - 1 == 0 || nodeDegrees[triangleSpecifiers.get(i).vertex2] - 1 == 0) {
                triangleSpecifiers.get(i).edgePrivilege = Double.POSITIVE_INFINITY;
            } else if (nodeDegrees[triangleSpecifiers.get(i).vertex1] - 1 < nodeDegrees[triangleSpecifiers.get(i).vertex2] - 1) {
                triangleSpecifiers.get(i).edgePrivilege = (double) (triangleSpecifiers.get(i).triangleCounter + 1) / (double) (nodeDegrees[triangleSpecifiers.get(i).vertex1] - 1);
            } else {
                triangleSpecifiers.get(i).edgePrivilege = (double) (triangleSpecifiers.get(i).triangleCounter + 1) / (double) (nodeDegrees[triangleSpecifiers.get(i).vertex2] - 1);
            }
        }
    }

    public void findoutTriangles() {
        int firstVertex = 0;
        int secondVertex = 0;
        int count;
        boolean exist = false;
        for (int i = 0; i < edgesTransferred.size(); i++) {
            exist = false;
            count = 0;
            for (int j = 0; j < triangleSpecifiers.size(); j++) {
                if (edgesTransferred.get(i).vertex1 == triangleSpecifiers.get(j).vertex2 && edgesTransferred.get(i).vertex2 == triangleSpecifiers.get(j).vertex1) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                for (int j = 0; j < listOfNodes.size(); j++) {
                    if (listOfNodes.get(j).head.vertexValue == edgesTransferred.get(i).vertex1) {
                        firstVertex = j;
                    }
                }

                for (int j = 0; j < listOfNodes.size(); j++) {
                    if (listOfNodes.get(j).head.vertexValue == edgesTransferred.get(i).vertex2) {
                        secondVertex = j;
                    }
                }
                for (int j = 0; j < listOfNodes.get(firstVertex).values.size(); j++) {
                    if (listOfNodes.get(secondVertex).values.contains(listOfNodes.get(firstVertex).values.get(j))) {
                        count++;
                    }
                }

                triangleSpecifiers.add(new TriangleSpecifier(firstVertex + 1, secondVertex + 1, count));

            }

        }
        for (int i = 0; i < triangleSpecifiers.size(); i++) {
            int minDegree;

            if (listOfNodes.get(triangleSpecifiers.get(i).vertex1 - 1).values.size() - 1 > listOfNodes.get(triangleSpecifiers.get(i).vertex2 - 1).values.size() - 1) {
                minDegree = listOfNodes.get(triangleSpecifiers.get(i).vertex2 - 1).values.size() - 1;
            } else {
                minDegree = listOfNodes.get(triangleSpecifiers.get(i).vertex1 - 1).values.size() - 1;
            }
            if (minDegree == 0) {
                triangleSpecifiers.get(i).edgePrivilege = Double.POSITIVE_INFINITY;

            } else {
                triangleSpecifiers.get(i).edgePrivilege = (double) (triangleSpecifiers.get(i).triangleCounter + 1) / (double) minDegree;
            }
            //System.out.printf("%d  %d  %d  %d  %d  %f\n", triangleSpecifiers.get(i).vertex1, triangleSpecifiers.get(i).vertex2, triangleSpecifiers.get(i).triangleCounter, listOfNodes.get(triangleSpecifiers.get(i).vertex1 - 1).values.size(), listOfNodes.get(triangleSpecifiers.get(i).vertex2 - 1).values.size(), triangleSpecifiers.get(i).edgePrivilege);

        }

    }


    public void bfs(int node) {
        visited[node - 1] = true;
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            for (int i = 0; i < listOfNodes.get(node - 1).values.size(); i++) {
                if (!visited[listOfNodes.get(node - 1).values.get(i) - 1]) {
                    queue.enqueue(listOfNodes.get(node - 1).values.get(i));
                    visited[listOfNodes.get(node - 1).values.get(i) - 1] = true;
                }
            }
        }
    }

    public void bfsMatrix(int node) {
        visited[node - 1] = true;
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            if (node == MAXAssign) {
                for (int i = startIndexes[node]; i < edgesTransferred.size(); i++) {
                    if (edgesTransferred.get(i).vertex2 != 0) {
                        if (!visited[edgesTransferred.get(i).vertex2 - 1]) {
                            visited[edgesTransferred.get(i).vertex2 - 1] = true;
                            queue.enqueue(edgesTransferred.get(i).vertex2);
                        }
                    }
                }
            } else {
                for (int i = startIndexes[node]; i < startIndexes[node + 1]; i++) {
                    if (edgesTransferred.get(i).vertex2 != 0) {
                        if (!visited[edgesTransferred.get(i).vertex2 - 1]) {
                            visited[edgesTransferred.get(i).vertex2 - 1] = true;
                            queue.enqueue(edgesTransferred.get(i).vertex2);
                        }
                    }
                }
            }
        }
    }

    public boolean connectedCheck() {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }


    public void removeLeastSignicantNode(TriangleSpecifier edgeToBeRemove) {
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        int firstVertex = edgeToBeRemove.vertex1;
        int secondVertex = edgeToBeRemove.vertex2;
        if (listOfNodes.get(firstVertex - 1).values.size() != 0) {
            listOfNodes.get(firstVertex - 1).values.remove((Integer) secondVertex);
        }
        if (listOfNodes.get(secondVertex - 1).values.size() != 0) {
            listOfNodes.get(secondVertex - 1).values.remove((Integer) firstVertex);
        }
        triangleSpecifiers.remove(edgeToBeRemove);
        for (int i = 0; i < edgesTransferred.size(); i++) {
            if (edgesTransferred.get(i).vertex1 == edgeToBeRemove.vertex1 && edgesTransferred.get(i).vertex2 == edgeToBeRemove.vertex2) {
                edgesTransferred.remove(i);
                break;
            }
        }
        for (int i = 0; i < edgesTransferred.size(); i++) {
            if (edgesTransferred.get(i).vertex2 == edgeToBeRemove.vertex1 && edgesTransferred.get(i).vertex1 == edgeToBeRemove.vertex2) {
                edgesTransferred.remove(i);
                break;
            }
        }
        bfs(listOfNodes.get(0).head.vertexValue);
    }

    public void removeLeastSignicantNodeMat(TriangleSpecifier edgeToBeRemove) {
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        int firstVertex = edgeToBeRemove.vertex1;
        int secondVertex = edgeToBeRemove.vertex2;
        for (int i = 0; i < edgesTransferred.size(); i++) {
            if (edgesTransferred.get(i).vertex1 == edgeToBeRemove.vertex1 && edgesTransferred.get(i).vertex2 == edgeToBeRemove.vertex2) {
                edgesTransferred.get(i).vertex2 = 0;
                if (nodeDegrees[edgeToBeRemove.vertex1] != 0) {
                    nodeDegrees[edgeToBeRemove.vertex1]--;
                }
                if (nodeDegrees[edgeToBeRemove.vertex2] != 0) {
                    nodeDegrees[edgeToBeRemove.vertex2]--;
                }
                break;
            }
        }
        for (int i = 0; i < edgesTransferred.size(); i++) {
            if (edgesTransferred.get(i).vertex1 == edgeToBeRemove.vertex2 && edgesTransferred.get(i).vertex2 == edgeToBeRemove.vertex1) {
                edgesTransferred.get(i).vertex2 = 0;
                break;
            }
        }
        triangleSpecifiers.remove(edgeToBeRemove);
        bfsMatrix(edgesTransferred.get(0).vertex1);
    }


}
