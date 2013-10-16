package org.jenkinsci.plugins.ironmqnotifier;

import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import io.iron.ironmq.Client;
import io.iron.ironmq.Cloud;
import io.iron.ironmq.Message;
import io.iron.ironmq.Queue;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

public class IronMQNotifier extends Notifier {

    private final String default_preferredServerName = "mq-rackspace-ord.iron.io";
    private final String default_queueName = "Jenkins";
    public String projectId;
    public String token;
    public String queueName;
    public String preferredServerName;
    public boolean send_success;
    public boolean send_failure;
    public boolean send_unstable;
    public long expirySeconds;
    private String jobName = "";
    private String messageText;

    @DataBoundConstructor
    public IronMQNotifier(String projectId,
                          String token,
                          String queueName,
                          String preferredServerName,
                          boolean send_success,
                          boolean send_failure,
                          boolean send_unstable,
                          long expirySeconds) {

        this.projectId = projectId;
        this.token = token;
        this.queueName = queueName;
        this.send_success = send_success;
        this.send_failure = send_failure;
        this.send_unstable = send_unstable;
        this.preferredServerName = preferredServerName;
        this.expirySeconds = expirySeconds;

        AdjustDataToAvoidCrashes();

    }

    private void AdjustDataToAvoidCrashes() {

        if (this.queueName.trim().length() == 0) {
            this.queueName = IronConstants.DEFAULT_QUEUE_NAME;
        }

        if (this.preferredServerName.trim().length() == 0) {
            this.preferredServerName = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;
        }

        if (this.expirySeconds <= 0) {
            expirySeconds = IronConstants.DEFAULT_EXPIRY_SECONDS;
        }
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }

    @Override
    public IronMQDescriptor getDescriptor() {
        return (IronMQDescriptor) super.getDescriptor();
    }

    @Override
    public boolean perform(@SuppressWarnings("rawtypes")
                           AbstractBuild build,
                           Launcher launcher,
                           BuildListener listener)
            throws InterruptedException, IOException {

        jobName = build.getFullDisplayName();

        String result;

        if (build.getResult() == Result.SUCCESS) {
            if (!send_success) {
                return true;
            }
            result = "succeeded";
        } else if (build.getResult() == Result.UNSTABLE) {
            if (!send_unstable) {
                return true;
            }
            result = "was unstable";
        } else if (build.getResult() == Result.FAILURE) {
            if (!send_failure) {
                return true;
            }
            result = "failed";
        } else {
            return true;
        }

        if (preferredServerName.trim().length() == 0) {
            preferredServerName = default_preferredServerName;
        }


        try {

            Client client = new Client(
                    projectId,
                    token,
                    new Cloud("https", preferredServerName, 443));

            Queue queue = client.queue(queueName);

            Message message = new Message();

            IronMQMessage ironMQMessage = new IronMQMessage();
            ironMQMessage.setBuildResult(result);
            ironMQMessage.setJobName(this.jobName);
            ironMQMessage.setExpirySeconds(this.expirySeconds);

            message.setBody(ironMQMessage.toJson());

            message.setExpiresIn(this.expirySeconds);
            message.setBody(message.getBody());


            String resultOfQueuePush = queue.push(message.getBody(), 0, 0, message.getExpiresIn());

            if (resultOfQueuePush == null || resultOfQueuePush.length() == 0) {
                build.setResult(Result.FAILURE);
            }

        } catch (Exception ex) {

            build.setResult(Result.FAILURE);

        }

        return true;
    }

    @Override
    public boolean needsToRunAfterFinalized() {
        return true;
    }

    public String getJobName() {
        return this.jobName;
    }
}
