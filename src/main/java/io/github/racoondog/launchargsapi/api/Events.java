package io.github.racoondog.launchargsapi.api;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class Events {
    public static final Event<CreateSpecs> CREATE_SPECS = EventFactory.createArrayBacked(CreateSpecs.class, callbacks -> optionParser -> {
        for (CreateSpecs callback : callbacks) {
            callback.createSpecs(optionParser);
        }
    });

    public static final Event<ParseArgs> PARSE_ARGS = EventFactory.createArrayBacked(ParseArgs.class, callbacks -> optionSet -> {
        for (ParseArgs callback : callbacks) {
            callback.parseArgs(optionSet);
        }
    });

    @FunctionalInterface
    public interface CreateSpecs {
        void createSpecs(OptionParser optionParser);
    }

    @FunctionalInterface
    public interface ParseArgs {
        void parseArgs(OptionSet optionSet);
    }
}
