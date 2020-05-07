package com.learn._04threadApi;

import java.text.SimpleDateFormat;

public class ThreadJoin3 {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Thread t1 = new Thread(new CaptureRunnable("M1",10000));
        Thread t2 = new Thread(new CaptureRunnable("M2",30000));
        Thread t3 = new Thread(new CaptureRunnable("M3",15000));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTime = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp is:%s, end timestamp is:%s\n",startTime,endTime);
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}

class CaptureRunnable implements Runnable {
    private String machinename;
    private long spendTime;

    public CaptureRunnable(String machinename, long spendTime) {
        this.machinename = machinename;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // do the really capture data
        try {
            Thread.sleep(spendTime);
            System.out.printf(machinename + " completed data capture at timestamp [%s] and successfully \n",System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machinename + "finash";
    }
}
