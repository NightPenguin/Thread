package com.learn._11displayLock;

/**
 * synchronezed 无法被打断
 */
public class SynchronizedProblem {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        }.start();

        Thread t2 = new Thread(){
            @Override
            public void run(){
                SynchronizedProblem.run();
            }
        };
        t2.start();
        try {
            Thread.sleep(2000);
            t2.interrupt();
            System.out.println(t2.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized static void run(){
        System.out.println(Thread.currentThread());
        while (true){

        }
    }
}
