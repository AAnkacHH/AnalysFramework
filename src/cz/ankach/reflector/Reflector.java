package cz.ankach.reflector;

import cz.ankach.application.Application;
import cz.ankach.application.Handler;
import cz.ankach.application.entity.SolutionArgument;
import cz.ankach.application.reader.InputReader;
import cz.ankach.application.tester.Tester;
import cz.ankach.application.view.CustomView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/** This class is responsible for loading custom classes */
public class Reflector {
    /**
     * Method creates reader class.
     * @param className - class name as a string.
     * @param input - input file name.
     * @param solution - solution file name.
     * @return InputReader
     * */
    public InputReader createReaderClass(String className, String input, String solution){
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class);

            return (InputReader) constructor.newInstance(input, solution);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method creates Tester class.
     * @param className - class name as a string.
     * @param enable - user option, we can enable or disable testing.
     * @param solutions - solution arguments loaded from file with solutions.
     * @return Tester
     * */
    public Tester createTester(String className, boolean enable, Map<Integer, SolutionArgument> solutions) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(boolean.class, Map.class);
            return (Tester) constructor.newInstance(enable, solutions);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method creates Handler class.
     * @param className - class name as a string.
     * @return Handler
     * */
    public Handler createHandler(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            return (Handler) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method creates CustomView class.
     * @param className - class name as a string.
     * @return CustomView
     * */
    public CustomView createView(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            return (CustomView) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method creates Application class.
     * @param className - class name as a string.
     * @param handler - user class witch represent algorithm for solving current problem.
     * @return Application
     * */
    public Application createAppObject(String className, Handler handler) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(Handler.class);
            return (Application) constructor.newInstance(handler);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
