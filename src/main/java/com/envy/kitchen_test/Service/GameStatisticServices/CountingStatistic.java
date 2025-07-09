package com.envy.kitchen_test.Service.GameStatisticServices;

import javafx.scene.control.Label;

import java.util.concurrent.atomic.AtomicInteger;

public class CountingStatistic {
    private static CountingStatistic instance;

    public static CountingStatistic getInstance() {
        if (instance == null) {
            instance = new CountingStatistic();
        }
        return instance;
    }

    public void initialize(Label counter) {
        this.counter = counter;
    }

    private Label counter;

    private final AtomicInteger count = new AtomicInteger(0);

    public synchronized void increment() {
        count.incrementAndGet();
    }

    public synchronized void updateCounter() {
        counter.setText(String.valueOf(count.get()));
    }
}
