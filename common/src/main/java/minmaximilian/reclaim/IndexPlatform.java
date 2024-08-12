package minmaximilian.reclaim;

import static minmaximilian.reclaim.Reclaim.REGISTRATE;

import dev.architectury.injectables.annotations.ExpectPlatform;
import minmaximilian.reclaim.block.ReclaimBlocks;
import minmaximilian.reclaim.data.PvPRegistrate;
import minmaximilian.reclaim.item.ReclaimItems;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab;

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
        REGISTRATE.useCreativeTab(ReclaimCreativeModeTab.getBaseTabKey());
    }

    public static void register() {
        useBaseTab();
        ReclaimBlocks.register();
        ReclaimItems.register();
    }
}
