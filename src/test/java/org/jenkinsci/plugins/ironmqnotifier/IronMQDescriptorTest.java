package org.jenkinsci.plugins.ironmqnotifier;



import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class IronMQDescriptorTest {

    @Test
    public void Test_Descriptor_Is_There_And_Returns_A_DisplayName()

    {
        IronMQDescriptor build = mock(IronMQDescriptor.class);
        when(build.getDisplayName()).thenReturn("IronMQNotifier");

        Assert.assertEquals("IronMQNotifier", build.getDisplayName());
    }



}
