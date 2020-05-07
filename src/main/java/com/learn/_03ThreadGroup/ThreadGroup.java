package com.learn._03ThreadGroup;

import java.util.Arrays;

public class ThreadGroup {
    public static void main(String[] args) {
        // 1、若构造线程对象未传入ThreadGroup，则Thread 默认获取父线程的THreadGroup
        // 此时，子线程和父线程就会在同一个ThreadGroup
//        System.out.println(Thread.currentThread().getName());

        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
//        System.out.println(t.getThreadGroup());
//        System.out.println(Thread.currentThread().getName());
//
//        System.out.println(Thread.currentThread().getThreadGroup().getName());

        java.lang.ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());  // 3
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

//        for(Thread temp: threads){
//            System.out.println(temp);
//        }
        // java8
        Arrays.asList(threads).forEach(System.out::println);
    }
}
