package com.example.laboratory.algorithm;

import android.util.Log;



public class FooBar {

    private Object lock = new Object();
    boolean start = true;

    public void test(final  int num)
    {



            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0;i<num;i++)
                    {
                        printFoo();
                    }
                }
            }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j = 0;j<num;j++)
                {
                    printBar();
                }

            }
        }).start();


        }



    public void printFoo()
    {

        synchronized (lock)
        {
            try {


                if(start)
                {
                    Log.e("duck","print Foo ");
                    start = false;
                }
                lock.notify();
                lock.wait();


            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e("duck","print Foo IException" );
            }

        }
    }


    public void printBar()
    {
        synchronized (lock)
        {
            try {

                if(!start)
                {
                    Log.e("duck","print Bar");
                    start = true;
                }

                lock.notify();
                lock.wait();




            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e("duck","print Bar IException" );
            }
        }

    }

}
