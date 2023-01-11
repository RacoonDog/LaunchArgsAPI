package io.github.racoondog.launchargsapi.api;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public interface ArgsListener {
    void createSpecs(OptionParser optionParser);
    void parseArgs(OptionSet optionSet);
}
