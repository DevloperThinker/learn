package JUC.container;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class T05_ArrayBlockingQueue {

    static ArrayBlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a"+i);
        }

        System.out.println(strs.poll());
        System.out.println(strs.offer("aaaa"));
    }
}
