package com.learn._09producerANDconsumer.versionMultiThread;

import com.learn._09producerANDconsumer.version2.ProducerConsumer;
import sun.misc.Lock;

import java.util.stream.Stream;

public class MutilThread {
    // 表示产品
    private int i = 0;

    // 定义锁对象
    final private Object LOCK = new Object();

    // 定义一个信号,true 为生产、 false 为消费
    private volatile boolean isProduce = false;

    public void prodece() {
        synchronized (LOCK) {
            while(isProduce){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 生产完唤醒消费
            i++;
            System.out.println("PP->" + i);
            // TODO
            LOCK.notifyAll();
            isProduce = true;
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            while(!isProduce){
                try {
                    // 没有产品时，等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 消费完之后唤醒生产
            System.out.println("CC->" + i);
            LOCK.notifyAll();
            isProduce = false;
        }
    }

    public static void main(String[] args) {

        ProducerConsumer producerConsumer = new ProducerConsumer();
        Stream.of("P1", "P2").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        producerConsumer.prodece();
                    }
                }
            }.start();
        });

        Stream.of("C1", "C2").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        producerConsumer.consumer();
                    }
                }
            }.start();
        });


    }
}
