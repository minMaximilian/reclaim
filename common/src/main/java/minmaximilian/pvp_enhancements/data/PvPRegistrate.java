package minmaximilian.pvp_enhancements.data;

import java.util.IdentityHashMap;
import java.util.Map;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.fabric.RegistryObject;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PvPRegistrate extends AbstractRegistrate<PvPRegistrate> {

    private static Map<RegistryEntry<?>, ResourceKey<CreativeModeTab>> tabLookup = new IdentityHashMap<>();
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

    public boolean isInCreativeTab(RegistryEntry<?> entry, ResourceKey<CreativeModeTab> tab) {
        return tabLookup.get(entry) == tab;
    }

    @Override
    protected <R, T extends R> RegistryEntry<T> accept(String name, ResourceKey<? extends Registry<R>> type,
        Builder<R, T, ?, ?> builder, NonNullSupplier<? extends T> creator,
        NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);
        if (currentTab != null) {
            tabLookup.put(entry, currentTab);
        }
        return entry;
    }
}
