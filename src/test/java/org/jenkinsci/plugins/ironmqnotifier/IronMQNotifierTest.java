package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Test;

public class IronMQNotifierTest {


    @Test
    public void CanInitiateNewNotifier() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier);
    }

    @Test
    public void Notifier_Has_A_ProjectId_Not_Null() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.projectId);
    }

    @Test
    public void Notifier_Has_A_ProjectId_Not_Empty() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertTrue(!notifier.projectId.isEmpty());
    }


    @Test
    public void Notifier_Has_A_Token_Not_Null() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.token);
    }

    @Test
    public void Notifier_Has_A_Token_Not_Empty() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertTrue(!notifier.token.isEmpty());
    }

    @Test
    public void Notifier_Has_Success_Flag_Success() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);
        Assert.assertNotNull(notifier.send_success);

    }

    @Test
    public void Notifier_Has_Success_Flag_Boolean() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);
        Object result = notifier.send_success;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean",type);

    }

    @Test
    public void Notifier_Has_Failure_Flag_Boolean() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);
        Object result = notifier.send_failure;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean",type);

    }
    @Test
    public void Notifier_Has_Unstable_Flag_Boolean() {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);
        Object result = notifier.send_unstable;
        String type = result.getClass().getSimpleName();
        Assert.assertEquals("Boolean",type);

    }
    @Test
    public void Make_Sure_Our_Fields_Are_Public_So_Forms_Can_Update_Them_During_Configuration()
    {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertNotNull(notifier.projectId);
        Assert.assertNotNull(notifier.token);
        Assert.assertNotNull(notifier.queueName);
        Assert.assertNotNull(notifier.preferredServerName);
        Assert.assertNotNull(notifier.send_success);
        Assert.assertNotNull(notifier.send_failure);
        Assert.assertNotNull(notifier.send_unstable);
        Assert.assertNotNull(notifier.ExpirySeconds);

    }

    @Test
    public void Make_Sure_We_Get_Back_An_Expiry_Not_Zero_By_Default()
    {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, 0);

        Assert.assertTrue("Expiry in seconds must be Greater than zero", notifier.ExpirySeconds > 0);

    }

    @Test
    public void Make_Sure_We_Get_Back_What_We_Set_As_An_Expiry()
    {
        IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME, TestSettings.TESTPREFERREDSERVERNAME, true, true, true, TestSettings.EXPIRYSETTINGS);

        Assert.assertEquals(TestSettings.EXPIRYSETTINGS, notifier.ExpirySeconds );


    }
}
