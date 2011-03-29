package concurrency.usage06;

/**
 * User: john
 * Date: Aug 9, 2010
 * Time: 8:06:20 PM
 */
public class ExceptionInThread extends Thread{


    public void run() {
    	try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        throw new RuntimeException("error");
    }

    public static void main(String[] args) {
        ExceptionInThread eit = new ExceptionInThread();
        try{
            eit.start();
        }catch (Exception e){
            System.out.println(e.getMessage());//Cannot get it
        }
    }
}
