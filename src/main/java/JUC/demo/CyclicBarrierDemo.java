package JUC.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    public static void main(String[] args) {
        CyclicBarrier cyclic = new CyclicBarrier(30, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }




}
