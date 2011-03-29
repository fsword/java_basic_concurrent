package concurrency.usage05;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:46:51
 */
public class StopThreadSync {
    private boolean stop;


    public static void main(String[] args) throws InterruptedException {
        final StopThreadSync sts = new StopThreadSync();
        new Thread(){
            @Override
            public void run() {
                int i = 0;
                while(!sts.isStop())
                    i++;
                System.out.println(i);
            }
        }.start();

        Thread.sleep(1000);
        sts.stop();
    }

    private synchronized boolean isStop(){
        return stop;
    }

    private synchronized void stop(){
        stop = true;
    }
}