package com.blamejared.the_breach.client.renderer.entity;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.entity.monster.zombie.Bleak;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;

public class BleakRenderer extends AbstractZombieRenderer<Bleak, ZombieRenderState, ZombieModel<ZombieRenderState>> {
    
    private static final Identifier BLEAK_LOCATION = TBConstants.id("textures/entity/zombie/bleak.png");
    private static final Identifier BABY_BLEAK_LOCATION = TBConstants.id("textures/entity/zombie/bleak_baby.png");
    
    public BleakRenderer(EntityRendererProvider.Context context) {
        
        this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_BABY, ModelLayers.ZOMBIE_ARMOR, ModelLayers.ZOMBIE_BABY_ARMOR);
    }
    
    public BleakRenderer(
            EntityRendererProvider.Context context,
            ModelLayerLocation body,
            ModelLayerLocation babyBody,
            ArmorModelSet<ModelLayerLocation> armorSet,
            ArmorModelSet<ModelLayerLocation> babyArmorSet
    ) {
        
        super(
                context,
                new ZombieModel<>(context.bakeLayer(body)),
                new BabyZombieModel<>(context.bakeLayer(babyBody)),
                ArmorModelSet.bake(armorSet, context.getModelSet(), ZombieModel::new),
                ArmorModelSet.bake(babyArmorSet, context.getModelSet(), BabyZombieModel::new)
        );
    }
    
    @Override
    public Identifier getTextureLocation(ZombieRenderState state) {
        
        return state.isBaby ? BABY_BLEAK_LOCATION : BLEAK_LOCATION;
    }
    
    @Override
    public ZombieRenderState createRenderState() {
        
        return new ZombieRenderState();
    }
    
}
