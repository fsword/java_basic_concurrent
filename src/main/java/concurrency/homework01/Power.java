package concurrency.homework01;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 17:17:51
 */
public class Power {

    public void on() {
        synchronized (this){
            this.notifyAll();
            System.out.println("power on.");
        }
    }

    public void off() {
        final Power power = this;
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("power off.");
                    synchronized (power){
                        power.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
    }
}
