package com.learn._07synchronized._03function;

public class Bank3 {

    public static void main(String[] args) {
        SynchronizedRunnble synchronizedRunnble = new SynchronizedRunnble();

        Thread t1 = new Thread(synchronizedRunnble,"一号窗口");
        Thread t2 = new Thread(synchronizedRunnble,"二号窗口");
        Thread t3 = new Thread(synchronizedRunnble,"三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
