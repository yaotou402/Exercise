package com.exercise.thread;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Object lock = new Object();

    public static void main(String[] args) {

        Thread a = new AlphabetThread();
        Thread b = new NumberThread();

        a.start();
        b.start();

    }
}
