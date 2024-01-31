package dev.redstudio.replace.utils;

import io.redstudioragnarok.redcore.logging.RedLogger;
import dev.redstudio.replace.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class defines constants for Replace.
 * <p>
 * They are automatically updated by RFG on compile time, except for the name as Gradle would remove spaces.
 */
public class ModReference {

    public static final String ID = Tags.ID;
    public static final String NAME = "Replace";
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOG = LogManager.getLogger(NAME);
    public static final RedLogger RED_LOG = new RedLogger(NAME, "placeholderNewIssueUrl", LOG);
}
