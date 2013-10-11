package org.jenkinsci.plugins.ironmqnotifier;

import hudson.util.FormValidation;

public class IronMQFormValidations {

    public FormValidation isValidQueueName(String name) {

        if (isAlpha(name)) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Check Queue Name");
        }

    }

    public FormValidation isValidExpirySeconds(Long expirySeconds) {

        if (expirySeconds > 0) {
            return FormValidation.ok();
        } else
            return FormValidation.warning("Expiry Should Not be Zero");
    }

    private static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");

    }

}
