package org.jenkinsci.plugins.ironmqnotifier;

import hudson.Plugin;
import java.util.logging.Logger;

/**
 * <p>IronPluginImplement class.</p>
 *
 * @author Mike Caspar
 * @version $Id: $
 */
public final class PluginImpl extends Plugin {

    /**
     * <p>logger is used to enable debugging on plugin startup.</p>
     */
    private final Logger logger
            = Logger.getLogger(PluginImpl.class.getName());

    /**
     * <p>start.</p>
     *
     * @throws java.lang.Exception if any.
     */
    public void start() throws Exception {
        logger.info("starting ironmq-notifier plugin");
    }
}