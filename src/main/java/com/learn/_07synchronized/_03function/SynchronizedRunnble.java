package com.learn._07synchronized._03function;

public class SynchronizedRunnble implements  Runnable{
    //    访问业务数据为同一份，有线程安全问题
    private int index = 1;
    // readonly
    private final static int MAX = 500;
    // 同步方法, 锁为this
/*    @Override
    public synchronized void run() {
        // 只有单个线程运行，浪费资源
        while (true) {
            if( index > MAX){
                break;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( Thread.currentThread() + " 的号码为:" + (index++));
        }
    }*/

    @Override
    public void run() {
        while(true){
            if (ticket()){
                break;
            }
        }
    }

    private synchronized  boolean ticket(){
        if(index > MAX)
            return true;

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1. get field index
        // 2. index = index + 1
        // 3. put field index
        System.out.println( Thread.currentThread() + " 的号码为:" + (index++));
        return false;
    }

    private boolean ticket2(){
        synchronized (this){
            if(index > MAX)
                return true;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 1. get field index
            // 2. index = index + 1
            // 3. put field index
            System.out.println( Thread.currentThread() + " 的号码为:" + (index++));
            return false;
        }
    }
}
