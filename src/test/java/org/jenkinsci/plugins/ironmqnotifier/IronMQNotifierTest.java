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

import hudson.tasks.BuildStepMonitor;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IronMQNotifierTest {


    private IronMQNotifier StandardTestNotifier() {
        return new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);
    }

    @Test
    public void CanInitiateNewNotifier() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier);
    }

    @Test
    public void IronMQNotifier_Extends_ANotifier() {
        IronMQNotifier notifier = StandardTestNotifier();
        String result = notifier.getClass().getSuperclass().getName();
        Assert.assertEquals("hudson.tasks.Notifier", result);
    }

    @Test
    public void Notifier_Has_A_ProjectId_Not_Null() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier.getProjectId());

    }

    @Test
    public void Notifier_Has_A_ProjectId_Not_Empty() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertTrue(!notifier.getProjectId().isEmpty());
    }

    @Test
    public void Notifier_Can_Set_A_ProjectId_And_Get_It_Back() {

        String testString = "t";
        IronMQNotifier notifier = StandardTestNotifier();
        notifier.setProjectId(testString);
        Assert.assertEquals(testString, notifier.getProjectId());
    }

    @Test
    public void Notifier_Has_A_Token_Not_Null() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier.getToken());

    }

    @Test
    public void Notifier_Has_A_Token_Not_Empty() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertTrue(!notifier.getToken().isEmpty());

    }

    @Test
    public void Notifier_Can_SetAndRetrieve_Token() {
        IronMQNotifier notifier = StandardTestNotifier();
        final String testString = "7714717174";
        notifier.setToken(testString);
        Assert.assertEquals(testString, notifier.getToken());
    }


    @Test
    public void Notifier_Has_Success_Flag_Success() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier.send_success);

    }

    @Test
    public void Notifier_Has_Success_Flag_Boolean() {

        IronMQNotifier notifier = StandardTestNotifier();
        Object result = notifier.send_success;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean", type);

    }

    @Test
    public void Notifier_Has_Failure_Flag_Boolean() {

        IronMQNotifier notifier = StandardTestNotifier();
        Object result = notifier.send_failure;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean", type);

    }

    @Test
    public void Notifier_Has_Unstable_Flag_Boolean() {

        IronMQNotifier notifier = StandardTestNotifier();
        Object result = notifier.send_unstable;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean", type);

    }

    @Test
    public void Configure_Has_Proper_Fields() {
        IronMQNotifier notifier = StandardTestNotifier();

        Assert.assertNotNull(notifier.getProjectId());
        Assert.assertNotNull(notifier.getToken());
        Assert.assertNotNull(notifier.getQueueName());
        Assert.assertNotNull(notifier.getPreferredServerName());
        Assert.assertNotNull(notifier.send_success);
        Assert.assertNotNull(notifier.send_failure);
        Assert.assertNotNull(notifier.send_unstable);
        Assert.assertNotNull(notifier.getExpirySeconds());

    }

    @Test
    public void Make_Sure_We_Get_Back_An_Expiry_Not_Zero_By_Default() {

        IronMQNotifier notifier
                = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN,
                TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                0L);

        Assert.assertTrue("expiry > 0",
                notifier.getExpirySeconds() > 0);

    }

    @Test
    public void Make_Sure_We_Get_Back_What_We_Set_As_The_Expiry() {

        IronMQNotifier notifier = StandardTestNotifier();

        Assert.assertEquals(TestSettings.EXPIRYSETTINGS, notifier.getExpirySeconds());

        Assert.assertNotEquals(new Long(0), notifier.getExpirySeconds());

    }

    @Test
    public void If_We_Do_Not_Send_A_Server_We_Get_A_Default_Back() {

        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN,
                TestSettings.TESTQUEUENAME,
                "",
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);
        Assert.assertEquals(TestSettings.STANDARDDEFAULTSERVER,
                notifier.getPreferredServerName());

    }

    @Test
    public void Needs_To_Run_After_Finalized_Should_Be_Set() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier.needsToRunAfterFinalized());

    }

    @Test
    public void Creating_Notifier_With_Blank_QueueName_Returns_A_Default() {

        IronMQNotifier notifier
                = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN,
                "",
                "",
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);
        Assert.assertNotSame(0, notifier.getQueueName().length());
    }

    @Test
    public void Can_Set_The_QueueName_Of_A_NotifierProperly() {

        final String testQueueName = "fred";
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, "", "", true, true, true, TestSettings.EXPIRYSETTINGS);
        notifier.setQueueName(testQueueName);
        Assert.assertEquals(testQueueName, notifier.getQueueName());
    }

    @Test
    public void Return_A_Valid_BuildStepMonitorService() {

        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier.getRequiredMonitorService());
        Object monitor = notifier.getRequiredMonitorService();

        Assert.assertTrue(BuildStepMonitor.BUILD == monitor |
                BuildStepMonitor.NONE == monitor |
                BuildStepMonitor.STEP == monitor);

        Object result = monitor.getClass().getSuperclass().getSimpleName();
        Assert.assertEquals("BuildStepMonitor", result);

    }

    @Test
    public void IronMQNotifier_Extends_Publisher() {

        IronMQNotifier notifier = StandardTestNotifier();
        Object check = notifier.getClass().getSuperclass()
                .getSimpleName();
        Assert.assertEquals("Notifier", check);
    }

    @Test
    public void IronMQNotifier_Returns_A_Job_Name() {
        IronMQNotifier notifier = StandardTestNotifier();
        Assert.assertNotNull(notifier.getJobName());
    }

    @Test
    public void Can_Set_The_PreferredServerName_Properly() {

        final String testString = "fred.test.com";
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, "", "", true, true, true, TestSettings.EXPIRYSETTINGS);
        notifier.setPreferredServerName(testString);
        Assert.assertEquals(testString, notifier.getPreferredServerName());
    }

    @Test
    public void Can_Set_The_DEFAULT_PreferredServerName_Properly() {

        final String testString = "fredDefault.test.com";
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, "", "", true, true, true, TestSettings.EXPIRYSETTINGS);
        notifier.setDefaultPreferredServerName(testString);
        Assert.assertEquals(testString, notifier.getDefaultPreferredServerName());
    }

    @Test
    public void The_DEFAULT_Preferred_ServerNameWillBeProperAndNotEmpty() {

        IronConstants ironConstants = new IronConstants();

        final String  testString =  ironConstants.DEFAULT_PREFERRED_SERVER_NAME;

        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, "", "", true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertEquals(testString, notifier.getDefaultPreferredServerName());
    }


    @Test
    public void IronMQNotifierSendFunctionReturnsAZeroIntegerOnNormal() throws IOException {

        IronMQNotifier notifier = mock(IronMQNotifier.class);
        when(notifier.SendMessageToIronMQ()).thenReturn(0);

        int result = notifier.SendMessageToIronMQ();

        Assert.assertEquals(0, result);

    }

    @Test
    public void IronMQNotifierFixesNullDefaultPreferredServerIfNecessary() {

        IronConstants ironConstants = new IronConstants();

        String testString = ironConstants.DEFAULT_PREFERRED_SERVER_NAME;

        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, "", null, true, true, true, TestSettings.EXPIRYSETTINGS);

        String result = notifier.getDefaultPreferredServerName();

        Assert.assertEquals(testString, result);

    }

}