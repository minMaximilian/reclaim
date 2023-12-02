package minmaximilian.pvp_enhancements;

import static minmaximilian.pvp_enhancements.PvPEnhancements.REGISTRATE;

import dev.architectury.injectables.annotations.ExpectPlatform;
import minmaximilian.pvp_enhancements.block.PvPEnhancementsBlocks;
import minmaximilian.pvp_enhancements.data.PvPRegistrate;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import minmaximilian.pvp_enhancements.registry.PvPEnhancementsCreativeModeTab;

public class IndexPlatform {

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PvPRegistrate createRegistrate(String id) {
        throw new AssertionError();
    }

    public static void useBaseTab() {
        REGISTRATE.useCreativeTab(PvPEnhancementsCreativeModeTab.getBaseTabKey());
    }


    public static void register() {
        useBaseTab();
        PvPEnhancementsBlocks.register();
        PvPEnhancementsItems.register();
    }
}
