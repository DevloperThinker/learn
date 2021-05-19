package JUC.jvm;

import java.util.concurrent.TimeUnit;

public class HelloGC {


    public static void main(String[] args) {
        byte[] byteArray = new byte[50 *1024*1024];

        System.out.println("hello gc");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
