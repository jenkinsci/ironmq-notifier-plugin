package org.jenkinsci.plugins.ironmqnotifier;


import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;

public class TestSettings {

    private static IronConstants ironConstants = new IronConstants();

    static String TESTPROJECTID = "141415252";
    static String TESTTOKEN = "55255555";
    static String TESTQUEUENAME = ironConstants.DEF_QUEUE_NAME;
    static String TESTPREFERREDSERVER = ironConstants.DEFAULT_PREFERRED_SERVER_NAME;
    static Long EXPIRYSETTINGS = ironConstants.DEF_EXPIRY_SEC;
    static String STANDARDDEFAULTSERVER = ironConstants.DEFAULT_PREFERRED_SERVER_NAME;

}

