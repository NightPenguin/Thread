package com.learn._09producerANDconsumer.Action;

import javax.swing.text.html.Option;
import java.util.*;

public class CaptureService {

    final static private LinkedList<Control> CONTROLS = new LinkedList<>();
    private final static int MAX_WORKER = 5;

    public static void main(String[] args) {

        // 通过jdk1.8 创建多个线程
        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9","M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });
        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 可以防止空指针异常
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    /**
     * 创建线程
     *
     * @param name
     * @return
     */
    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("The worker [" + Thread.currentThread().getName() + "] Begin capture data")
                    .ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() >= MAX_WORKER){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 创建线程之后，counter 加1
                CONTROLS.addLast(new Control());
            }

            Optional.of("The worker [" + Thread.currentThread().getName()+"] is working...")
                    .ifPresent(System.out::println);
            //TODO
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // DONE，唤醒并创建线程
            synchronized (CONTROLS){
                Optional.of("The worker [" + Thread.currentThread().getName()+"] End capture data")
                        .ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);
    }

    // 控制作用
    private static class Control {
    }
}
