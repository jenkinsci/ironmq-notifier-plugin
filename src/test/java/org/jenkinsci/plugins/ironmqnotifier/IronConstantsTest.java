package org.jenkinsci.plugins.ironmqnotifier;

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


import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import hudson.util.FormValidation;

public class IronConstantsTest {

    private IronConstants ironConstants = new IronConstants();

    @Before
    public void LoadStandardClass()
    {
        ironConstants = new IronConstants();

    }

    @Test
    public void Expiry_Seconds_Not_Null() {
        Assert.assertNotNull(ironConstants.DEF_EXPIRY_SEC);
     }

    @Test
    public void DefaultQueueName_Not_Null() {
        Assert.assertNotNull(ironConstants.DEF_QUEUE_NAME);
    }

    @Test
    public void DefaultPreferredServerName_Not_Null() {
        Assert.assertNotNull(ironConstants.DEFAULT_PREFERRED_SERVER_NAME);
    }

    @Test
    public void DefaultServerForVersion3IsCorrect() {
        Assert.assertEquals("mq-aws-us-east-1-1.iron.io", ironConstants.DEFAULT_PREFERRED_SERVER_NAME);
    }


    @Test
    public void Expiry_Seconds_Valid() {
        Assert.assertNotEquals(Long.valueOf(0), ironConstants.DEF_EXPIRY_SEC);
        Assert.assertTrue(ironConstants.DEF_EXPIRY_SEC > 60L);
    }


    @Test
    public void QueueNameDefaultIsValid() {
        IronMQFormValidations validationForms =  new IronMQFormValidations();
        FormValidation validationResult =
                validationForms.isValidQueueName(ironConstants.DEF_QUEUE_NAME);

        Assert.assertEquals(FormValidation.ok(), validationResult);
    }

    @Test
    public void DefaultExpiryValidationPasses() {
        IronMQFormValidations validationForms =  new IronMQFormValidations();
        FormValidation validationResult =
                validationForms.isValidExpirySeconds(ironConstants.DEF_EXPIRY_SEC);
        Assert.assertEquals(FormValidation.ok(), validationResult);
    }

    @Test
    public void DefaultMessageVersion() {
        Assert.assertNotNull(ironConstants.DEF_MESSAGE_VERSION);
    }

    @Test
    public void IronConstantsConstructorTest()
    {
        Assert.assertNotNull(ironConstants);
    }
}
