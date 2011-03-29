package concurrency.practice03;

import java.util.Date;

/**
 * User: john
 * Date: 11-2-18
 * Time: 下午7:50
 */
public class DaemonThread extends Thread{
    private boolean stopped;

    @Override
    public void run() {
        while (!stopped){
            try {
                sleep(1000);
                System.out.println(new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("deal");
    }

    public static void main(String[] args) {
        Thread dt = new DaemonThread();
        dt.setDaemon(true);
        dt.start();
    }
}
