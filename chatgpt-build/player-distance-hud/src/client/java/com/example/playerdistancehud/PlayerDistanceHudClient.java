package com.example.playerdistancehud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

/**
 * Client entry point and HUD renderer for Player Distance HUD.
 */
public final class PlayerDistanceHudClient implements ClientModInitializer {
    public static final String MOD_ID = "playerdistancehud";

    private static final Identifier HUD_ELEMENT_ID = Identifier.of(MOD_ID, "nearest_player_distance");
    private static final int HUD_VERTICAL_OFFSET = 16;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.CROSSHAIR,
                HUD_ELEMENT_ID,
                PlayerDistanceHudClient::renderHud
        );
    }

    private static void renderHud(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.world == null
                || client.player == null
                || client.options.hudHidden
                || client.currentScreen != null) {
            return;
        }

        PlayerEntity nearestPlayer = NearestPlayerFinder.findNearest(client.world, client.player).orElse(null);
        if (nearestPlayer == null) {
            return;
        }

        double distance = DistanceFormatter.calculateDistance(client.player, nearestPlayer);
        String line = nearestPlayer.getGameProfile().name()
                + " - "
                + DistanceFormatter.format(distance)
                + " blocks";

        int x = (context.getScaledWindowWidth() - client.textRenderer.getWidth(line)) / 2;
        int y = context.getScaledWindowHeight() / 2 + HUD_VERTICAL_OFFSET;
        context.drawTextWithShadow(client.textRenderer, line, x, y, TEXT_COLOR);
    }
}
