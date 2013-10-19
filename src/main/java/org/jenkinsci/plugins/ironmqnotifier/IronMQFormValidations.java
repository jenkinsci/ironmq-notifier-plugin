package org.jenkinsci.plugins.ironmqnotifier;

import hudson.util.FormValidation;

/**
 * <p>IronMQFormValidations class.</p>
 *
 * @author Mike Caspar
 * @version $Id: $
 */
class IronMQFormValidations{

    /**
     * <p>isAlpha.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link boolean} object.
     */
    protected static boolean isAlpha( final String name ) {
        return name.matches("[a-zA-Z]+");

    }

    /**
     * <p>isValidQueueName.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link hudson.util.FormValidation} object.
     */
    protected final FormValidation isValidQueueName( final String name ) {

        if(isAlpha(name)) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Check Queue Name");
        }

    }

    /**
     * <p>isValidExpirySeconds.</p>
     *
     * @param expirySeconds a long.
     * @return a {@link hudson.util.FormValidation} object.
     */
    protected final FormValidation isValidExpirySeconds( final long expirySeconds ) {

        if(expirySeconds > 0) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Expiry Should Not be Zero");
        }
    }

}
