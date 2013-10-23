package org.jenkinsci.plugins.ironmqnotifier.Iron;


import io.iron.ironmq.Client;
import io.iron.ironmq.Message;
import io.iron.ironmq.Queue;

import java.io.IOException;

public class IronMQSender{

         public void Send(final Client client,
                               final MessageSettings messageSettings)

            throws Exception {

        Queue queue = client.queue(messageSettings.getQueueName());

        Message message = new Message();

        IronMQMessage ironMQMessage = new IronMQMessage();

        ironMQMessage.setBuildResult(messageSettings.getBuildResultString());
        ironMQMessage.setJobName(messageSettings.getJobName());

        ironMQMessage.setExpirySeconds(messageSettings.getExpirySeconds());


        message.setBody(ironMQMessage.toJson());

        message.setExpiresIn(messageSettings.getExpirySeconds());
        message.setBody(message.getBody());


        String resultOfQueuePush
                = queue.push(message.getBody(), 0, 0, message.getExpiresIn());

        if(resultOfQueuePush == null
                || resultOfQueuePush.length() == 0) {


            throw new IOException("Not successful in sending message");
        }
    }
}
