package com.learn._07synchronized;

/**
 * this 锁
 */
public class ThisLockSynchronized {

    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();

        new Thread("T1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock{

    public synchronized void m1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 若锁不是this 则会同步输出
    public /*synchronized*/ void m2(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}