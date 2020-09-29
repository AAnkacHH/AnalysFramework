package cz.ankach.application.view;

import cz.ankach.application.entity.OutputArgument;

import java.util.List;

public interface CustomView {
    void print(List<OutputArgument> outputs);
    void printTimes(double [] times);
}
