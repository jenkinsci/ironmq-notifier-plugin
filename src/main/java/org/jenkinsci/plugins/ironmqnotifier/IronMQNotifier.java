package org.jenkinsci.plugins.ironmqnotifier;

import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import io.iron.ironmq.Client;
import io.iron.ironmq.Cloud;

import org.jenkinsci.plugins.ironmqnotifier.Iron.ClientWrapper;
import org.jenkinsci.plugins.ironmqnotifier.Iron.IronMQSender;
import org.jenkinsci.plugins.ironmqnotifier.Iron.MessageSettings;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * <p>IronMQNotifier class.</p>
 *
 * @author Mike Caspar
 * @version $Id: $
 */
public class IronMQNotifier extends Notifier{


    private static final Logger logger
            = Logger.getLogger(IronPluginImplement.class.getName());

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
    private String resultString = "UNKNOWN";

    private Client client;

    @DataBoundConstructor
    public IronMQNotifier(final String projectId,
                          final String token,
                          final String queueName,
                          final String preferredServerName,
                          final boolean send_success,
                          final boolean send_failure,
                          final boolean send_unstable,
                          final long expirySeconds) {

        this.projectId = projectId;
        this.token = token;
        this.queueName = queueName;
        this.send_success = send_success;
        this.send_failure = send_failure;
        this.send_unstable = send_unstable;
        this.preferredServerName = preferredServerName;
        this.expirySeconds = expirySeconds;


        adjustDataToAvoidCrashes();

    }

    private void adjustDataToAvoidCrashes() {

        if(this.queueName.trim().length() == 0) {
            this.queueName = IronConstants.DEF_QUEUE_NAME;
        }

        if(this.preferredServerName.trim().length() == 0) {
            this.preferredServerName
                    = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;
        }

        if(this.expirySeconds <= 0) {
            setExpirySeconds(IronConstants.DEF_EXPIRY_SEC);
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
    public boolean perform(AbstractBuild<?, ?> build,
                           Launcher launcher,
                           BuildListener listener)
            throws InterruptedException, IOException {

        this.jobName = build.getFullDisplayName();

        if(build.getResult() == Result.SUCCESS) {
            if(!send_success) {
                return true;
            }
            this.resultString = "succeeded";
        } else if(build.getResult() == Result.UNSTABLE) {
            if(!send_unstable) {
                return true;
            }
            this.resultString = "was unstable";
        } else if(build.getResult() == Result.FAILURE) {
            if(!send_failure) {
                return true;
            }
            this.resultString = "failed";
        } else {
            return true;
        }


        try {

            client = generateClientToUse();

            MessageSettings messageSettings = generateMessageSettings();

            IronMQSender sender = new IronMQSender();

            sender.Send(client, messageSettings);

        } catch (Exception ex) {

            logWarning();

            build.setResult(Result.UNSTABLE);

        }

        return true;
    }

    private Client generateClientToUse() {
        return new ClientWrapper (
                                         this.projectId,
                                          this.token,
                                            new Cloud(IronConstants.DEF_PREFERRED_SERVER_SCHEME,
                                            this.preferredServerName,
                                            IronConstants.DEF_PREFERRED_SERVER_PORT)
                                             );
    }



    private MessageSettings generateMessageSettings() {
        MessageSettings messageSettings = new MessageSettings();
        messageSettings.setExpirySeconds(this.expirySeconds);
        messageSettings.setJobName(this.jobName);
        messageSettings.setBuildResultString (this.resultString);
        return messageSettings;
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
    public void setQueueName(final String queueName) {
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
    public void setProjectId(final String projectId) {
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
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * <p>Getter for the field <code>expirySeconds</code>.</p>
     *
     * @return a {@link long} object.
     * @since 1.0.6
     */
    public long getExpirySeconds() {
        return this.expirySeconds;
    }

    /**
     * <p>Setter for the field <code>expirySeconds</code>.</p>
     *
     * @param expirySeconds a {@link long} object.
     * @since 1.0.6
     */
    public void setExpirySeconds(long expirySeconds) {
        this.expirySeconds = expirySeconds;
    }


    /**
     * <p>Setter for the field <code>preferredServerName</code>.</p>
     *
     * @param preferredServerName a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public void setPreferredServerName(final String preferredServerName) {
        this.preferredServerName = preferredServerName;
    }

    /**
     * <p>Getter for the field <code>preferredServerName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public String getPreferredServerName() {
        return this.preferredServerName;
    }

    private void logWarning() {

        logger.warning("Check Configuration Settings");

    }
}
