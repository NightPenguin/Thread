package com.learn._09producerANDconsumer.version2;

import java.util.stream.Stream;

/**
 * synchronized 同步释放锁的时机
 * 1、当前线程的同步方法、代码块执行结束的时候释放
 *
 * 2、当前线程在同步方法、同步代码块中遇到break 、 return 终于该代码块或者方法的时候释放。
 *
 * 3、出现未处理的error或者exception导致异常结束的时候释放
 *
 * 4、程序执行了 同步对象 wait 方法 ，当前线程暂停，释放锁
 */
public class ProducerConsumer {
    // 表示产品
    private int i  = 0;

    // 定义锁对象
    final private Object LOCK = new Object();

    // 定义一个信号
    private volatile boolean isProduce = false;

    public void prodece(){
        synchronized (LOCK){
            if(isProduce){
                try {
                    // 生产完之后等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                // 生产完唤醒消费
                i++;
                System.out.println("P->" + i);
                // TODO
                LOCK.notify();
                isProduce = true;
            }
        }
    }

    public void consumer(){
        synchronized (LOCK){
            if(isProduce){
                // 消费完之后唤醒生产
                System.out.println("C->" + i);
                LOCK.notify();
                isProduce = false;
            }else{
                try {
                    // 没有产品时，等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        new Thread(){
            @Override
            public void run() {
                while(true){
                    producerConsumer.prodece();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    producerConsumer.consumer();
                }
            }
        }.start();
    }
}
