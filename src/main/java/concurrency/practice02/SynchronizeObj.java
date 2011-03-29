package concurrency.practice02;

/**
 * User: john
 * Date: 11-3-28
 * Time: 上午12:50
 */
public class SynchronizeObj {

    public synchronized static void test1(){
        System.out.println("test1:"+System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void test2() {
        System.out.println("test2:"+System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("class synchronize");
        for(int i=0;i<5;i++){
            new Thread(){
                @Override
                public void run() {
                    SynchronizeObj.test1();
                }
            }.start();
        }

        System.out.println("instance synchronize");
        final SynchronizeObj sc = new SynchronizeObj();
        for(int i=0;i<5;i++){
            new Thread(){
                @Override
                public void run() {
                    sc.test2();
                }
            }.start();
        }
    }
}
