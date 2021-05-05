package JUC.container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_TicketSeller {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
//                while (tickets.size() > 0){
//                    System.out.println("销售了---"+tickets.remove(0));
//                }
                while (true){
                    String s = tickets.poll();
                    if(s == null) break;
                    else System.out.println("销售了---"+s);
                }
            }).start();
        }
    }
}
