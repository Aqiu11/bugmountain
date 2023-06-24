import java.util.concurrent.TimeUnit;

/**
 * ClassName: Book
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author AQiu
 * @Create 17/04/2023 22:29
 */
public class Book {
    final Object o = new Object();
    final Object o2 = new Object();
    public void m1(){
        synchronized (o){
            System.out.println("11111");
            synchronized (o2){
                System.out.println("22222");
            }
        }
    }
    public void m2(){
        synchronized (o2){
            System.out.println("22222");
        }
    }

    public static void main(String[] args) {
        final Object o = new Object();
        final Object o2 = new Object();
        final Object o3 = new Object();
        new Thread(()->{
            synchronized (o2){
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                System.out.println("2222");
            }
        }).start();
       new Thread(()->{
           synchronized (o){
               System.out.println("11111");
               synchronized (o2){
                   System.out.println("22222");
               }
           }
       }).start();
    }
}
