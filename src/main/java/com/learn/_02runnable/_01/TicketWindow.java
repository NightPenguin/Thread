package com.learn._02runnable._01;

public class TicketWindow  extends Thread{
//  模拟银行
    private final int Max = 50;

//    独立的变量
//    private int index = 1;
    private static  int index = 1;

//    柜台名称
    private final String name;

    public TicketWindow(String name){
        this.name = name;
    }

    @Override
    public void run(){
        while (index <= Max) {
            System.out.println("柜台: " + name + "当前的号码是：" + (index++));
        }
    }
}
