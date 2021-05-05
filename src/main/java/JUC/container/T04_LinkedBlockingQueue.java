package JUC.container;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 无界队列
 *
 */
public class T04_LinkedBlockingQueue {

    static LinkedBlockingQueue<String> strs = new LinkedBlockingQueue();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a"+i);
                    System.out.println("put: a"+i);
//                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"P1").start();

        for (int i = 0; i < 5; i++) {
        new Thread(()->{
                for(;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+" take -"+strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"c"+i).start();
        }
    }
}
