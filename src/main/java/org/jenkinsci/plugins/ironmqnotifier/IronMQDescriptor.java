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
/**
 * <p>IronMQDescriptor class.</p>
 *
 * @author Mike Caspar
 * @version $Id: $
 */
public class IronMQDescriptor extends BuildStepDescriptor<Publisher> {

    private final long defaultExpirySeconds
            = IronConstants.DEFAULT_EXPIRY_SECONDS;
    private final String defaultPreferredServerName
            = IronConstants.DEFAULT_PREFERRED_SERVER_NAME;
    private final String defaultQueueName
            = IronConstants.DEFAULT_QUEUE_NAME;

    /**
     * <p>Constructor for IronMQDescriptor.</p>
     */
    public IronMQDescriptor() {
        super(IronMQNotifier.class);
        load();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return "Send Message to IronMQ";
    }

    /** {@inheritDoc} */
    @Override
    public boolean configure( StaplerRequest req,
                              JSONObject formData ) throws FormException {
        save();
        return super.configure(req, formData);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isApplicable( @SuppressWarnings ("rawtypes")
                                     Class<? extends AbstractProject> arg0 ) {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param value a {@link java.lang.String} object.
     * @return a {@link hudson.util.FormValidation} object.
     */
    FormValidation doCheckQueueName(
            @QueryParameter final String value ) {

        IronMQFormValidations validations = new IronMQFormValidations();

        FormValidation validationReturn;

        if(value == null) {
            validationReturn = validations.isValidQueueName("");
        }

        else
        {
            validationReturn = validations.isValidQueueName(value);
        }


        return validationReturn;

    }

    /**
     * {@inheritDoc}
     *
     * @param value a long.
     * @return a {@link hudson.util.FormValidation} object.
     */
    FormValidation doCheckExpirySeconds(
            @QueryParameter final long value ) {

        IronMQFormValidations validations = new IronMQFormValidations();

        FormValidation validationReturn;

        validationReturn = validations.isValidExpirySeconds(value);

        return validationReturn;

    }

}
