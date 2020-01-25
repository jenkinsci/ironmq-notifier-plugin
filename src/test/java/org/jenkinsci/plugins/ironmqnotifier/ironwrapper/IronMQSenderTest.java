/*
 * The MIT License
 *
 * Copyright (c) 2013-2016, Caspar Computer Services Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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

        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        ironMessageSettings.setExpirySeconds(1000L);
        ironMessageSettings.setQueueName("test");

        Queue mockQueue = mock(Queue.class);

        when(mockQueue.push(anyString())).thenReturn("OK");
        when(mockQueue.getName()).thenReturn("test");

        Client mockClient = mock(Client.class);
        when(mockClient.queue("test")).thenReturn(mockQueue);


        IronMQSender sender = new IronMQSender();

        String resultOfPush = sender.send(mockClient, ironMessageSettings);

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

    @Test(expected = IllegalArgumentException.class)
    public void Sender_Queue_Push_Error_Empty_String() throws IOException {

        IronMessageSettings mockIronMessageSettings = mock(IronMessageSettings.class);
        when(mockIronMessageSettings.getExpirySeconds()).thenReturn(1000L);
        when(mockIronMessageSettings.getQueueName()).thenReturn("");

        Queue mockQueue = mock(Queue.class);


        when(mockQueue.getName()).thenReturn("");

        Client mockClient = mock(Client.class);
        when(mockClient.queue(anyString())).thenReturn(mockQueue);


        IronMQSender sender = new IronMQSender();


        String resultOfPush = sender.send(mockClient, mockIronMessageSettings);

        Assert.assertNotEquals("OK", resultOfPush);

    }
}



