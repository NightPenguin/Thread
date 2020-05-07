package com.learn._02runnable._02New;

public class Bank2 {
    public final static int MAX = 50;
    public static int index = 1;
    public static void main(String[] args) {
       /* final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();

        Thread t1 = new Thread(ticketWindowRunnable,"一号窗口");
        Thread t2 = new Thread(ticketWindowRunnable,"二号窗口");
        Thread t3 = new Thread(ticketWindowRunnable,"三号窗口");

        t1.start();
        t2.start();
        t3.start();*/

        Runnable ticketWindowRunnable = () ->{

            while (index <= MAX) {
                System.out.println(Thread.currentThread() + "号码为：" + (index++));
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        Thread t1 = new Thread(ticketWindowRunnable,"一号窗口");
        Thread t2 = new Thread(ticketWindowRunnable,"二号窗口");
        Thread t3 = new Thread(ticketWindowRunnable,"三号窗口");

        t1.start();
        t2.start();
        t3.start();
    }
}
