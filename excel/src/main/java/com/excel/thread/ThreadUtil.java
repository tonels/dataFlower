package com.excel.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadUtil {

    public static class ExcelWriter implements Runnable {

        private final CountDownLatch doneSignal;

        private int sheet;

        public ExcelWriter(CountDownLatch doneSignal, int page) {
            this.doneSignal = doneSignal;
            this.sheet = sheet;
        }

        @Override
        public void run() {


        }
    }



}
