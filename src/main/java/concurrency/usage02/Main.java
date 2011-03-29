package concurrency.usage02;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:26:37
 */
public class Main {
    public static void main(String[] args) {
        ReturnThread t = new ReturnThread();
        t.start();
        System.out.println(t.getResult());
    }
}
