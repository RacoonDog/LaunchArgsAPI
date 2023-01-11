package io.github.racoondog.launchargsapi.mixin;

import io.github.racoondog.launchargsapi.api.ArgsListener;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.SERVER)
@Mixin(Main.class)
public abstract class ServerMainMixin {
    @ModifyVariable(method = "main([Ljava/lang/String;)V", at = @At("STORE"))
    private static OptionParser hookOptionParser(OptionParser optionParser) {
        for (var entrypointContainer : FabricLoader.getInstance().getEntrypointContainers("argsListener", ArgsListener.class)) {
            entrypointContainer.getEntrypoint().createSpecs(optionParser);
        }
        return optionParser;
    }

    @ModifyVariable(method = "main([Ljava/lang/String;)V", at = @At("STORE"))
    private static OptionSet hookOptionSet(OptionSet optionSet) {
        for (var entrypointContainer : FabricLoader.getInstance().getEntrypointContainers("argsListener", ArgsListener.class)) {
            entrypointContainer.getEntrypoint().parseArgs(optionSet);
        }
        return optionSet;
    }
}
