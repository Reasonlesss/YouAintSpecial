package me.reasonless.youaintspecial.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ElytraFeatureRenderer.class)
public class MixinElytraFeatureRenderer {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;canRenderCapeTexture()Z"))
    public boolean render(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        Identifier identifier = abstractClientPlayerEntity.getCapeTexture();

        if (identifier == null) {
            return false;
        } else return !identifier.getPath().equals("skins/17f76a23ff4d227a94ea3d5802dccae9f2ae9aa9");
    }
}
