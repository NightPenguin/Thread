package com.learn._04threadApi;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {

    public static void main(String[] args) throws InterruptedException {
        /*Thread t1 = new Thread(() ->{
                try {
                    System.out.println("t1 is running");
                    Thread.sleep(10_000);
                    System.out.println("t1 is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        t1.start();
        t1.join(100);

        Optional.of("All of tasks finish done.").ifPresent(System.out::println);
        IntStream.range(1, 1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));*/
        // start httpServer
        Thread.currentThread().join();
    }
}
