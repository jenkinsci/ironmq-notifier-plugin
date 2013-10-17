package org.jenkinsci.plugins.ironmqnotifier;

import hudson.util.FormValidation;

public class IronMQFormValidations {

    public final FormValidation isValidQueueName(final String name) {

        if (isAlpha(name)) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Check Queue Name");
        }

    }

    public final FormValidation isValidExpirySeconds(final Long expirySeconds) {

        if (expirySeconds > 0) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Expiry Should Not be Zero");
        }
    }

    private static boolean isAlpha(final String name) {
        return name.matches("[a-zA-Z]+");

    }

}
