package com.learn._09producerANDconsumer.version1;

/**
 * 线程间无法通信
 */
public class ProducerConsumer {

    private int i = 1;

    final Object lock = new Object();

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();

        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    producerConsumer.produce();
                }
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    producerConsumer.consumer();
                }
            }
        }.start();
    }

    private void produce() {
        synchronized (lock) {
            System.out.println("P->" + (i++));
        }
    }

    private void consumer() {
        synchronized (lock) {

            System.out.println("C->" + i);

        }
    }
}
