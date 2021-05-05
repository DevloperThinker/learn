package JUC.demo;

import java.util.concurrent.locks.LockSupport;

public class T04_Quesetion {
    //两个线程交叉打印，线程1打印A~Z，线程2打印1~26.交叉打印A1B2C3。。。
    static Thread t1 =null,t2=null;

    public static void main(String[] args) {
        t2 = new Thread(()->{
                for (int i = 1; i <= 26; i++) {
                    LockSupport.park(); //t2阻塞
                    System.out.print(i);
                    LockSupport.unpark(t1); //唤醒t1
                }
        },"T2");

       t1 =  new Thread(()->{
                for (int i = 1; i <= 26; i++) {
                    System.out.print((char)(64+i));
                    LockSupport.unpark(t2);//唤醒t2
                    LockSupport.park(); // t1 阻塞
                    }
        },"T1");

       t1.start();
       t2.start();

    }
}
