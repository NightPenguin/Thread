package com.learn._11displayLock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {

    /**
     * The initValue is true indicated the lock have be get.
     * false indicated the lock is free( other thread can get this).
     */
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            // 当锁被抢占，线程等待并添加到集合中
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }

        // 抢到锁
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;

        // 超时时间
        long waitedTime = mills;
        while (initValue) {
            if (hasRemaining <= 0) {
                throw new TimeOutException("Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);

            //当被唤醒时
            hasRemaining = endTime - System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " wait >>" + waitedTime + "ms");

            // 当被唤醒时，等待时间需要更新
            mills = hasRemaining;
        }

        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    /**
     * 只能锁对象释放锁
     */
    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            Optional.of(Thread.currentThread() + " release the lock monitor.")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
