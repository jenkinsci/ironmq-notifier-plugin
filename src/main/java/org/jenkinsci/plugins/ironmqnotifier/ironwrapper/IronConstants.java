package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;

public final class IronConstants {

    public static final String DEF_PREFERRED_SERVER_SCHEME = "https";
    public static final String DEF_QUEUE_NAME = "Jenkins";
    public static final String DEFAULT_PREFERRED_SERVER_NAME
            = "mq-aws-us-east-1-1.iron.io";
    public static final int DEF_PREFERRED_SERVER_PORT = 443;
    public static final long DEF_EXPIRY_SEC = 604800L;
    public static final String DEF_MESSAGE_VERSION = "3.0";


    private IronConstants() {
    }


}
