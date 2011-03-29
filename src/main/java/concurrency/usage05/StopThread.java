package concurrency.usage05;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:46:51
 */
public class StopThread {
    private boolean stop;

    public static void main(String[] args) throws InterruptedException {
        final StopThread st = new StopThread();
        new Thread(){
            @Override
            public void run() {
                int i = 0;
                while(!st.stop)
                    i++;
                System.out.println(i);
                
            }
        }.start();

        Thread.sleep(1000);
        st.stop = true;
    }
}
