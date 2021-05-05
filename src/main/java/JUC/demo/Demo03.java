package JUC.demo;

import java.util.ArrayList;
import java.util.List;

public class Demo03 {

    private volatile static int count = 0;

    private synchronized void m(){
        for (int i = 0; i < 10000; i++) {
            count ++;
        }
    }

    public static void main(String[] args) {
        Demo03 t = new Demo03();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread-"+i));
        }

        threads.forEach((o)->o.start() );
        threads.forEach((o)-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(count);
    }
}
