package org.jenkinsci.plugins.ironmqnotifier;



import hudson.util.FormValidation;
import org.junit.Assert;
import org.junit.Test;
import org.kohsuke.stapler.QueryParameter;

import static org.mockito.Mockito.*;


public class IronMQDescriptorTest {

    @Test
    public void Test_Descriptor_Is_There_And_Returns_A_DisplayName()

    {
        IronMQDescriptor build = mock(IronMQDescriptor.class);
        when(build.getDisplayName()).thenReturn("IronMQNotifier");

        Assert.assertEquals("IronMQNotifier", build.getDisplayName());

    }

    @Test
    public void Test_Descriptor_Form_Expiry_Works()

    {
        IronMQDescriptor build = mock(IronMQDescriptor.class);
        when(build.doCheckExpirySeconds(1000L)).thenReturn(FormValidation.ok());

        Assert.assertEquals(FormValidation.ok(), build.doCheckExpirySeconds(1000L));

     }


    @Test
    public void Test_Descriptor_Form_QueueName_Works()

    {
        IronMQDescriptor build = mock(IronMQDescriptor.class);
        when(build.doCheckQueueName("test")).thenReturn(FormValidation.ok());

        Assert.assertEquals(FormValidation.ok(), build.doCheckQueueName("test"));

    }


}
