package minmaximilian.pvp_enhancements.data;

import com.tterrag.registrate.AbstractRegistrate;

public abstract class PvPRegistrate extends AbstractRegistrate<PvPRegistrate> {
    protected PvPRegistrate(String modid) {
        super(modid);
    }

    public PvPRegistrate registerEventListeners(Object bus) {
        return null;
    }
}
