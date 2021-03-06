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


import hudson.model.Result;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;


public class SendDeciderTest {

    /**
     * JUnit rule which instantiates a local Jenkins instance with our plugin installed.
     */
    @Rule
    public JenkinsRule jenkins = new JenkinsRule();


    @Test
    public void IfSuccessShouldSendIfRequested() {

         Result buildResult = Result.SUCCESS;

         IronMQNotifier ironMQNotifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                 TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                 TestSettings.TESTPREFERREDSERVER,
                 true,
                 true,
                 true,
                 TestSettings.EXPIRYSETTINGS);


         ironMQNotifier.send_success = true;
         ironMQNotifier.send_failure = true;
         ironMQNotifier.send_unstable = true;


        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertTrue("should be true based on success requested and set", shouldISend);

    }

    @Test
    public void IfSuccessShouldNotSendIfDeclined() {

        Result buildResult = Result.SUCCESS;

        IronMQNotifier ironMQNotifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);

        ironMQNotifier.send_success = false;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = true;


        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertFalse("should be false based on success declined and set", shouldISend);

    }

    @Test
    public void IfFailedShouldSendIfRequested() {

        Result buildResult = Result.FAILURE;

        IronMQNotifier ironMQNotifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);

        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = true;



        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertTrue("should be true based on failed requested and set", shouldISend);

    }

    @Test
    public void IfFailedShouldNotSendIfDeclined() {

        Result buildResult = Result.FAILURE;

        IronMQNotifier ironMQNotifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);

        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = false;
        ironMQNotifier.send_unstable = true;


        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertFalse("should be false based on success declined and set", shouldISend);

    }

    @Test
    public void IfUnstableShouldSendIfRequested() {


        Result buildResult = Result.UNSTABLE;

        IronMQNotifier ironMQNotifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);


        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = true;


        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertTrue("should be true based on unstable requested and set", shouldISend);

    }

    @Test
    public void IfUnstableShouldNotSendIfDeclined() {

        Result buildResult = Result.UNSTABLE;

        IronMQNotifier ironMQNotifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);

        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = false;


        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertFalse("should be false based on success declined and set", shouldISend);

    }

}

