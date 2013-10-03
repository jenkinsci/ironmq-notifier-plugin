package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class IronMQDescriptorClassTest {

    //TODO: I need some help to learn how to test these without forcing static


    @Test
        public void CheckForValidQueueNameInDescriptor()
    {
        Assert.assertEquals(true,IronMQDescriptor.isValidQueueName("testTEST"));

    }

    @Test
    public void CheckForInvalidQueueNameInDescriptor()
    {
        Assert.assertEquals(false,IronMQDescriptor.isValidQueueName("88")); // force error

    }

    @Test
    public void Check_That_A_Valid_Queuename_Has_at_least_one_Character()
    {
        Assert.assertEquals(false,IronMQDescriptor.isValidQueueName(""));
    }


}
