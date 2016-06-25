package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void CanExecuteGetDescriptorMocked() {

        IronMQNotifier notifier = mock(IronMQNotifier.class);
        when(notifier.getDescriptor()).thenReturn(new IronMQNotifier.IronMQNotifierDescriptor());

        IronMQNotifier.IronMQNotifierDescriptor result = notifier.getDescriptor();

        Assert.assertNotNull(result);

    }

    @Test
    public void CanExecuteGetDescriptorDirect() {

        IronMQNotifier notifier = this.StandardTestNotifier();

        IronMQNotifier.IronMQNotifierDescriptor result = notifier.getDescriptor();

        Assert.assertNotNull(result);

    }

}

