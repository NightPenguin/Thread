package com.learn._03ThreadGroup;

public class ThreadGroup2 {

    private static int counter = 1;
    public static void main(String[] args) {

        Thread t1 = new Thread(null,new Runnable() {
            @Override
            public void run(){
                try{
                    add(1);
                }catch (Error e){
                    System.out.println(counter);
                }
            }

            private void add(int i){
                counter++;
                add(i+1);
            }
        },"test",1<<24);
        t1.start();


    }
}
