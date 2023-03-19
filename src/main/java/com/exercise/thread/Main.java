package com.exercise.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static Object lock = new Object();

    //门栓，控制线程运行顺序
    private static CountDownLatch latch = new CountDownLatch(1);

    public static List<Character> list = new ArrayList<Character>() {
        {
            //利用ascall循环得到A-Z的码
            //加入到list中
            for (int j = 65; j < 91; j++) {
                add((char) j);
            }
        }
    };


    public static void main(String[] args) {
        //常规notity、wait方式
        //general();
        //LockSupport方式
        //lockSupport();
        //ReentrantlLock-Condition
        reentrantLockCondition();

    }

    /**
     * 常规方式.
     *
     * @return
     * @Author chensy
     * @Description
     * @Modified by :
     * @params
     */
    public static void general() {

        Thread t1 = new Thread(() -> {
            try {
                //添加门栓，当前线程阻塞
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (Main.lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.println(i);
                    //打开门栓
                    latch.countDown();
                    Main.lock.notify();
                    try {
                        Main.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }

        }, "t2");

        t1.start();
        t2.start();

    }

    /**
     * LockSupport.
     * @Author chensy
     * @Description
     * @Modified by :
     * @params
     * @return
     */
    public static Thread a = null;
    public static Thread b = null;
    public static void lockSupport() {

        a = new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                //当前线程上锁
                LockSupport.park();
                System.out.println(list.get(i));
                //唤醒t2
                LockSupport.unpark(b);
            }
        }, "t1");

        b = new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);
                //唤醒t1线程
                LockSupport.unpark(a);
                //当前线程上锁
                LockSupport.park();

            }
        }, "t2");

        a.start();
        b.start();
    }

    /**
     * ReentrantLock-Condition.
     * @Author chensy
     * @Description
     * @Modified by :
     * @params
     * @return
     */
    public static void reentrantLockCondition() {

        Lock lock = new ReentrantLock();
        //定义条件1的锁
        Condition condition1 = lock.newCondition();
        //定义条件2的锁
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            //获取锁
            lock.lock();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                //叫醒条件2的锁
                condition2.signal();
                //条件1释放锁，等待
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //叫醒条件2的锁
            condition2.signal();
            //释放锁
            lock.unlock();
        },"t1").start();

        new Thread(() -> {
            //获取锁
            lock.lock();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);
                //叫醒条件1的锁
                condition1.signal();
                //条件2释放锁，等待
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //叫醒条件1的锁
            condition1.signal();

            lock.unlock();
        },"t2").start();

    }

}
