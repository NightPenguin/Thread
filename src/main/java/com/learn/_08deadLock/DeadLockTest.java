package com.learn._08deadLock;

/**
 * 使用jps  和 jstack 查看死锁原因
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(){
            @Override
            public void run() {
                while(true){
                    deadLock.m1();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    otherService.s2();
                }
            }
        }.start();
    }
}
