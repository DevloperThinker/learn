package JUC.container;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;

public class T06_PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("a");
        q.add("d");
        q.add("e");
        q.add("z");
        q.add("m");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }

    }
}
