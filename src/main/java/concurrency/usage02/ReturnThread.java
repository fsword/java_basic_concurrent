package concurrency.usage02;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:17:57
 */
public class ReturnThread extends Thread{
    private Object lock = new Object();
    private String result;

    @Override
    public void run() {

        synchronized (lock){
            try {Thread.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            result = "done.";
            lock.notifyAll();
        }
    }



    public Object getResult(){
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
