package me.reasonless.youaintspecial.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeFeatureRenderer.class)
public class MixinCapeFeatureRenderer {

    @Inject(
            method = "render",
            at = @At("HEAD"),
            cancellable = true
    )
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        Identifier identifier = abstractClientPlayerEntity.getCapeTexture();

        // check if it is null or the game will commit die
        if(identifier != null) {
            /*
                the cape identifier's path is "skin/<hash>",
                the hash of the migration cape is 17f76a23ff4d227a94ea3d5802dccae9f2ae9aa9
                so we can compare the texture's identifier and then cancel it if the
                migration cape is present.
             */
            if(identifier.getPath().equals("skins/17f76a23ff4d227a94ea3d5802dccae9f2ae9aa9")) {
                ci.cancel(); // cancel it (stopping it from rendering)
            }
        }
    }

}
