package concurrency.practice02;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 12:00:04
 */
public class ObjectMonit implements Runnable{

    private Object lock;

    public ObjectMonit(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.hashCode()+" lock notified - "+System.currentTimeMillis());
        }
    }

}
