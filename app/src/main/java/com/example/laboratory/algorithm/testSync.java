package com.example.laboratory.algorithm;

import android.util.Log;

public class testSync {

    private static int count = 0;
    private final static Object lock = new Object();
    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count < 10) {
                synchronized (lock) {

                    Log.e("duck",""+Thread.currentThread().getName() + "     " );

                    count++;
                    lock.notifyAll();
                    try {

                        if (count <  10) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    void test()
    {
        Thread a = new Thread(new TurningRunner(), "偶数");
        Thread b = new Thread(new TurningRunner(), "奇数");
        a.start();
        b.start();
    }

}
