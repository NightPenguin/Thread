package com.learn._05interrupt;

public class ThreadInterruptStatic {

    private  static final Object MONITOR = new Object();

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while (true){
                synchronized(MONITOR){
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }
            }
        });
    }
}
