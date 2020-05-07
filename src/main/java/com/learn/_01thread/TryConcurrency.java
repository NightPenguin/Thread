package com.learn._01thread;

public class TryConcurrency {

    public static void main(String[] args) {

        new Thread("Read-Thead"){

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                readFromDataBase();
            }
        }.start();

        new Thread("Write-Thead"){

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                writeDataTofile();
            }
        }.start();

//        Thread t1 = new Thread("Custom-_01thread"){
//            public void run(){
//                for (int i = 0; i < 100; i++) {
//                    System.out.println("Task 1 >=" + i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t1.start();
//
//        for (int j = 0; j < 100; j++) {
//            System.out.println("Task 2 >=" + j);
//        }
//        readFromDataBase();
//        writeDataTofile();
    }

    private static void readFromDataBase(){
        try{
            System.out.println("Begin read data frm db");
            Thread.sleep(1000*5L);
            System.out.println("Read data done and start handle it");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("The data handle finish and successfully");
    }

    private static void writeDataTofile(){
        try{
            System.out.println("Begin write data frm db");
            Thread.sleep(1000*10L);
            System.out.println("Write data done and start handle it");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("The data handle finish and successfully");
    }

}
