package dev.redstudio.flexipause.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

import static dev.redstudio.flexipause.ProjectConstants.LOGGER;

@IFMLLoadingPlugin.Name("FlexiPause Plugin")
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"dev.redstudio.flexipause.asm", "dev.redstudio.flexipause.config"})
public final class FlexiPausePlugin implements IFMLLoadingPlugin {

    public static boolean isObfuscated = true;

    public FlexiPausePlugin() {
        LOGGER.info("Initializing FlexiPause Plugin");
        FlexiPauseTransformer.loadConfig();
    }

    @Override
    public String[] getASMTransformerClass() {
        LOGGER.info("Loading FlexiPause ASM Transformer");
        return new String[]{FlexiPauseTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(final Map<String, Object> data) {
        isObfuscated = (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
