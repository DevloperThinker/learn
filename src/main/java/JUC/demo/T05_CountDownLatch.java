package JUC.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T05_CountDownLatch {

    static List list = new ArrayList<>();

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println("T2启动");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{
            System.out.println("T1启动");
            for (int i = 0; i < 10; i++) {
                list.add(new Object());
                System.out.println("add "+i);
            }
            if(list.size() == 5){
                latch.countDown();
            }
        }).start();
    }
}
