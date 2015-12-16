package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import io.iron.ironmq.Client;
import io.iron.ironmq.Message;
import io.iron.ironmq.Queue;

import java.io.IOException;

public class IronMQSender {

    public IronMQSender() {
        // placeholder for testing
    }

    public void send(final Client client,
                     final IronMessageSettings ironMessageSettings)
            throws IOException {

        checkMessageParameters(ironMessageSettings);

        Queue queue = client.queue(ironMessageSettings.getQueueName());

        Message message = new Message();

        IronMQMessage ironMQMessage = new IronMQMessage();

        ironMQMessage.setBuildResult(ironMessageSettings.getBuildResultString());
        ironMQMessage.setJobName(ironMessageSettings.getJobName());

        ironMQMessage.setExpirySeconds(ironMessageSettings.getExpirySeconds());


        message.setBody(ironMQMessage.toJson());

        message.setExpiresIn(ironMessageSettings.getExpirySeconds());
        message.setBody(message.getBody());

        String resultOfQueuePush
                = queue.push(message.getBody());


        if (resultOfQueuePush == null
                || resultOfQueuePush.length() == 0) {
            throw new IOException("Not successful in sending message");
        }
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
