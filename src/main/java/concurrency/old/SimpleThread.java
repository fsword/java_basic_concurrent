package concurrency.old;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 11:32:54
 */
public class SimpleThread extends Thread{

    public SimpleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i < 3;i++){
            try {Thread.sleep(200);} catch (InterruptedException ignored) {}
            System.out.println(getName() + "run " + i + " times: "+ System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for(int t=0; t < 2; t++){
            new SimpleThread("thread"+t).start();
        }
    }
}
