# Community_detection_in_a_graph

In this program it is tried to detect communities in graphs via an algorithm in which all the edges in the graph rates up and 
in each step of the algorithm they will be sorted by one of the four types of sorting algorithms :
Quick sort , Merge sort , Insertion sort , Bubble sort ; then the edge with the least score is omitted . 
The user can also take advantage of using a sorting algorithm called optimum sort which is almost like quick sort but
during the implementation of quick sort, before calling the recursive functions for each subarrays, it checks the length of 
two subarrays ,if it was less than N (N is a number defined by the user at the beginning of the program), it calls insertion or bubble sort instead of quick sort for the forgoing subarray . 
The algorithm terminates when the graph is divided into two parts . Each part involves the commiunities of the graph.

## Storing the graph 
This program can store the graph via adjacent linkedList or adjacent matrix.

## Running the test
To run a test the user should write a CSV text file consisting the edge list of the graph ,for example "3,4" ,each edge should 
be specified by its two vertices and written in a seperate line .At the beginning of the program the user will be asked to 
give the file path ,then they should write their desired command.

Examples of user's commands :

Run + storage type + sorting algorithm

* RUN‬‬ ‫‪Matrix‬‬ ‫‪Quick‬‬  
‫‪‫‪* RUN‬‬ ‫‪LinkedList‬‬ ‫‪Optimum‬‬ ‫‪Insertion‬‬ ‫‪N‬‬
