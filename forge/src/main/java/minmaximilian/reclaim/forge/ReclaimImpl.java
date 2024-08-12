package minmaximilian.reclaim.forge;

import static minmaximilian.reclaim.Reclaim.REGISTRATE;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.config.ReclaimConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reclaim.MOD_ID)
public class ReclaimImpl {

    static IEventBus modEventBus;

    public ReclaimImpl() {
        modEventBus = FMLJavaModLoadingContext.get()
            .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        ModLoadingContext mlContext = ModLoadingContext.get();

        ReclaimConfig.registerConfigs(mlContext::registerConfig);

        modEventBus.addListener(this::onLoadConfig);
        modEventBus.addListener(this::onReloadConfig);

        ReclaimCommonEventsImpl.register(forgeEventBus);

        Reclaim.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
            () -> () -> ReclaimClientImpl.prepareClient(modEventBus, forgeEventBus));
    }

    public static void finalizeRegistrate() {
        REGISTRATE.registerEventListeners(modEventBus);
    }

    private void onLoadConfig(ModConfigEvent.Loading event) {
        ReclaimConfig.onLoad(event.getConfig());
    }

    private void onReloadConfig(ModConfigEvent.Reloading event) {
        ReclaimConfig.onReload(event.getConfig());
    }
}
