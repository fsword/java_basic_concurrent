package concurrency.homework01;

import java.util.Date;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 17:12:29
 */
public class Workflow extends Thread{
    private Workflow waitFor;
    private int pause;
    private Power lock;

    public Workflow(String name, Power power) {
        super(name);
        lock = power;
    }

    public void run() {
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(waitFor!=null && waitFor.isAlive()){
            try {
                waitFor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i<10; i++){
            if(pause > 0){
                try {
                    System.out.println(new Date() + " " + getName() + " is paused for " + pause + " ms");
                    Thread.sleep(pause);
                    pause = 0;
                } catch (InterruptedException e) {e.printStackTrace();}
            }else{
                try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
            }
            System.out.println(new Date() + " " + getName() + " make product: " + i);
        }
    }

    public void waitFor(Workflow workflow) {
        waitFor = workflow;
    }

    public void pause(int millisecond) {
        this.pause = millisecond;
    }
}
