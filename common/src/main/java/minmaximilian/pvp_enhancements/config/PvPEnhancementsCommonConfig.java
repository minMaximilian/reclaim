package minmaximilian.pvp_enhancements.config;

public class PvPEnhancementsCommonConfig extends PvPEnhancementsBase {

    public final ConfigBool healExplosions = b(true, "healExplosions", Comments.healExplosions);
    public final ConfigBool healCreeperExplosions = b(false, "healCreeperExplosions", Comments.healCreeperExplosions);
    public final ConfigBool healFires = b(true, "healFires", Comments.healFires);
    public final ConfigInt delayInTicksBeforeHealingDamage = i(6000, 0, "delayInTicksBeforeHealingDamage", Comments.delayInTicksBeforeHealingDamage);
    public final ConfigInt ticksBetweenHeals = i(20, 10, 100, "ticksBetweenHeals", Comments.ticksBetweenHeals);

    @Override
    public String getName() {
        return "common";
    }

    private static class Comments {
        public static String ticksBetweenHeals = "Ticks between each heal.";
        static String healExplosions = "Heal damage from explosive sources.";
        static String healCreeperExplosions = "Heal damage from creeper explosions.";
        static String healFires = "Heal damage from fire sources.";
        static String delayInTicksBeforeHealingDamage = "Delay in ticks before any damage is. E.g. 6000 ticks == 5 minutes.";

    }
}
