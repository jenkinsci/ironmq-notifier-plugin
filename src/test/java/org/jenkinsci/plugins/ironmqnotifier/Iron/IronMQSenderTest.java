package org.jenkinsci.plugins.ironmqnotifier.Iron;



import hudson.util.FormValidation;
import org.jenkinsci.plugins.ironmqnotifier.Iron.IronMQSender;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IronMQSenderTest{

    @Test
    public void Can_Create_A_Sender()
    {
        IronMQSender sender = new IronMQSender();

        Assert.assertNotNull(sender);
    }

    @Test (expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Zero_Expiry()
            throws IOException, IllegalArgumentException {

        ClientWrapper mockClient = mock(ClientWrapper.class);
        MessageSettings mockMessageSettings = mock(MessageSettings.class);
        when(mockMessageSettings.getExpirySeconds ()).thenReturn(0L);

        IronMQSender sender =  new IronMQSender();

        sender.Send (mockClient, mockMessageSettings);

    }

    @Test (expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Empty_QueueName()
            throws IOException, IllegalArgumentException {

        ClientWrapper mockClient = mock(ClientWrapper.class);
        MessageSettings mockMessageSettings = mock(MessageSettings.class);
        when(mockMessageSettings.getExpirySeconds ()).thenReturn(1000L); //avoid exception
        when(mockMessageSettings.getQueueName ()).thenReturn ("");

        IronMQSender sender =  new IronMQSender();

        sender.Send (mockClient, mockMessageSettings);

    }

    @Test (expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Null_QueueName()
            throws IOException, IllegalArgumentException {

        ClientWrapper mockClient = mock(ClientWrapper.class);
        MessageSettings mockMessageSettings = mock(MessageSettings.class);
        when(mockMessageSettings.getExpirySeconds ()).thenReturn(1000L); //avoid exception
        when(mockMessageSettings.getQueueName ()).thenReturn (null);

        IronMQSender sender =  new IronMQSender();

        sender.Send (mockClient, mockMessageSettings);

    }
}


