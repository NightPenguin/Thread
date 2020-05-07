package com.learn._02runnable._02New;

//用Runnable 接口将线程的逻辑执行单元和线程控制分离开来
public class TicketWindowRunnable implements Runnable {
    //    访问业务数据为同一份，有线程安全问题
    private int index = 1;

    private final static int MAX = 50;

    @Override
    public void run() {

        while (index <= MAX) {
            System.out.println(Thread.currentThread() + "号码为：" + (index++));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
