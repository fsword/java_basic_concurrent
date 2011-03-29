package concurrency.homework01;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 17:11:27
 */
public class Main {
    public static void main(String[] args) {
        Power power = new Power();
        power.on();

        Workflow p1 = new Workflow("p1",power);
        Workflow p2 = new Workflow("p2",power);
        Workflow p3 = new Workflow("p3",power);
        Workflow p4 = new Workflow("p4",power);

        p2.waitFor(p1);

        p1.start(); p2.start(); p3.start(); p4.start();

        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}

        power.off();

        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}

        power.on();

        try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

        p3.pause(1000);
    }
}
