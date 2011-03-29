package concurrency.usage05;

/**
 * User: john
 * Date: Aug 9, 2010
 * Time: 1:33:59 PM
 */
public class IntPlus {

    volatile int i = 0;

    public static void main(String[] args) {
        final IntPlus itp = new IntPlus();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int j=0;j < 5000000;j++)
                    itp.i++;
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(int j=0;j < 5000000;j++)
                    itp.i++;
            }
        };
        t2.start();
        try {
			t1.join();
	        t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println(itp.i);
    }
}
