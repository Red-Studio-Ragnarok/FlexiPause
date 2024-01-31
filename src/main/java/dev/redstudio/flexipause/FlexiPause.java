package dev.redstudio.flexipause;

import dev.redstudio.flexipause.config.FlexiPauseConfig;
import dev.redstudio.flexipause.handlers.GuiOpeningLogger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static dev.redstudio.flexipause.ProjectConstants.ID;
import static dev.redstudio.flexipause.ProjectConstants.NAME;
import static dev.redstudio.flexipause.ProjectConstants.VERSION;

//   /$$$$$$$$ /$$                     /$$ /$$$$$$$
//  | $$_____/| $$                    |__/| $$__  $$
//  | $$      | $$  /$$$$$$  /$$   /$$ /$$| $$  \ $$ /$$$$$$  /$$   /$$  /$$$$$$$  /$$$$$$
//  | $$$$$   | $$ /$$__  $$|  $$ /$$/| $$| $$$$$$$/|____  $$| $$  | $$ /$$_____/ /$$__  $$
//  | $$__/   | $$| $$$$$$$$ \  $$$$/ | $$| $$____/  /$$$$$$$| $$  | $$|  $$$$$$ | $$$$$$$$
//  | $$      | $$| $$_____/  >$$  $$ | $$| $$      /$$__  $$| $$  | $$ \____  $$| $$_____/
//  | $$      | $$|  $$$$$$$ /$$/\  $$| $$| $$     |  $$$$$$$|  $$$$$$/ /$$$$$$$/|  $$$$$$$
//  |__/      |__/ \_______/|__/  \__/|__/|__/      \_______/ \______/ |_______/  \_______/
@Mod(modid = ID, name = NAME, version = VERSION, updateJSON = "https://forge.curseupdate.com/969543/flexipause")
public final class FlexiPause {

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public static void init(final FMLInitializationEvent initializationEvent) {
        updateGuiOpeningLogger();
    }

    /**
     * Register or unregister the {@link GuiOpeningLogger} event handler which logs the name of every GUI the client opens.
     * <p>
     * It registers or unregisters based on the {@link FlexiPauseConfig.Client#logClientGuiOpening} config value.
     *
     * @author Luna Lage (Desoroxxx)
     * @see GuiOpeningLogger
     * @since 1.0
     */
    @SideOnly(Side.CLIENT)
    public static void updateGuiOpeningLogger() {
        if (FlexiPauseConfig.client.logClientGuiOpening) {
            MinecraftForge.EVENT_BUS.register(GuiOpeningLogger.class);
        } else {
            MinecraftForge.EVENT_BUS.unregister(GuiOpeningLogger.class);
        }
    }
}
