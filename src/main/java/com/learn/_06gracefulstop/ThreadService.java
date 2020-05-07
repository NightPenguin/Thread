package com.learn._06gracefulstop;

/**
 * 通过执行线程里面设置守护线程
 */
public class ThreadService {

    // 定义一个执行线程
    private  Thread executeThread;
    // 线程是否执行完毕
    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {

                Thread  runner = new Thread(task);
                // 设置守护线程
                runner.setDaemon(true);
                //执行任务
                runner.start();
                try {
                    // 防止守护线程执行前被关闭
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 执行线程开始启动
        executeThread.start();
    }

    /**
     * 线程执行超时则关闭
     * @param mills
     */
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        while(!finished){
            if((System.currentTimeMillis() - currentTime >= mills)){
                System.out.println("任务超时，需要结束！");
                // 打断执行线程、则相应关闭守护线程
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }

        finished = false;
    }
}
