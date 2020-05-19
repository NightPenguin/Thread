package com.learn._11displayLock;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {

    public static void main(String[] args) {
        final BooleanLock booleanLock =  new BooleanLock();
        Stream.of("T1","T2","T3","T4")
                .forEach(name->
                        new Thread(()->{
                            try {
                                booleanLock.lock(5_000
                                );
                                Optional.of(Thread.currentThread().getName() + " have the lock monitor")
                                        .ifPresent(System.out::println);
                                work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }catch (Lock.TimeOutException e){
                                Optional.of(Thread.currentThread().getName()+" time out")
                                        .ifPresent(System.out::println);
                            }finally {
                                booleanLock.unlock();
                            }
                        },name).start()
                );

        Stream.of("m1","m2")
                .forEach(name->
                        new Thread(()->{
                            try {
                                booleanLock.lock(15_000);
                                Optional.of(Thread.currentThread().getName() + " have the lock monitor")
                                        .ifPresent(System.out::println);
                                work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }catch (Lock.TimeOutException e){
                                Optional.of(Thread.currentThread().getName()+" time out")
                                        .ifPresent(System.out::println);
                            }finally {
                                booleanLock.unlock();
                            }
                        },name).start()
                );

        // 锁只能当前锁对象释放
        try {
            Thread.sleep(100);
            booleanLock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void work(){
        try {
            Optional.of(Thread.currentThread().getName() + " is working...")
                    .ifPresent(System.out::println);
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
