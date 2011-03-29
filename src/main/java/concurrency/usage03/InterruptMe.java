package concurrency.usage03;

/**
 * User: john
 * Date: 11-3-27
 * Time: 下午4:45
 */
public class InterruptMe extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            System.out.println("interrupted. Clean something");
        }
    }
}
