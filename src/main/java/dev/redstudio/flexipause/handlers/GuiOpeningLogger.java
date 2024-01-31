package dev.redstudio.flexipause.handlers;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static dev.redstudio.flexipause.ProjectConstants.LOGGER;

/**
 * Static event handler which logs the name of every GUI the client opens.
 *
 * @author Luna Lage (Desoroxxx)
 * @since 1.0
 */
public final class GuiOpeningLogger {

    @SubscribeEvent
    public static void onGuiOpenEvent(final GuiOpenEvent guiOpenEvent) {
        final GuiScreen gui = guiOpenEvent.getGui();

        if (gui != null)
            LOGGER.debug("Opened GUI: {}", gui.getClass().getName());
    }
}
