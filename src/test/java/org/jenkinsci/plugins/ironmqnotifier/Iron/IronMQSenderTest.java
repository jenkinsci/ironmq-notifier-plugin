package org.jenkinsci.plugins.ironmqnotifier.Iron;



import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IronMQSenderTest{

    @Test
    public void Can_Create_A_Sender()
    {
        ironMQSender sender = new ironMQSender();

        Assert.assertNotNull(sender);
    }

    @Test (expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Zero_Expiry()
            throws IOException, IllegalArgumentException {

        clientWrapper mockClient = mock(clientWrapper.class);
        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds ()).thenReturn(0L);

        ironMQSender sender =  new ironMQSender();

        sender.Send (mockClient, mockIronMessageSettings);

    }

    @Test (expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Empty_QueueName()
            throws IOException, IllegalArgumentException {

        clientWrapper mockClient = mock(clientWrapper.class);
        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds ()).thenReturn(1000L); //avoid exception
        when(mockIronMessageSettings.getQueueName ()).thenReturn ("");

        ironMQSender sender =  new ironMQSender();

        sender.Send (mockClient, mockIronMessageSettings);

    }

    @Test (expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Null_QueueName()
            throws IOException, IllegalArgumentException {

        clientWrapper mockClient = mock(clientWrapper.class);
        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds ()).thenReturn(1000L); //avoid exception
        when(mockIronMessageSettings.getQueueName ()).thenReturn (null);

        ironMQSender sender =  new ironMQSender();

        sender.Send (mockClient, mockIronMessageSettings);

    }
}


