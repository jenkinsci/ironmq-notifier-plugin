package org.jenkinsci.plugins.ironmqnotifier;

final class IronConstants {


    protected static String DEFAULT_QUEUE_NAME = "Jenkins";
    protected static String DEFAULT_PREFERRED_SERVER_NAME
            = "mq-rackspace-ord.iron.io";
    protected static final int DEFAULT_PREFERRED_SERVER_PORT = 443;
    protected static long DEFAULT_EXPIRY_SECONDS = 806400L;

    protected static long MILLISECONDS_TO_SECONDS_CONVERSION = 1000;

    /**
     * <p>Constructor for IronConstants.</p>
     */
    public IronConstants() {
        //placeholder
    }

}
