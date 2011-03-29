package concurrency.usage04;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:54:52
 */
public class ThreadWithUnbox {

    private static Integer counter = 5;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            synchronized (counter) {
                counter--;
                System.out.println("counter is:" + counter);
                if (counter == 0) {
                    try {
                        counter.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("deal!");
            }
        }
    }
}
