package org.jenkinsci.plugins.ironmqnotifier;

import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.junit.Assert;
import org.junit.Test;
import hudson.util.FormValidation;

public class IronConstantsTest {

    @Test
    public void Expiry_Seconds_Not_Null() {
        Assert.assertNotNull(IronConstants.DEF_EXPIRY_SEC);
     }

    @Test
    public void DefaultQueueName_Not_Null() {
        Assert.assertNotNull(IronConstants.DEF_QUEUE_NAME);
    }

    @Test
    public void DefaultPreferredServerName_Not_Null() {
        Assert.assertNotNull(IronConstants.DEFAULT_PREFERRED_SERVER_NAME);
    }

    @Test
    public void DefaultPreferredServerPort_Valid_And_NotNull() {
        Assert.assertNotNull(IronConstants.DEF_PREFERRED_SERVER_PORT);
        Assert.assertNotEquals(0,IronConstants.DEF_PREFERRED_SERVER_PORT);
    }

    @Test
    public void DefaultPreferredServerScheme_Valid_And_NotNull() {
        Assert.assertNotNull(IronConstants.DEF_PREFERRED_SERVER_SCHEME);
        Assert.assertNotEquals(0,IronConstants.DEF_PREFERRED_SERVER_SCHEME);
    }

    @Test
    public void Expiry_Seconds_Valid() {
        Assert.assertNotEquals(0L, IronConstants.DEF_EXPIRY_SEC);
        Assert.assertTrue(IronConstants.DEF_EXPIRY_SEC > 60L);
    }

    @Test
    public void QueueNameDefaultIsValid() {
        IronMQFormValidations validationForms =  new IronMQFormValidations();
        FormValidation validationResult =
                validationForms.isValidQueueName(IronConstants.DEF_QUEUE_NAME);

        Assert.assertEquals(FormValidation.ok(), validationResult);
    }

    @Test
    public void DefaultExpiryValidationPasses() {
        IronMQFormValidations validationForms =  new IronMQFormValidations();
        FormValidation validationResult =
                validationForms.isValidExpirySeconds(IronConstants.DEF_EXPIRY_SEC);
        Assert.assertEquals(FormValidation.ok(), validationResult);
    }

    @Test
    public void IronConstantsConstructorTest()
    {
        Assert.assertNotNull(new IronConstants());
    }
}
