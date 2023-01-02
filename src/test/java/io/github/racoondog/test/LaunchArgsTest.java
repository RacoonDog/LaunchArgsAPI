package io.github.racoondog.test;

import com.mojang.logging.LogUtils;
import io.github.racoondog.launchargsapi.api.Events;
import joptsimple.OptionSpecBuilder;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

public class LaunchArgsTest implements PreLaunchEntrypoint {
    Logger LOG = LogUtils.getLogger();

    @Override
    public void onPreLaunch() {
        AtomicReference<OptionSpecBuilder> optionSpec = new AtomicReference<>();

        Events.CREATE_SPECS.register(optionParser -> optionSpec.set(optionParser.accepts("someArg")));

        Events.PARSE_ARGS.register(optionSet -> {
            if (optionSet.has(optionSpec.get())) LOG.info("Found argument!");
        });
    }
}
