package JUC.demo;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉模式 （线程安全）
 */
public class SingletonDemo03 {

    private static SingletonDemo03 instance;

    public static SingletonDemo03 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo03.class) {
                if (instance == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new SingletonDemo03();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
//        SingletonDemo03 instance = SingletonDemo03.getInstance();
//        System.out.println(instance);

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo03.getInstance().hashCode());
            }).start();
        }
    }
}
