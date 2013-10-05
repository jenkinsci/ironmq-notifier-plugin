package org.jenkinsci.plugins.ironmqnotifier;

import hudson.util.FormValidation;
import org.junit.Assert;
import org.junit.Test;

public class IronMQDescriptorClassTest {

    //TODO: I need some help to learn how to test these without forcing static

    @Test
    public void FormCheckForValidQueueNameInDescriptor() {
        FormValidation validationTest;
        FormValidation expectedValidation = FormValidation.ok();

        try {
            validationTest = IronMQDescriptor.doCheckQueueName("testTEST");
        } catch (Exception exception) {
            validationTest = FormValidation.error(exception.getMessage());
        }

        Assert.assertEquals(expectedValidation, validationTest);

    }

    @Test
    public void FormCheckForInValidQueueNameInDescriptor() {

        FormValidation expectedNoToGet = FormValidation.ok();
        FormValidation testObject;

        try {
            testObject = IronMQDescriptor.doCheckQueueName("55");  // force error

        } catch (Exception exception) {

            testObject = FormValidation.error(exception.getMessage());
        }

        Assert.assertTrue(testObject != expectedNoToGet);

    }

    @Test
    public void FormCheckForInValidQueueNameEmptyString() {

        FormValidation expectedNoToGet = FormValidation.ok();
        FormValidation testObject;

        try {
            testObject = IronMQDescriptor.doCheckQueueName("");

        } catch (Exception exception) {

            testObject = FormValidation.error(exception.getMessage());
        }

        Assert.assertTrue(testObject != expectedNoToGet);

    }



}
