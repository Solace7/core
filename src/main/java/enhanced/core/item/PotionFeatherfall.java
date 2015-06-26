package enhanced.core.item;

import net.minecraft.potion.Potion;

public class PotionFeatherfall extends Potion {
    public PotionFeatherfall(int id, boolean isBad, int liquidColour) {
        super(id, isBad, liquidColour);
        setIconIndex(0, 0);
        setPotionName("potion.featherfall");
    }
}
