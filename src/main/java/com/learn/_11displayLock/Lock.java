package com.learn._11displayLock;

import java.util.Collection;

/**
 * 显示锁
 */
public interface Lock {

    public static class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }

    /**
     * 可打断锁
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 一定时间后中断
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    void lock(long mills) throws InterruptedException,TimeOutException;

    void unlock();

    /**
     *  阻塞线程的集合
     * @return
     */
    Collection<Thread> getBlockedThread();

    /**
     * 阻塞线程的个数
     * @return
     */
    int getBlockedSize();
}
