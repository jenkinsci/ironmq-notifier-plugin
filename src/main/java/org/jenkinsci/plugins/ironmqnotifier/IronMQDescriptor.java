package org.jenkinsci.plugins.ironmqnotifier;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

@Extension
public class IronMQDescriptor extends BuildStepDescriptor<Publisher> {

    private final long default_expirySeconds = 806400;
    private final String default_preferredServerName = "mq-rackspace-ord.iron.io";

    public IronMQDescriptor() {
        super(IronMQNotifier.class);
        load();
    }

    @Override
    public String getDisplayName() {
        return "Send Message to IronMQ";
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
        save();
        return super.configure(req, formData);
    }

    @Override
    public boolean isApplicable(@SuppressWarnings("rawtypes") Class<? extends AbstractProject> arg0) {
        return true;
    }

    @Override
    public IronMQNotifier newInstance(StaplerRequest req, JSONObject formData) throws FormException {

        String projectId = formData.optString("projectId");
        String tokenID = formData.optString("token");
        String queueName = formData.optString("queueName");
        String preferredServerName = formData.optString("preferredServerName", default_preferredServerName);
        boolean success = formData.optBoolean("send_success");
        boolean failure = formData.optBoolean("send_failure");
        boolean unstable = formData.optBoolean("send_unstable");
        long expirySeconds = formData.optLong("expirySeconds", default_expirySeconds);

        return new IronMQNotifier(projectId, tokenID, queueName, preferredServerName, success, failure, unstable, expirySeconds);
    }

    public FormValidation doCheckQueueName(@QueryParameter String value) {

        IronMQFormValidations validations = new IronMQFormValidations();

        FormValidation validationReturn;

        if (value == null) { value = ""; }

        validationReturn = validations.isValidQueueName(value);

        return validationReturn;

    }

}