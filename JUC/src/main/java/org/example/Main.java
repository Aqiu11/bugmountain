package org.example;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ${NAME}
 * Package: org.example
 * Description:
 *
 * @Author AQiu
 * @Create ${DATE} ${TIME}
 */


// 两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z，要求用线程间通信
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        map.get(12);
//        print print = new print();
//        new Thread(()->{
//            print.print1();
//        }).start();
//        new Thread(()->{
//            print.print2();
//        }).start();
//        FutureTask<String> voidFutureTask = new FutureTask<String>(()->{
//            System.out.println("1");
//            return "hehe";
//        });
//        new Thread(voidFutureTask).start();
//        System.out.println(voidFutureTask.get());
//
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 12L, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(3), new ThreadPoolExecutor.AbortPolicy());
//
//        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>(){
//            @Override
//            protected Integer initialValue() {
//                return 0;
//            }
//        };
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();

    }

}

class print{
    private  int printNum = 0;
    private  int printZiMu = 65;
    private  int flag = 1; // 1表示打印数字 2 表示打印字母
    private Lock lock = new ReentrantLock();
    private Condition cN = lock.newCondition();
    private Condition cZ = lock.newCondition();
    public  void print1() {

        try {
            lock.lock();
           while (true){
                while (flag == 2){
                    cN.await();
                }
                if (printNum == 52){
                    flag =2;
                    cZ.signal();
                    return;
                }else{
                    System.out.println(++printNum);
                    System.out.println(++printNum);
                    flag =2;
                    cZ.signal();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
    public  void print2()  {
        try {
            lock.lock();
           while (true){
                while (flag == 1){
                    cZ.await();
                }
                if(printZiMu == 91){
                    flag = 1;
                    cN.signal();
                    return;
                }else{
                    System.out.println((char)printZiMu++);
                    flag = 1;
                    cN.signal();
                }
           }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}