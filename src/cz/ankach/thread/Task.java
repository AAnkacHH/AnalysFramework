package cz.ankach.thread;

import cz.ankach.application.Application;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.tester.Tester;
import cz.ankach.application.view.CustomView;
import cz.ankach.application.view.TesterView;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

public class Task implements Runnable {

    private static int idGenerator = 0;
    private final int id;
    private final Application application;
    private final List<InputArgument> arguments;
    private final Tester tester;
    private final TesterView testerView;
    private final List<CustomView> views;
    private final double [] runningTimes;
    private final int currentIndex;
    private final int scale;

    public Task(
            Application application,
            List<InputArgument> arguments,
            Tester tester, TesterView testerView,
            List<CustomView> views,
            double[] runningTimes,
            int currentIndex,
            int scale
    ) {
        this.id = idGenerator++;
        this.application = application;
        this.arguments = arguments;
        this.tester = tester;
        this.testerView = testerView;
        this.views = views;
        this.runningTimes = runningTimes;
        this.currentIndex = currentIndex;
        this.scale = scale;
    }

    @Override
    public void run() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        long start = threadMXBean.getCurrentThreadCpuTime();
        List<OutputArgument> outputs = application.run(arguments);
        long end = threadMXBean.getCurrentThreadCpuTime();
        runningTimes[currentIndex] = ((double) (end - start))/scale;

        if (tester.isEnabled()) {
            List<String> errors = tester.testAll(outputs);
            testerView.showErrors(errors);
        }
        for (CustomView view : views) {
            view.print(outputs);
        }
        System.out.println("============================| Task: " + id + " was ended. |=================");
    }
}
