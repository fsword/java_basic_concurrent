package concurrency.usage02;

import java.util.concurrent.*;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 16:04:22
 */
public class SimpleCallable implements Callable {

    public Object call(){
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        return (System.currentTimeMillis()% 2 == 0) ? "true" : "false";
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future f = threadPool.submit(new SimpleCallable());
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}
