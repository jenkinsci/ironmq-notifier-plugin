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


}

