package minmaximilian.pvp_enhancements.data;

import com.tterrag.registrate.AbstractRegistrate;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PvPRegistrate extends AbstractRegistrate<PvPRegistrate> {

    private ResourceKey<CreativeModeTab> currentTab;

    public PvPRegistrate(String modid) {
        super(modid);
    }

    public PvPRegistrate registerEventListeners(Object bus) {
        return null;
    }

    public PvPRegistrate useCreativeTab(ResourceKey<CreativeModeTab> tab) {
        this.currentTab = tab;
        return this;
    }
}
