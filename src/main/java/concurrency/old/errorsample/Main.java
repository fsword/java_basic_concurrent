package concurrency.old.errorsample;

/**
 * User: john
 * Date: Aug 9, 2010
 * Time: 8:23:50 PM
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ErrorThread1 t = new ErrorThread1();
        t.start();
        Thread.sleep(1000);
        t.shutdown();
    }
}
