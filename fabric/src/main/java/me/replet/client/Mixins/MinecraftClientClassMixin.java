package me.replet.client.Mixins;

import me.replet.client.ModLoaders;
import me.replet.client.crashui.GuiProblemScreen;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.Bootstrap;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

@Mixin(MinecraftClient.class)
@SuppressWarnings({"StaticVariableMayNotBeInitialized", "MixinAnnotationTarget", "UnresolvedMixinReference", "InvalidInjectorMethodSignature"})
public abstract class MinecraftClientClassMixin extends ReentrantThreadExecutor<Runnable> {

    @Shadow
    @Nullable
    private Supplier<CrashReport> crashReportSupplier;

    @Shadow protected abstract void printCrashReport();

    public MinecraftClientClassMixin(String string) {
        super(string);
    }

/*
    @Inject(method = "setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"), cancellable = true)
    private void setScreenDontResetCrashScreen(Screen screen, CallbackInfo ci) {
        if (this.crashReport!=null && !(screen instanceof GuiProblemScreen)) ci.cancel();
    }

 */
    @Inject(method = "run()V", at = @At("HEAD"))
    private void beforeRun(CallbackInfo ci) {
        if (this.crashReport!=null)  MinecraftClient.getInstance().setScreen(new GuiProblemScreen());

    }

    @ModifyArg(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;printCrashReport(Lnet/minecraft/util/crash/CrashReport;)V", ordinal = 1))
    private CrashReport atTheEndOfFirstCatchBeforePrintingCrashReport(CrashReport report) {

        MinecraftClient.getInstance().setScreen(new GuiProblemScreen());
        if(MinecraftClient.getInstance().getDebugHud().shouldShowDebugHud())
            MinecraftClient.getInstance().getDebugHud().toggleDebugHud();
        MinecraftClient.getInstance().inGameHud.getChatHud().clear(true);
        MinecraftClient.getInstance().run();
        return report;
    }
    /*
    @ModifyArg(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;printCrashReport(Lnet/minecraft/util/crash/CrashReport;)V", ordinal = 2))
    private CrashReport atTheEndOfSecondCatchBeforePrintingCrashReport(CrashReport report) {

        // we MUST use the report passed as parameter, because the field one only gets assigned in integrated server crashes.
        MinecraftClient.getInstance().setScreen(new GuiProblemScreen());
        if(MinecraftClient.getInstance().getDebugHud().shouldShowDebugHud())
            MinecraftClient.getInstance().getDebugHud().toggleDebugHud();
        MinecraftClient.getInstance().inGameHud.getChatHud().clear(true);
        MinecraftClient.getInstance().run();
        return report;
    }
     */
    private CrashReport crashReport;
    @Redirect(method = "<init>", require = 0, at = @At(value = "INVOKE", target = "Lnet/fabricmc/loader/impl/game/minecraft/Hooks;startClient(Ljava/io/File;Ljava/lang/Object;)V", remap = false))
    private void catchFabricInit(File runDir, Object gameInstance) {
            try {
                ModLoaders.fabricEntrypoints(runDir, gameInstance);
            } catch (Throwable throwable) {
                LogManager.getLogger("VanillaIcecreamFix").error("Caught a Crash");
                crashReport = CrashReport.create(throwable, "Initializing game");
                crashReport.addElement("Initialization");
                MinecraftClient.addSystemDetailsToCrashReport(null, null, MinecraftVersion.create().getName(), null,crashReport);
                printCrashReport(crashReport);
                // Make GL shutup about any GL error that occurred
                Window.acceptError((integer, stringx) -> {
                });
            }
    }

    @Inject(method = "printCrashReport()V", at = @At("HEAD"))
    private void onCheckGameCrashed(CallbackInfo ci) {

        if (this.crashReportSupplier != null) {
            printCrashReport(crashReportSupplier.get());

            // Causes the run loop to keep going
            crashReportSupplier = null;
        }
    }


    @Redirect(method = "<init>", require = 0, at = @At(value = "INVOKE", target = "Lorg/quiltmc/loader/impl/entrypoint/minecraft/hooks/EntrypointClient;start(Ljava/io/File;Ljava/lang/Object;)V", remap = false))
    private void catchQuiltInit(File runDir, Object gameInstance) {
            try {
                ModLoaders.quiltEntrypoints(runDir, gameInstance);
            } catch (Throwable throwable) {
                LogManager.getLogger("VanillaIcecreamFix").error("Caught a Crash");
                crashReport = CrashReport.create(throwable, "Initializing game");
                crashReport.addElement("Initialization");
                MinecraftClient.addSystemDetailsToCrashReport(null, null, MinecraftVersion.create().getName(), null, crashReport);
                printCrashReport(crashReport);
                // Make GL shuttup about any GL error that occurred
                Window.acceptError((integer, stringx) -> {
                });
            }
    }
    @SuppressWarnings({"MixinAnnotationTarget", "UnresolvedMixinReference"})
    @Redirect(method = "doLoadLevel(Ljava/lang/String;Lnet/minecraft/util/registry/DynamicRegistryManager$Impl;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;ZLnet/minecraft/client/MinecraftClient$WorldLoadAction;Z)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;printCrashReport(Lnet/minecraft/util/crash/CrashReport;)V"),
            require = 0)
    private void redirectForgePrintCrashReport(CrashReport report) {
        printCrashReport(report);
    }
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void printCrashReport(CrashReport report){
        File file = new File(MinecraftClient.getInstance().runDirectory, "crash-reports");
        SimpleDateFormat var10003 = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
        Date var10004 = new Date();
        File file2 = new File(file, "crash-" + var10003.format(var10004) + "-client.txt");
        Bootstrap.println(report.asString());
        if (report.getFile() != null) {
            Bootstrap.println("#@!@# Game crashed! Crash report saved to: #@!@# " + report.getFile());
        } else if (report.writeToFile(file2)) {
            Bootstrap.println("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
        } else {
            Bootstrap.println("#@?@# Game crashed! Crash report could not be saved. #@?@#");
        }
        MinecraftClient.getInstance().setScreen(new GuiProblemScreen());
        if(MinecraftClient.getInstance().getDebugHud().shouldShowDebugHud())
            MinecraftClient.getInstance().getDebugHud().toggleDebugHud();
        MinecraftClient.getInstance().inGameHud.getChatHud().clear(true);
    }
}
