package concurrency.usage05;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:46:51
 */
public class StopThreadVolatile {
    private volatile boolean stop;

    public static void main(String[] args) throws InterruptedException {
        final StopThreadVolatile st = new StopThreadVolatile();
        new Thread(){
            @Override
            public void run() {
                int i = 0;
                while(!st.stop)
                    i++;
                System.out.println(i);

            }
        }.start();

        Thread.currentThread().interrupt();

        Thread.sleep(1000);
        st.stop = true;
    }
}