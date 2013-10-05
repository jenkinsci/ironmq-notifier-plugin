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
        String preferredServer = formData.optString("preferredServerName");
        boolean success = formData.optBoolean("send_success");
        boolean failure = formData.optBoolean("send_failure");
        boolean unstable = formData.optBoolean("send_unstable");


        return new IronMQNotifier(projectId, tokenID, queueName, preferredServer, success, failure, unstable);
    }

   /*                      Form Validation Logic Goes Here                */


    public static FormValidation doCheckQueueName(@QueryParameter String value) {
        if (isValidQueueName(value)) return FormValidation.ok();
        else return FormValidation.error("QueueName must be Alpha characters only");
    }

    public static FormValidation doCheckPreferredServerName(@QueryParameter String value) {
        if (isValidServerName(value)) return FormValidation.ok();
        else return FormValidation.error("Server Name cannot be empty");
    }

    private static boolean isValidQueueName(String name) {
        return !name.isEmpty() && isAlpha(name);

    }

    private static boolean isValidServerName(String name) {
        return !name.isEmpty() ;

    }
    private static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}