package JUC.demo;

public class T03_Question {
//两个线程交叉打印，线程1打印A~Z，线程2打印1~26.交叉打印A1B2C3。。。
    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                for (int i = 1; i <= 26; i++) {
                    try {
                        lock.wait();
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(i);
                }
            }

        },"T2").start();

        new Thread(()->{
            synchronized (lock){
                for (int i = 1; i <= 26; i++) {
                    System.out.print((char)(64+i));
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        },"T1").start();


    }
}
