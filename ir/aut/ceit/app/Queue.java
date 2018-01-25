package ir.aut.ceit.app;

public class Queue {      // FIFO( First in First Out )
    int[] queue;
    private int front;
    private int rear;
    private int size;

    public Queue(int size) {
        queue = new int[size];
        this.size = size;
        front = -1;
        rear = -1;
        for (int i = 0; i < size; i++) {
            queue[i] = -1;
        }
    }


    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    public void enqueue(int value) {
        if ((rear + 1) % size == front) {
            System.out.println("queue is full");
            return;
        } else if (isEmpty()) {
            front++;
            rear++;
            queue[rear] = value;
        } else {
            rear = (rear + 1) % size;
            queue[rear] = value;
        }
    }

    public int dequeue() {
        int value = -1;
        if (isEmpty()) {
            System.out.println("the queue is empty");
            return value;
        } else if (front == rear) {
            value = queue[front];
            rear = -1;
            front = -1;
            return value;
        } else {
            value = queue[front];
            front = (front + 1) % size;
            return value;
        }

    }


    public void print() {
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != -1) {
                System.out.print(queue[i] + " ");
            }
        }
        System.out.println();
    }
}

