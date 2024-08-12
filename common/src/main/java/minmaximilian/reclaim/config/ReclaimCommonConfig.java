package minmaximilian.reclaim.config;

public class ReclaimCommonConfig extends ReclaimBase {

    public final ConfigBool healExplosions = b(true, "healExplosions", Comments.healExplosions);
    public final ConfigBool healCreeperExplosions = b(false, "healCreeperExplosions", Comments.healCreeperExplosions);
    public final ConfigBool healFires = b(true, "healFires", Comments.healFires);
    public final ConfigInt delayInTicksBeforeHealingDamage = i(12000, 1200, "delayInTicksBeforeHealingDamage",
        Comments.delayInTicksBeforeHealingDamage);

    @Override
    public String getName() {
        return "common";
    }

    private static class Comments {

        static String healExplosions = "Heal damage from explosive sources.";
        static String healCreeperExplosions = "Heal damage from creeper explosions.";
        static String healFires = "Heal damage from fire sources.";
        static String delayInTicksBeforeHealingDamage = "Delay in ticks before any damage is. E.g. 6000 ticks == 5 minutes.";

    }
}
