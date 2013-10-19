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

/**
 * <p>IronMQNotifier class.</p>
 *
 * @author Mike Caspar
 * @version $Id: $
 */
public class IronMQNotifier extends Notifier{

    private final int defaultPreferredServerPort
            = IronConstants.DEFAULT_PREFERRED_SERVER_PORT;
    public String preferredServerName;
    public boolean send_success;
    public boolean send_failure;
    public boolean send_unstable;
    private String token;
    private long expirySeconds;
    private String projectId;
    private String queueName;
    private String jobName = "";
    private String messageText;

    /**
     * <p>Constructor for IronMQNotifier.</p>
     *
     * @param projectId           a {@link java.lang.String} object.
     * @param token               a {@link java.lang.String} object.
     * @param queueName           a {@link java.lang.String} object.
     * @param preferredServerName a {@link java.lang.String} object.
     * @param send_success        a boolean.
     * @param send_failure        a boolean.
     * @param send_unstable       a boolean.
     * @param expirySeconds       a long.
     */
    @DataBoundConstructor
    public IronMQNotifier( final String projectId,
                           final String token,
                           final String queueName,
                           final String preferredServerName,
                           final boolean send_success,
                           final boolean send_failure,
                           final boolean send_unstable,
                           final long expirySeconds ) {

        this.projectId = projectId;
        this.setToken(token);
        this.queueName = queueName;
        this.send_success = send_success;
        this.send_failure = send_failure;
        this.send_unstable = send_unstable;
        this.preferredServerName = preferredServerName;
        this.setExpirySeconds(expirySeconds);

        adjustDataToAvoidCrashes();

    }

    private void adjustDataToAvoidCrashes() {

        if(this.queueName.trim().length() == 0) {
            this.queueName = IronConstants.DEFAULT_QUEUE_NAME;
        }

        if(this.preferredServerName.trim().length() == 0) {
            this.preferredServerName
                    = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;
        }

        if(this.getExpirySeconds() <= 0) {
            setExpirySeconds(IronConstants.DEFAULT_EXPIRY_SECONDS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IronMQDescriptor getDescriptor() {
        return (IronMQDescriptor) super.getDescriptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean perform( AbstractBuild<?, ?> build,
                            Launcher launcher,
                            BuildListener listener )
            throws InterruptedException, IOException {

        this.jobName = build.getFullDisplayName();

        String result;

        if(build.getResult() == Result.SUCCESS) {
            if(!send_success) {
                return true;
            }
            result = "succeeded";
        } else if(build.getResult() == Result.UNSTABLE) {
            if(!send_unstable) {
                return true;
            }
            result = "was unstable";
        } else if(build.getResult() == Result.FAILURE) {
            if(!send_failure) {
                return true;
            }
            result = "failed";
        } else {
            return true;
        }


        try {

            Client client = new Client(
                    projectId,
                    getToken(),
                    new Cloud("https",
                            preferredServerName,
                            defaultPreferredServerPort));

            Queue queue = client.queue(queueName);

            Message message = new Message();

            IronMQMessage ironMQMessage = new IronMQMessage();
            ironMQMessage.setBuildResult(result);
            ironMQMessage.setJobName(this.jobName);
            ironMQMessage.setExpirySeconds(this.getExpirySeconds());

            message.setBody(ironMQMessage.toJson());

            message.setExpiresIn(this.getExpirySeconds());
            message.setBody(message.getBody());


            String resultOfQueuePush
                    = queue.push(
                    message.getBody(),
                    0,
                    0,
                    message.getExpiresIn());

            if(resultOfQueuePush == null
                    || resultOfQueuePush.length() == 0) {
                build.setResult(Result.FAILURE);
            }

        } catch (Exception ex) {

            build.setResult(Result.FAILURE);

        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean needsToRunAfterFinalized() {
        return true;
    }

    /**
     * <p>Getter for the field <code>jobName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     * @since 1.0.6
     */
    protected String getJobName() {
        return this.jobName;
    }

    /**
     * <p>Getter for the field <code>queueName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public String getQueueName() {
        return this.queueName;
    }

    /**
     * <p>Setter for the field <code>queueName</code>.</p>
     *
     * @param queueName a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public void setQueueName( final String queueName ) {
        this.queueName = queueName;
    }

    /**
     * <p>Getter for the field <code>projectId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public String getProjectId() {
        return this.projectId;
    }

    /**
     * <p>Setter for the field <code>projectId</code>.</p>
     *
     * @param projectId a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public void setProjectId( final String projectId ) {
        this.projectId = projectId;
    }

    /**
     * <p>Getter for the field <code>token</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public String getToken() {
        return token;
    }

    /**
     * <p>Setter for the field <code>token</code>.</p>
     *
     * @param token a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public void setToken( String token ) {
        this.token = token;
    }

    /**
     * <p>Getter for the field <code>expirySeconds</code>.</p>
     *
     * @return a {@link long} object.
     * @since 1.0.6
     */
    public long getExpirySeconds() {
        return expirySeconds;
    }

    /**
     * <p>Setter for the field <code>expirySeconds</code>.</p>
     *
     * @param expirySeconds a {@link long} object.
     * @since 1.0.6
     */
    public void setExpirySeconds( long expirySeconds ) {
        this.expirySeconds = expirySeconds;
    }
}
