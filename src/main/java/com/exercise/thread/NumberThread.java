package com.exercise.thread;

import lombok.Data;

@Data
public class NumberThread extends Thread{

    @Override
    public void run() {
        synchronized (Main.lock){
            for (int i = 1;i<= 26;i++) {
                System.out.println(i);
                Main.lock.notify();
                try {
                    Main.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
