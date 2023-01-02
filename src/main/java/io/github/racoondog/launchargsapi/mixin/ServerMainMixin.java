package io.github.racoondog.launchargsapi.mixin;

import io.github.racoondog.launchargsapi.api.Events;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.SERVER)
@Mixin(Main.class)
public abstract class ServerMainMixin {
    @ModifyVariable(method = "main([Ljava/lang/String;)V", at = @At("STORE"))
    private static OptionParser hookOptionParser(OptionParser optionParser) {
        Events.CREATE_SPECS.invoker().createSpecs(optionParser);
        return optionParser;
    }

    @ModifyVariable(method = "main([Ljava/lang/String;)V", at = @At("STORE"))
    private static OptionSet hookOptionSet(OptionSet optionSet) {
        Events.PARSE_ARGS.invoker().parseArgs(optionSet);
        return optionSet;
    }
}