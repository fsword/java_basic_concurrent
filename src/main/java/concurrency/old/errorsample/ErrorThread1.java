package concurrency.old.errorsample;

/**
 * User: john
 * Date: Aug 9, 2010
 * Time: 8:23:19 PM
 */
public class ErrorThread1 extends Thread{

    private volatile boolean stopped;

    @Override
    public void run() {
        int i = 0;
        while(!stopped){
            i++;
        }
        System.out.println("print " + i++ + " lines");

    }

    public void shutdown(){
        stopped = true;
    }
}
