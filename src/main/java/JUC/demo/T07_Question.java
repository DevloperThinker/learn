package JUC.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T07_Question {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);
    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 1; i < 27; i++) {
                try {
                    q1.put("OK");
                    q2.take();
                    System.out.print(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t1").start();

        new Thread(()->{
            for (int i = 1; i < 27; i++) {
                try {
                    q1.take();
                    System.out.print((char)(64+i));
                    q2.put("OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t2").start();
    }
}
