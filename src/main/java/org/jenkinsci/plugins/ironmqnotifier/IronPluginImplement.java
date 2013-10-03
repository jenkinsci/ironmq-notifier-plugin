package org.jenkinsci.plugins.ironmqnotifier;


import hudson.Plugin;

import java.util.logging.Logger;


public class IronPluginImplement extends Plugin {

    private final static Logger LOG = Logger.getLogger(IronPluginImplement.class.getName());

    public void start() throws Exception {
        LOG.info("starting ironmq-notifier plugin");
    }
}

