package cz.ankach.application;

import cz.ankach.application.entity.OutputArgument;

import java.util.List;

public interface Handler {
    public List<OutputArgument> handle();
}
