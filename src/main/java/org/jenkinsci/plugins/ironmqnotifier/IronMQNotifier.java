package org.jenkinsci.plugins.ironmqnotifier;

import hudson.AbortException;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.*;
import hudson.util.FormValidation;
import io.iron.ironmq.Client;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.apache.commons.io.DirectoryWalker;
import org.apache.log4j.Logger;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.ClientBuilder;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronMQSender;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronMessageSettings;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;

/**
 * <p>IronMQNotifier class.</p>
 *
 * @author Mike Caspar
 * @version $Id: $
 */

public class IronMQNotifier extends Notifier implements SimpleBuildStep {

    private static final Logger LOGGER
            = Logger.getLogger("IronMQNotifier");

    private String preferredServerName;
    private String defaultPreferredServerName;

    public boolean send_success;
    public boolean send_failure;
    public boolean send_unstable;

    private String token;
    private long expirySeconds;
    private String projectId;
    private String queueName;
    private String jobName = "";

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


    @Override
    public void perform(Run<?,?> build, FilePath workspace, Launcher launcher, TaskListener listener) throws AbortException {
        // This is where you 'build' the project.
        // Since this is a dummy, we just say 'hello world' and call that a build.

        // This also shows how you can consult the global configuration of the builder
        String logMe = getDescriptor().getDisplayName();

            listener.getLogger().println(logMe + " executed");

                this.jobName = build.getFullDisplayName();

        if (build.getResult() == Result.SUCCESS) {
            if (!send_success) {
                //  return true;
            }
            this.resultString = "succeeded";
        } else if (build.getResult() == Result.UNSTABLE) {
            if (!send_unstable) {
                // return true;
            }
            this.resultString = "was unstable";
        } else if (build.getResult() == Result.FAILURE) {
            if (!send_failure) {
                // return true;
            }
            this.resultString = "failed";
        } else {
            // return true;
        }


        try {

            SendMessageToIronMQ();

        } catch (Exception ex) {

            String errorMessage = "Check Configuration Settings - " + ex.getMessage();

            listener.getLogger().println(errorMessage);

            throw new AbortException(errorMessage);

        }


    }

    private void adjustDataToAvoidCrashes() {

        if (this.queueName == null) {
              this.queueName = "";
        }

        if (this.queueName.trim().length() == 0) {
            this.queueName = IronConstants.DEF_QUEUE_NAME;
        }

        if (this.preferredServerName == null)    {
            this.preferredServerName = "" ;
        }

        if (this.preferredServerName.trim().length() == 0) {
            this.preferredServerName
                    = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;
        }

        if (this.expirySeconds <= 0) {
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
     * Descriptor for {@link HelloWorldBuilder}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     *
     * <p>
     * See <tt>src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
     * for the actual HTML fragment for the configuration screen.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }


    private void SendMessageToIronMQ() throws IOException {

        ClientBuilder builder = new ClientBuilder(this.projectId,
                this.token, this.preferredServerName);

        client = builder.createClient();

        IronMessageSettings ironMessageSettings = generateMessageSettings();

        IronMQSender sender = new IronMQSender();

        sender.send(client, ironMessageSettings);
    }


    private IronMessageSettings generateMessageSettings() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        ironMessageSettings.setExpirySeconds(this.expirySeconds);
        ironMessageSettings.setJobName(this.jobName);
        ironMessageSettings.setBuildResultString(this.resultString);
        ironMessageSettings.setQueueName(this.queueName);
        return ironMessageSettings;
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
    public String getJobName() {
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
     * @return a {@link java.lang.Long} object.
     */
    public Long getExpirySeconds() {


        return this.expirySeconds;
    }

    /**
     * <p>Setter for the field <code>expirySeconds</code>.</p>
     *
     * @param expirySeconds a {@link java.lang.Long} object.
     */
    public void setExpirySeconds(Long expirySeconds) {
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

    /**
     * <p>Getter for the field <code>defaultPreferredServer</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public String getDefaultPreferredServerName() {

        if (this.defaultPreferredServerName == null) {
            this.defaultPreferredServerName = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;

        }
        return this.defaultPreferredServerName;
    }

    /**
     * <p>Setter for the field <code>defaultPreferredServerName</code>.</p>
     *
     * @param defaultPreferredServerName a {@link java.lang.String} object.
     * @since 1.0.6
     */
    public void setDefaultPreferredServerName(final String defaultPreferredServerName) {

        this.defaultPreferredServerName = defaultPreferredServerName;

    }




    // ...............................................  Descriptor .............................................


    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        private String defaultPreferredServerName = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;
        private String defaultProjectId = "";
        private String defaultToken = "";
        private String defaultQueueName = IronConstants.DEF_QUEUE_NAME;
        private Long defaultExpirySeconds = IronConstants.DEF_EXPIRY_SEC;


        public DescriptorImpl() {
            load();
        }

        @Override
        public String getDisplayName() {

            return Messages.IronMQNotifier_DisplayName();

        }

//        @Override
//        public Publisher newInstance(StaplerRequest req, JSONObject formData)
//                throws FormException {
//            return super.newInstance(req, formData);
//        }


        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        public String getDefaultPreferredServerName() {
            return defaultPreferredServerName;
        }

        public String getDefaultProjectId() {
            return defaultProjectId;
        }

        public String getDefaultToken() {
            return defaultToken;
        }

        public String getDefaultQueueName() {
            return defaultQueueName;
        }

        public Long getDefaultExpirySeconds() {
            return defaultExpirySeconds;
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {

            JSONObject json = formData.getJSONObject("ironmqNotifier");

            defaultPreferredServerName = json.getString("defaultPreferredServerName");
            defaultProjectId = json.getString("defaultProjectId");
            defaultToken = json.getString("defaultToken");
            defaultQueueName = json.getString("defaultQueueName");
            try {
                defaultExpirySeconds = json.getLong("defaultExpirySeconds");
            } catch (Exception exception) {
                defaultExpirySeconds = IronConstants.DEF_EXPIRY_SEC;
            }

            save();
            return super.configure(req,formData);
        }


        public FormValidation doCheckQueueName(@QueryParameter final String value) {

            IronMQFormValidations validations = new IronMQFormValidations();

            hudson.util.FormValidation validationReturn;

            if (value == null) {
                validationReturn = validations.isValidQueueName("");
            } else {
                validationReturn = validations.isValidQueueName(value);
            }


            return validationReturn;

        }

        public FormValidation doCheckExpirySeconds(@QueryParameter final Long value) {

            IronMQFormValidations validations = new IronMQFormValidations();

            return validations.isValidExpirySeconds(value);

        }

    }


}

