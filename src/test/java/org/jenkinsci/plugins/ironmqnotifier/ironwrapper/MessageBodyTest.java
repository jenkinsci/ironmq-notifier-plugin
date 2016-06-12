package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import org.junit.Assert;
import org.junit.Test;

import java.util.Date;


public class MessageBodyTest {

    @Test
    public void Check_Message_Body_Fields() {
        MessageBody messageBody = new MessageBody();

        Date testDate = new Date();

        messageBody.setJobName("test");
        messageBody.setBuildResult("failed");
        messageBody.setSubmissionDate(testDate);
        messageBody.setMessageVersion("3.0");


        Assert.assertEquals("test", messageBody.getJobName());
        Assert.assertEquals("failed", messageBody.getBuildResult());
        Assert.assertEquals(testDate, messageBody.getSubmissionDate());
        Assert.assertEquals("3.0", messageBody.getMessageVersion());


    }

    @Test
    public void An_Empty_SubmissionDate_Defaults_to_today_if_set () {
        MessageBody messageBody = new MessageBody();

        Date expectedDate = new Date();

        messageBody.setSubmissionDate(null);

        Assert.assertEquals(expectedDate, messageBody.getSubmissionDate());

    }

    @Test
    public void An_Empty_SubmissionDate_Defaults_to_today_if_null () {
        MessageBody messageBody = new MessageBody();

        Date expectedDate = new Date();

        Assert.assertEquals(expectedDate, messageBody.getSubmissionDate());

    }

    @Test
    public void EmptyMessageVersionReturnsCorrectVersion() {
        MessageBody messageBody = new MessageBody();

        Date testDate = new Date();

        String expectedVersion = "3.0";

        messageBody.setJobName("test");
        messageBody.setBuildResult("failed");
        messageBody.setSubmissionDate(testDate);

        Assert.assertEquals(expectedVersion, messageBody.getMessageVersion());


    }

}
