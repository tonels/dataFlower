package tonels.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadUtil {

    protected static class ExcelWriter implements Runnable {

        private final CountDownLatch doneSignal;

        private int sheet;

        public ExcelWriter(CountDownLatch doneSignal, int page) {
            this.doneSignal = doneSignal;
            this.sheet = sheet;
        }

        public void run() {

        }

    }



}
