package org.jenkinsci.plugins.ironmqnotifier;

import hudson.tasks.BuildStepMonitor;
import org.junit.Assert;
import org.junit.Test;

public class IronMQNotifierTest {

    @Test
    public void CanInitiateNewNotifier() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier);

    }

    @Test
    public void Notifier_Has_A_ProjectId_Not_Null() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.projectId);

    }

    @Test
    public void Notifier_Has_A_ProjectId_Not_Empty() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertTrue(!notifier.projectId.isEmpty());

    }

    @Test
    public void Notifier_Has_A_Token_Not_Null() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.token);

    }

    @Test
    public void Notifier_Has_A_Token_Not_Empty() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertTrue(!notifier.token.isEmpty());

    }

    @Test
    public void Notifier_Has_Success_Flag_Success() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);
        Assert.assertNotNull(notifier.send_success);


    }

    @Test
    public void Notifier_Has_Success_Flag_Boolean() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);
        Object result = notifier.send_success;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean", type);

    }

    @Test
    public void Notifier_Has_Failure_Flag_Boolean() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);
        Object result = notifier.send_failure;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean", type);

    }

    @Test
    public void Notifier_Has_Unstable_Flag_Boolean() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);
        Object result = notifier.send_unstable;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean", type);

    }

    @Test
    public void Make_Sure_Our_Fields_Are_Public_So_Forms_Can_Update_Them_During_Configuration() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.projectId);
        Assert.assertNotNull(notifier.token);
        Assert.assertNotNull(notifier.queueName);
        Assert.assertNotNull(notifier.preferredServerName);
        Assert.assertNotNull(notifier.send_success);
        Assert.assertNotNull(notifier.send_failure);
        Assert.assertNotNull(notifier.send_unstable);
        Assert.assertNotNull(notifier.expirySeconds);

    }

    @Test
    public void Make_Sure_We_Get_Back_An_Expiry_Not_Zero_By_Default() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, 0);

        Assert.assertTrue("Expiry in seconds must be Greater than zero", notifier.expirySeconds > 0);

    }

    @Test
    public void Make_Sure_We_Get_Back_What_We_Set_As_An_Expiry() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVER, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertEquals(TestSettings.EXPIRYSETTINGS, notifier.expirySeconds);

    }

    @Test
    public void If_We_Do_Not_Send_A_Server_We_Get_A_Default_Back() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, "", true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertEquals(TestSettings.STANDARDDEFAULTSERVER, notifier.preferredServerName);

    }

    @Test
    public void Needs_To_Run_After_Finalized_Should_Be_Set() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, "", true, true, true, TestSettings.EXPIRYSETTINGS);
        Assert.assertNotNull(notifier.needsToRunAfterFinalized());

    }

    @Test
    public void Creating_Notifier_With_Blank_QueueName_Returns_A_Default() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, "", "", true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotSame(0, notifier.queueName.length());
    }

    @Test
    public void Return_A_Valid_BuildStepMonitorService() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, "", true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.getRequiredMonitorService());
        Object monitor = notifier.getRequiredMonitorService();

        Assert.assertTrue(BuildStepMonitor.BUILD == monitor |
                BuildStepMonitor.NONE == monitor |
                BuildStepMonitor.STEP == monitor);

        Object result = monitor.getClass().getSuperclass().getSimpleName().toString();

        Assert.assertEquals("BuildStepMonitor", result);
    }

    @Test
    public void IronMQNotifier_Extends_Notifier() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, "", true, true, true, TestSettings.EXPIRYSETTINGS);

        Object notifierCheck = notifier.getClass().getSuperclass().getSimpleName().toString();
        Assert.assertEquals("Notifier", notifierCheck);

    }

    @Test
    public void IronMQNotifier_Returns_A_Job_Name() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, "", true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.getJobName());
    }

}