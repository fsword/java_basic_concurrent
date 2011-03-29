package concurrency.usage02;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:26:37
 */
public class Main02 {
    public static void main(String[] args) {
        ReturnThread t = new ReturnThread();
        System.out.println(t.getResult());
        t.start();
    }
}
