package org.jenkinsci.plugins.ironmqnotifier;

import hudson.util.FormValidation;
import org.junit.Assert;
import org.junit.Test;

public class IronMQFormValidationsTest {


    @Test
    public void Valid_Queue_Name_Should_Pass() {

        FormValidation testResult;
        FormValidation expectedResult = FormValidation.ok();

        IronMQFormValidations testValidations = new IronMQFormValidations();


        testResult = testValidations.isValidQueueName("testTest");


        Assert.assertEquals(expectedResult, testResult);

    }

    @Test
    public void ExpirySeconds_Greater_Than_Zero_Should_Pass() {

        FormValidation testResult;
        FormValidation expectedResult = FormValidation.ok();

        IronMQFormValidations testValidations = new IronMQFormValidations();


        testResult = testValidations.isValidExpirySeconds(10005L);

        Assert.assertEquals(expectedResult, testResult);

    }

    @Test
    public void ExpirySeconds_Zero_Or_Less_Should_Fail() {

        FormValidation testResult;
        FormValidation unexpectedResult = FormValidation.ok();

        IronMQFormValidations testValidations = new IronMQFormValidations();

        testResult = testValidations.isValidExpirySeconds(0L);

        Assert.assertNotSame(unexpectedResult, testResult);
    }

    @Test
    public void FormValidations_Returns_A_Warning_in_the_event_of_A_Not_Alpha_Name()
    {
        FormValidation testResult;
        FormValidation notexpectedResult = FormValidation.ok();

        IronMQFormValidations testValidations = new IronMQFormValidations();

        testResult = testValidations.isValidQueueName("55");

        Assert.assertNotSame(notexpectedResult, testResult);

    }

}
