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
