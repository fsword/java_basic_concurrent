package concurrency.usage06;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * User: john
 * Date: Aug 9, 2010
 * Time: 8:09:31 PM
 */
public class ExceptionInCallable implements Callable<String> {

    public String call() throws Exception {
        throw new Exception("error");
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> f = pool.submit(new ExceptionInCallable());
        try{
            f.get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        pool.shutdown();
    }
}
