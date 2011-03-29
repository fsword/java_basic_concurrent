package concurrency.practice02;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 12:26:11
 */
public class ObjectMonitMain {
    public static void main(String[] args){
        Object lock = new Object();
        Thread t1 = new Thread(new ObjectMonit(lock));
        Thread t2 = new Thread(new ObjectMonit(lock));
        Thread t3 = new Thread(new ObjectMonit(lock));
        t1.start(); t2.start(); t3.start();
        synchronized (lock){
            lock.notify();
        }
        try {Thread.sleep(1000);} catch (InterruptedException ignored) {}
        synchronized (lock){
            lock.notifyAll();
        }
        try {Thread.sleep(20);} catch (InterruptedException ignored) {}
        System.out.println(t1.isAlive() || t2.isAlive() || t3.isAlive());
    }
}
