/*
 * The MIT License
 *
 * Copyright 2015, 2016 Mike Caspar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
    protected final FormValidation isValidExpirySeconds( final Long expirySeconds ) {

        if(expirySeconds > 0) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Expiry Should Not be Zero");
        }
    }

}
