package org.jenkinsci.plugins.ironmqnotifier;

public class IronConstants {


    public static String DEFAULT_QUEUE_NAME = "Jenkins";
    public static String DEFAULT_PREFERRED_SERVER_NAME = "mq-rackspace-ord.iron.io";
    public static final int DEFAULT_PREFERRED_SERVER_PORT = 443;
    public static long DEFAULT_EXPIRY_SECONDS = 806400L;
    public static long MILLISECONDS_TO_SECONDS_CONVERSION = 1000;

    public IronConstants() {
        //placeholder
    }

}
