package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;

import io.iron.ironmq.Client;
import io.iron.ironmq.Queue;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IronMQSenderTest {

    @Test
    public void Can_Create_A_Sender() {
        IronMQSender sender = new IronMQSender();

        Assert.assertNotNull(sender);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Zero_Expiry()
            throws IOException, IllegalArgumentException {

        Client mockClient = mock(Client.class);
        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds()).thenReturn(0L);

        IronMQSender sender = new IronMQSender();

        sender.send(mockClient, mockIronMessageSettings);

    }

    @Test(expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Empty_QueueName()
            throws IOException, IllegalArgumentException {

        Client mockClient = mock(Client.class);
        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds()).thenReturn(1000L); //avoid exception
        when(mockIronMessageSettings.getQueueName()).thenReturn("");

        IronMQSender sender = new IronMQSender();

        sender.send(mockClient, mockIronMessageSettings);

    }

    @Test(expected = IllegalArgumentException.class)
    public void SenderThrows_An_Arg_Exception_On_Null_QueueName()
            throws IOException, IllegalArgumentException {

        Client mockClient = mock(Client.class);
        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds()).thenReturn(1000L); //avoid exception
        when(mockIronMessageSettings.getQueueName()).thenReturn(null);

        IronMQSender sender = new IronMQSender();

        sender.send(mockClient, mockIronMessageSettings);

    }

    @Test
    public void Sender_Normal_Operation() throws IOException {

        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds()).thenReturn(1000L); //avoid exception
        when(mockIronMessageSettings.getQueueName()).thenReturn("test");

        Queue mockQueue = mock(Queue.class);

        when(mockQueue.push(anyString())).thenReturn("OK");
        when(mockQueue.getName()).thenReturn("test");

        Client mockClient = mock(Client.class);
        when(mockClient.queue(anyString())).thenReturn(mockQueue);


        IronMQSender sender = new IronMQSender();

        String resultOfPush = sender.send(mockClient, mockIronMessageSettings);

        Assert.assertEquals("expected a string response", "OK", resultOfPush);

    }


    @Test(expected = IOException.class)
    public void Sender_Queue_Push_Error() throws IOException {

        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds()).thenReturn(1000L);
        when(mockIronMessageSettings.getQueueName()).thenReturn("test");

        Queue mockQueue = mock(Queue.class);


        when(mockQueue.getName()).thenReturn("test");

        Client mockClient = mock(Client.class);
        when(mockClient.queue(anyString())).thenReturn(mockQueue);


        IronMQSender sender = new IronMQSender();


        String resultOfPush = sender.send(mockClient, mockIronMessageSettings);

        Assert.assertNotEquals("OK", resultOfPush);

    }

}



