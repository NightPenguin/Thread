package com.learn._03ThreadGroup;

public class CreateThead {
    public static void main(String[] args) {
        //Thread() 1、创建线程对象，默认有线程名，以Thread-开头，从0开始计数
        Thread t = new Thread();
        t.start();
        Thread t2 = new Thread(){
            @Override
            public void run(){
                System.out.println("##############");
            }
        };
        t2.start();
        System.out.println(t.getName());
        System.out.println(t2.getName());

        Thread t3 = new Thread("Myname");
        Thread t4 = new Thread(() ->{
            System.out.println("Runnable....");
        });
        System.out.println(t3.getName());
        System.out.println(t4.getName());

        Thread t5 = new Thread(() ->{
            System.out.println("Runnable...." + Thread.currentThread().getName());
        });
        t5.start();
    }
}
