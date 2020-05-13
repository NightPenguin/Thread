package com.learn._09producerANDconsumer.versionMultiThread;

import com.learn._09producerANDconsumer.version2.ProducerConsumer;

import java.util.stream.Stream;

public class Temp {
    // 表示产品
    private int i = 0;

    // 定义锁对象
    final private Object LOCK = new Object();

    // 定义一个信号
    private volatile boolean isProduce = false;

    public void prodece() {
        synchronized (LOCK) {
            if (isProduce) {
                try {
                    // 生产完之后等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 生产完唤醒消费
                i++;
                System.out.println("PP->" + i);
                // TODO
                LOCK.notify();
                isProduce = true;
            }
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            if (isProduce) {
                // 消费完之后唤醒生产
                System.out.println("CC->" + i);
                LOCK.notify();
                isProduce = false;
            } else {
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
