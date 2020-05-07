package com.learn._06gracefulstop;

public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        // 调用执行线程
        service.execute(()->{
//            // load a very heavy resource
//            while(true){
//
//            }
            // 当5秒内执行完线程
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000);
        long end = System.currentTimeMillis();

        System.out.println(end-start);
    }
}
