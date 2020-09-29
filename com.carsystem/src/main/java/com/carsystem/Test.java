package com.carsystem;

public class Test {
    public static void main(String[] args) {
        Thread t1 = new ThreadTest();
        Thread t2 = new ThreadTest();

        t1.start();
        t2.start();
    }
}


class ThreadTest extends Thread{
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count++;
            System.out.println(count);
        }
    }
}
