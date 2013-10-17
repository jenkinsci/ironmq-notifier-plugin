package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Test;
import hudson.util.FormValidation;

public class IronConstantsTest {

    @Test
    public void Expiry_Seconds_Not_Null() {
        Assert.assertNotNull(IronConstants.DEFAULT_EXPIRY_SECONDS);
     }

    @Test
    public void DefaultQueueName_Not_Null() {
        Assert.assertNotNull(IronConstants.DEFAULT_QUEUE_NAME);
    }

    @Test
    public void DefaultPreferredServerName_Not_Null() {
        Assert.assertNotNull(IronConstants.DEFAULT_PREFERRED_SERVER_NAME);
    }

    @Test
    public void DefaultPreferredServerPort_Valid_And_NotNull() {
        Assert.assertNotNull(IronConstants.DEFAULT_PREFERRED_SERVER_PORT);
        Assert.assertNotEquals(0,IronConstants.DEFAULT_PREFERRED_SERVER_PORT);
    }


    @Test
    public void Expiry_Seconds_Valid() {
        Assert.assertNotEquals(0L, IronConstants.DEFAULT_EXPIRY_SECONDS);
        Assert.assertTrue(IronConstants.DEFAULT_EXPIRY_SECONDS > 60L);
    }

    @Test
    public void QueueNameDefaultIsValid() {
        IronMQFormValidations validationForms =  new IronMQFormValidations();
        FormValidation validationResult =
                validationForms.isValidQueueName(IronConstants.DEFAULT_QUEUE_NAME);

        Assert.assertEquals(FormValidation.ok(), validationResult);
    }

    @Test
    public void DefaultExpiryValidationPasses() {
        IronMQFormValidations validationForms =  new IronMQFormValidations();
        FormValidation validationResult =
                validationForms.isValidExpirySeconds(IronConstants.DEFAULT_EXPIRY_SECONDS);
        Assert.assertEquals(FormValidation.ok(), validationResult);
    }

    @Test
    public void IronConstantsConstructorTest()
    {
        Assert.assertNotNull(new IronConstants());
    }
}
