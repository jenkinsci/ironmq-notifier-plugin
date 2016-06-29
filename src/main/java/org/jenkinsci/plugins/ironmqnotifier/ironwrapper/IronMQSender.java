/*
 * The MIT License
 *
 * Copyright 2015, 2016 Mike Caspar
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
import io.iron.ironmq.Message;
import io.iron.ironmq.Queue;

import java.io.IOException;

public class IronMQSender {

    public IronMQSender() {
        // placeholder for testing
    }

    public String send(final Client client,
                       final IronMessageSettings ironMessageSettings)
            throws IOException, IllegalArgumentException {

        checkMessageParameters(ironMessageSettings);

        Queue queue = client.queue(ironMessageSettings.getQueueName());


        Message message = createMessageObject(ironMessageSettings);

        String resultOfQueuePush = queue.push(message.getBody());


        if (resultOfQueuePush == null
                || resultOfQueuePush.length() == 0) {
            throw new IOException("Not successful in sending message");
        } else {
            return resultOfQueuePush;
        }
    }

    private Message createMessageObject(IronMessageSettings ironMessageSettings) {

        Message message = new Message();

        IronMQMessage ironMQMessage = new IronMQMessage();

        ironMQMessage.setBuildResult(ironMessageSettings.getBuildResultString());

        ironMQMessage.setJobName(ironMessageSettings.getJobName());

        ironMQMessage.setExpirySeconds(ironMessageSettings.getExpirySeconds());


        message.setBody(ironMQMessage.toJson());

        message.setExpiresIn(ironMessageSettings.getExpirySeconds());
        message.setBody(message.getBody());

        return message;
    }

    private void checkMessageParameters(final IronMessageSettings ironMessageSettings) {

        if (ironMessageSettings.getExpirySeconds() == 0) {
            throw new IllegalArgumentException("expiry seconds Zero exception");
        }

        String queueString = ironMessageSettings.getQueueName();

        if (queueString == null || queueString.length() == 0) {
            throw new IllegalArgumentException("queueName is invalid exception");
        }


    }

}
