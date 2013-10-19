package org.jenkinsci.plugins.ironmqnotifier.send;


import org.junit.Assert;
import org.junit.Test;

public class IronMQSenderTest{

    @Test
    public void Can_Create_A_Sender()
    {
        IronMQSender sender = new IronMQSender();

        Assert.assertNotNull(sender);
    }

}
