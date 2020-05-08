package com.learn._07synchronized._01;

//用Runnable 接口将线程的逻辑执行单元和线程控制分离开来
public class TicketWindowRunnable implements Runnable {
    //    访问业务数据为同一份，有线程安全问题
    private int index = 1;

    private final static int MAX = 500;
    // 添加锁，保证线程安全问题
    private final Object MONITOR = new Object();

    @Override
    public void run() {

        while (true) {
            // 同步代码块
            synchronized (MONITOR){
                if( index > MAX){
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " 的号码是" + (index++));
            }
        }
    }
}
