package io.github.racoondog.test;

import com.mojang.logging.LogUtils;
import io.github.racoondog.launchargsapi.api.ArgsListener;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.slf4j.Logger;
public class LaunchArgsTest implements ArgsListener {
    private static final Logger LOG = LogUtils.getLogger();
    private OptionSpec<Void> optionSpec;

    @Override
    public void createSpecs(OptionParser optionParser) {
        optionSpec = optionParser.accepts("someArg");
    }

    @Override
    public void parseArgs(OptionSet optionSet) {
        if (optionSet.has(optionSpec)) LOG.info("Found argument!");
    }
}
