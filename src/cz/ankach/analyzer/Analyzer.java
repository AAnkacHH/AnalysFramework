package cz.ankach.analyzer;

import cz.ankach.application.Application;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.application.tester.Tester;
import cz.ankach.application.view.CustomView;
import cz.ankach.application.view.TesterView;
import cz.ankach.configuration.Config;
import cz.ankach.thread.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Analyzer {

    private final Application application;
    private final InputReader reader;
    private final Config config;
    private final Tester tester;
    private final TesterView testerView;
    private final List<CustomView> views;

    public Analyzer(
            Application application,
            InputReader reader,
            Tester tester,
            Config config,
            TesterView testerView,
            List<CustomView> views
    ) {
        this.application = application;
        this.reader = reader;
        this.config = config;
        this.tester = tester;
        this.testerView = testerView;
        this.views = views;
    }

    /**
     * This method creates a thread pool and runs tasks.
     * */
    public void start() {
        int numOfThreads = Integer.parseInt(config.getProperty(Config.PROPERTY_THREADS).trim());
        int times = Integer.parseInt(config.getProperty(Config.PROPERTY_TIMES).trim());
        double [] runningTimes = new double [times];
        ExecutorService pool = Executors.newFixedThreadPool(numOfThreads);
        List<Runnable> tasks = createRunners(runningTimes);
        for (Runnable r: tasks) {
            pool.execute(r);
        }
        this.awaitTerminationAfterShutdown(pool);

        for (CustomView view : views) {
            view.printTimes(runningTimes);
        }
    }

    private List<Runnable> createRunners(double [] runningTimes) {
        int times = Integer.parseInt(config.getProperty(Config.PROPERTY_TIMES));
        int scale = Integer.parseInt(config.getProperty(Config.PROPERTY_SCALE));
        List<Runnable> runners = new ArrayList<>(times);

        List<InputArgument> inputs = reader.readInput();
        runners.add(new Task(application, inputs, tester, testerView, views, runningTimes, 0, scale));
        for (int i = 1; i < times; i++) {
            inputs = reader.readInput();
            Application app = application.copy();

            runners.add(new Task(app, inputs, tester, testerView, views, runningTimes, i, scale));
        }

        return runners;
    }

    /**
     * This blocks the thread until all tasks complete their execution or the specified timeout is reached.
     *
     * See <a href="https://www.baeldung.com/java-executor-wait-for-threads">https://www.baeldung.com</a>
     * */
    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(10, TimeUnit.HOURS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
