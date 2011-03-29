package concurrency.usage04;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 20:54:52
 */
public class ThreadWithUnboxDecompile {

    public static void main(String args[])
    {
        for(int i = 0; i < 5; i++)
            synchronized(counter)
            {
                Integer integer1 = counter;
                Integer integer2 = counter = Integer.valueOf(counter.intValue() - 1);
                Integer _tmp = integer1;
                System.out.println((new StringBuilder()).append("counter is:").append(counter).toString());
                if(counter.intValue() == 0)
                    try
                    {
                        counter.notifyAll();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                System.out.println("deal!");
            }

    }

    private static Integer counter = Integer.valueOf(5);
}