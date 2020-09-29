package cz.ankach.analyzer;

import cz.ankach.application.Application;
import cz.ankach.application.entity.InputArgument;
import cz.ankach.application.entity.OutputArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.application.tester.Tester;
import cz.ankach.application.view.CustomView;
import cz.ankach.application.view.TesterView;
import cz.ankach.configuration.Config;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

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
     * This method starts solving a problem using custom handler and configuration.
     * */
    public void start() {
        int size = Integer.parseInt(config.getProperty(Config.PROPERTY_TIMES).trim());
        double [] runningTimes = new double [size];

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        for (int i = 0; i < size; i++) {
            List<InputArgument> inputs = reader.readInput();

            long start = threadMXBean.getCurrentThreadCpuTime();
            List<OutputArgument> outputs = application.run(inputs);
            long end = threadMXBean.getCurrentThreadCpuTime();
            runningTimes[i] = ((double) (end - start))/1000000000;
            if (tester.isEnabled()) {
                List<String> errors = tester.testAll(outputs);
                testerView.showErrors(errors);
            }
            for (CustomView view : views) {
                view.print(outputs);
            }
        }
        for (CustomView view : views) {
            view.printTimes(runningTimes);
        }
        double sum = 0;
        for (int i = 0; i < size; i++) {
            if (i>5) sum +=runningTimes[i];
            System.out.println(i+": "+runningTimes[i]);
        }
        System.out.println("Total: "+ sum/(size-5));
    }
}
