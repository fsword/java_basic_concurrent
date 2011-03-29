package concurrency.old;

/**
 * User: john
 * Date: 11-2-18
 * Time: 下午4:01
 */
public class LockArea {

    private volatile int id = 0;

    public static void main(String[] args) {
        final LockArea la = new LockArea();
        new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (la){
                        la.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                la.id = la.id + 1;
                System.out.println(la.id);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (la){
                        la.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                la.id = la.id + 2;
                System.out.println(la.id);
            }
        }.start();
        synchronized (la){
            la.notifyAll();
        }
    }
}
