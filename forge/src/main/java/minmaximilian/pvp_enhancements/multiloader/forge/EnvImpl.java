package minmaximilian.pvp_enhancements.multiloader.forge;

import minmaximilian.pvp_enhancements.multiloader.Env;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class EnvImpl {

    public static Env getCurrent() {
        return FMLEnvironment.dist == Dist.CLIENT ? Env.CLIENT : Env.SERVER;
    }
}
