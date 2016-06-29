/*
 * The MIT License
 *
 * Copyright (c) 2013-2016, Caspar Computer Services Inc.
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

package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import org.junit.Assert;
import org.junit.Test;


public class IronMessageSettingsTest {


    @Test
    public void Message_SettingsStoresExpiryField() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        Long testLong = 1000L;
        ironMessageSettings.setExpirySeconds(testLong);
        Assert.assertEquals(testLong, ironMessageSettings.getExpirySeconds());

    }

    @Test
    public void MessageSettings_Contains_ResultofBuild_String() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        String testBuildResult = "FAILED";
        ironMessageSettings.setBuildResultString(testBuildResult);
        Assert.assertEquals(testBuildResult, ironMessageSettings.getBuildResultString());
    }

    @Test
    public void MessageSettings_Returns_Default_Of_Unknown_If_Not_Set() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        Assert.assertEquals("UNKNOWN", ironMessageSettings.getJobName());
    }

    @Test
    public void MessageSettings_Contains_JobName() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        String testString = "testJob";
        ironMessageSettings.setJobName(testString);
        Assert.assertEquals(testString, ironMessageSettings.getJobName());
    }

    @Test
    public void MessageSettings_Return_Default_QueueName_If_Not_Set() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        IronConstants ironConstants = new IronConstants();

        Assert.assertEquals(ironConstants.DEF_QUEUE_NAME, ironMessageSettings.getQueueName());
    }

    @Test
    public void MessageSettings_Returns_Proper_QueueName() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        String testString = "testingName";
        ironMessageSettings.setQueueName(testString);
        Assert.assertEquals(testString, ironMessageSettings.getQueueName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Message_Settings_Throws_Arg_Exception_OnNull() {
        IronMessageSettings ironMessageSettings = new IronMessageSettings();
        ironMessageSettings.setQueueName(null);

    }

    @Test
    public void IronMessageSettingsCreatedWithProperData() {

        String queueName = "testQueue";
        String jobName = "jobName";
        String resultString = "SUCCESS";
        Long expirySeconds = 80601L;

        IronMessageSettings ironMessageSettings = new IronMessageSettings(jobName, resultString, queueName, expirySeconds );

        Assert.assertEquals(queueName, ironMessageSettings.getQueueName());
        Assert.assertEquals(jobName, ironMessageSettings.getJobName());
        Assert.assertEquals(resultString, ironMessageSettings.getBuildResultString());
        Assert.assertEquals(expirySeconds, ironMessageSettings.getExpirySeconds());

    }


}

