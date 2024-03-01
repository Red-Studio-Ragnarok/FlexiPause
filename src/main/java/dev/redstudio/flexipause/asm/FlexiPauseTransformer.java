package dev.redstudio.flexipause.asm;

import dev.redstudio.flexipause.config.FlexiPauseConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.*;

import static dev.redstudio.flexipause.ProjectConstants.LOGGER;

/**
 * Transformer for FlexiPause.
 * <p>
 * It will transform any class the user asked it to transform.
 * First it will remove any {@link GuiScreen#doesGuiPauseGame} method from the class.
 * It will then add a {@link GuiScreen#doesGuiPauseGame} method to the class with a hardcoded true or false value depending on the config.
 *
 * @author Luna Lage (Desoroxxx)
 * @since 1.0
 */
public final class FlexiPauseTransformer implements IClassTransformer {

    private static final String MCP_OR_NOTCH_METHOD_NAME = FMLLaunchHandler.isDeobfuscatedEnvironment() ? "doesGuiPauseGame" : "d";
    private static final String SEARGE_METHOD_NAME =  "func_73868_f";

    private static Map<String, Boolean> pauseOverrides;

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!pauseOverrides.containsKey(transformedName))
            return basicClass;

        final ClassNode classNode = new ClassNode();

        new ClassReader(basicClass).accept(classNode, 0);

        boolean matchedSeargeMethodName = false;

        final Iterator<MethodNode> iterator = classNode.methods.iterator();
        while (iterator.hasNext()) {
            final MethodNode method = iterator.next();

            if (!((method.name.equals(MCP_OR_NOTCH_METHOD_NAME) || method.name.equals(SEARGE_METHOD_NAME)) && method.desc.equals("()Z")))
                continue;

            if (method.name.equals(SEARGE_METHOD_NAME))
                matchedSeargeMethodName = true;

            iterator.remove();
        }

        classNode.methods.add(createMethod(matchedSeargeMethodName, pauseOverrides.get(transformedName)));

        final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        classNode.accept(classWriter);

        return classWriter.toByteArray();
    }

    /**
     * Creates a {@link GuiScreen#doesGuiPauseGame} with a hardcoded return value.
     *
     * @param useSeargeMethodName Whether the name of the created {@link GuiScreen#doesGuiPauseGame} method should use {@link #SEARGE_METHOD_NAME} or {@link #MCP_OR_NOTCH_METHOD_NAME}
     * @param shouldPauseGame The hardcoded return value determining if the game should be paused
     * @return The newly created {@link GuiScreen#doesGuiPauseGame} method
     */
    private MethodNode createMethod(final boolean useSeargeMethodName, final boolean shouldPauseGame) {
        final String methodName = useSeargeMethodName ? SEARGE_METHOD_NAME : MCP_OR_NOTCH_METHOD_NAME;

        final MethodNode method = new MethodNode(Opcodes.ACC_PUBLIC, methodName, "()Z", null, null);

        method.instructions.add(new InsnNode(shouldPauseGame ? Opcodes.ICONST_1 : Opcodes.ICONST_0));
        method.instructions.add(new InsnNode(Opcodes.IRETURN));

        return method;
    }

    /**
     * Loads the {@link FlexiPauseConfig#pauseOverrides} config.
     */
    static void loadConfig() {
        pauseOverrides = new HashMap<>();

        for (final String pauseOverride : FlexiPauseConfig.pauseOverrides) {
            final String[] parts = pauseOverride.split(":");

            if (parts.length == 2) {
                pauseOverrides.put(parts[0], Boolean.parseBoolean(parts[1]));
            } else {
                LOGGER.error("Malformed pause override: {}", pauseOverride);
            }
        }
    }
}
