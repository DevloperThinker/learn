package JUC.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class T06_LockSupportDemo {

    static List list = Collections.synchronizedList(new ArrayList<>());
    static Thread t1 =null;
    static Thread t2=null;

    public static void main(String[] args) {

        t1 = new Thread(()->{
            System.out.println("T1启动");
            for (int i = 0; i < 10; i++) {
                list.add(new Object());
                System.out.println("add "+i);

                if(list.size() == 5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }

        });

         t2 = new Thread(()->{
            System.out.println("T2启动");
            try {
                if(list.size() != 5){
                    LockSupport.park();
                }
                LockSupport.unpark(t1);
            } catch (Exception e) {
                e.printStackTrace();
            }
             System.out.println("T2结束");
        });


        t2.start();
        t1.start();
    }
}
