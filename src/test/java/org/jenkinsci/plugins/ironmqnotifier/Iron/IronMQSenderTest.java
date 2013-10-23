package org.jenkinsci.plugins.ironmqnotifier.Iron;


import org.jenkinsci.plugins.ironmqnotifier.Iron.IronMQSender;
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
