package com.exercise.thread;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlphabetThread extends Thread{

    public static List<Character> list = new ArrayList<Character>(){
        {
            //利用ascall循环得到A-Z的码
            //加入到list中
            for (int j = 65; j < 91; j++) {
                add((char) j);
            }
            ;
        }
    };

    @Override
    public void run() {

        synchronized (Main.lock){
            for (int i = 0;i< list.size();i++) {
                System.out.println(list.get(i));
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
