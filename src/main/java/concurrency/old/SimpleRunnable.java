package concurrency.old;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 11:32:54
 */
public class SimpleRunnable implements Runnable{
    private String name;

    public SimpleRunnable(String name) {
        this.name = name;
    }

    public void run() {
        for(int i=0;i < 3;i++){
            try {Thread.sleep(200);} catch (InterruptedException ignored) {}
            System.out.println(name + "run " + i + " times: "+ System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for(int t=0; t < 2; t++){
            new Thread(new SimpleRunnable("thread"+t)).start();
        }
    }
}