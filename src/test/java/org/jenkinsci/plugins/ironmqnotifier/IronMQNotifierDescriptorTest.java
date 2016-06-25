package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class IronMQNotifierDescriptorTest {


    private IronMQNotifier StandardTestNotifier() {
        return new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);
    }

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();


    @Test
    public void CanInitiateNewNotifier() {

        IronMQNotifier.IronMQNotifierDescriptor descriptor = new IronMQNotifier.IronMQNotifierDescriptor();

        Assert.assertNotNull(descriptor);
    }

}
