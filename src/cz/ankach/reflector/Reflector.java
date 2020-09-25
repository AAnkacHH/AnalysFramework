package cz.ankach.reflector;

import cz.ankach.application.Handler;
import cz.ankach.application.tester.Tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reflector {
    public Object createReaderClass(String className, String input, String solution){
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class);

            return constructor.newInstance(input, solution);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object createObject(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object createAppObject(String className, Handler handler, Tester tester) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(Handler.class, Tester.class);
            return constructor.newInstance(handler, tester);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
