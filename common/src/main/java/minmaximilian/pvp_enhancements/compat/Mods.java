package minmaximilian.pvp_enhancements.compat;

import static net.minecraft.core.registries.BuiltInRegistries.BLOCK;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import minmaximilian.pvp_enhancements.IndexPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public enum Mods {
    CREATEBIGCANNONS,
    CREATE;

    public boolean isLoaded() {
        return IndexPlatform.isModLoaded(asId());
    }

    public String asId() {
        return name().toLowerCase(Locale.ROOT);
    }

    public <T> Optional<T> runIfInstalled(Supplier<Supplier<T>> toRun) {
        if (isLoaded()) {
            return Optional.of(toRun.get().get());
        }
        return Optional.empty();
    }

    public void executeIfInstalled(Supplier<Runnable> toExecute) {
        if (isLoaded()) {
            toExecute.get().run();
        }
    }

    public Block getBlock(String id) {
        return BLOCK.get(new ResourceLocation(asId(), id));
    }
}
