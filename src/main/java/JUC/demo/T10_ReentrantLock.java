package JUC.demo;

import java.util.concurrent.locks.ReentrantLock;

public class T10_ReentrantLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
