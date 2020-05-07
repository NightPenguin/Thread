package com.learn._04threadApi;

public class DeamThread {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " Running...");
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " Down...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        System.out.println(Thread.currentThread().getName());
    }
}
