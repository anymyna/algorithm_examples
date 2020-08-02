package com.example.laboratory.algorithm;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



//多线程交替打印
public class AlternativePrint {

    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int number = 1;

    public void test(){
        new Thread("A"){
            @Override
            public void run() {
                for (int i= 0; i < 20; i++)
                    loopA();
            }
        }.start();

        new Thread("B"){
            @Override
            public void run() {
                for (int i= 0; i < 20; i++)
                    loopB();
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                for (int i= 0; i < 20; i++)
                    loopC();
            }
        }.start();
    }


    public void loopA(){
        lock.lock();
        try {
            if (number != 1){
                conditionA.await();
            }

            for (int i = 1; i <= 5; i++){
                Log.e("duck",""+Thread.currentThread().getName() + "     " + i);
            }

            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(){
        lock.lock();
        try {
            if (number != 2){
                conditionB.await();
            }

            for (int i = 1; i <= 5; i++){
                Log.e("duck",""+Thread.currentThread().getName() + "     " + i);
            }

            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(){
        lock.lock();
        try {
            if (number != 3){
                conditionC.await();
            }

            for (int i = 1; i <= 5; i++){
                Log.e("duck","  "+Thread.currentThread().getName() + "     " + i);
            }

            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
