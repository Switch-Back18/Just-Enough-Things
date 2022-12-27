package fr.mathisskate.justenoughthings.item;

public class GlassCutterUnbreakableItem extends GlassCutterItem {
    public GlassCutterUnbreakableItem() {
        super();
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
}
