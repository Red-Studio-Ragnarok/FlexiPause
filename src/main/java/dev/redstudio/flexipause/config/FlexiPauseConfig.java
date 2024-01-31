package dev.redstudio.flexipause.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import dev.redstudio.flexipause.FlexiPause;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static dev.redstudio.flexipause.ProjectConstants.ID;
import static dev.redstudio.flexipause.ProjectConstants.NAME;

/**
 * @author Luna Lage (Desoroxxx)
 * @since 1.0
 */
@Config(modid = ID, name = NAME)
public final class FlexiPauseConfig {

    public static final Client client = new Client();

    @SideOnly(Side.CLIENT)
    public static class Client {

        @Config.Name("Log Client GUI Opening")
        @Config.Comment("If true, the name of every GUI the client opens will be logged.")
        public boolean logClientGuiOpening;
    }

    @Config.RequiresMcRestart
    @Config.Name("Pause Overrides")
    @Config.Comment({
            "The list of GUI to modify the pausing behavior of.",
            "",
            "The format is the class name and the pausing behavior.",
            "The pausing behavior can be true or false.",
            "",
            "For example: net.minecraft.client.gui.inventory.GuiInventory:true",
            "This will cause the game to pause when the inventory is opened.",
            "",
            "Another example: net.minecraft.client.gui.GuiIngameMenu:false",
            "This will cause the game to not pause when the esc menu is opened.",
    })
    public static String[] pauseOverrides = new String[]{};

    @Mod.EventBusSubscriber(modid = ID)
    public static final class ConfigEventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent onConfigChangedEvent) {
            if (!onConfigChangedEvent.getModID().equals(ID))
                return;

            ConfigManager.sync(ID, Config.Type.INSTANCE);

            if (FMLCommonHandler.instance().getSide().isClient())
                FlexiPause.updateGuiOpeningLogger();
        }
    }

    static {
        ConfigAnytime.register(FlexiPauseConfig.class);
    }
}
