package org.jenkinsci.plugins.ironmqnotifier.send;


import org.jenkinsci.plugins.ironmqnotifier.IronConstants;
import org.junit.Assert;
import org.junit.Test;


public class MessageSettingsTest{





    @Test
    public void Message_SettingsStoresExpiryField()
    {
        MessageSettings messageSettings = new MessageSettings();
        long testLong = 1000L;
        messageSettings.setExpirySeconds(testLong);
        Assert.assertEquals(testLong, messageSettings.getExpirySeconds());

    }

    @Test
    public void MessageSettings_Contains_ResultofBuild_String()
    {
        MessageSettings messageSettings = new MessageSettings();
        String testBuildResult = "FAILED";
        messageSettings.setBuildResultString(testBuildResult);
        Assert.assertEquals(testBuildResult, messageSettings.getBuildResultString());
    }

    @Test
    public void MessageSettings_Returns_Default_Of_Unknown_If_Not_Set()
    {
        MessageSettings messageSettings = new MessageSettings();
        Assert.assertEquals("UNKNOWN", messageSettings.getJobName());
    }

    @Test
    public void MessageSettings_Contains_JobName()
    {
        MessageSettings messageSettings = new MessageSettings();
        String testString = "testJob";
        messageSettings.setJobName(testString);
        Assert.assertEquals(testString, messageSettings.getJobName());
    }

    @Test
    public void MessageSettings_Return_Default_QueueName_If_Not_Set()
    {
        MessageSettings messageSettings = new MessageSettings();
        Assert.assertEquals(IronConstants.DEF_QUEUE_NAME, messageSettings.getQueueName());
    }

    @Test
    public void MessageSettings_Returns_Proper_QueueName()
    {
        MessageSettings messageSettings = new MessageSettings();
        String testString = "testingName";
        messageSettings.setQueueName(testString);
        Assert.assertEquals(testString, messageSettings.getQueueName());
    }

    @Test(expected =  IllegalArgumentException.class)
    public void Message_Settings_Throws_Arg_Exception_OnNull() {
        MessageSettings messageSettings = new MessageSettings();
        String testString = null;
        messageSettings.setQueueName(testString);

    }


}

