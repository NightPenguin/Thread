package com.learn._05interrupt;

public class Join {
    private  static final Object MONITOR = new Object();

    public static void main(String[] args) {
        //静态Interrupt
        /*Thread t = new Thread(()->{
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
        t.start();*/

        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){

                }
            }
        };
        t.start();

        Thread main = Thread.currentThread();
        Thread  t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 打断main
                main.interrupt();
                System.out.println("Interrupt");
            }
        };
        t2.start();

        try {
            t.join();  // ，main 线程jion
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
