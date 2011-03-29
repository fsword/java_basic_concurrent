package concurrency.usage03;

/**
 * User: john
 * Date: 11-3-27
 * Time: 下午4:46
 */
public class MainStop {
    public static void main(String[] args) {
        Thread im;
        im = new InterruptMe();
        im.start();
        im.stop();
    }
}
