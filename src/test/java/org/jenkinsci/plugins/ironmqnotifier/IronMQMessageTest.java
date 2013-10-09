package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class IronMQMessageTest {

    @Test
    public void Can_Create_A_Jenkins_IronMQ_Message() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotNull(msg);
    }

    @Test
    public void IronMQ_Message_Contains_A_JobName() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotNull(msg.getJobName());
    }

    @Test
    public void IronMQ_Message_Can_Set_A_JobName_And_Return_It() {
        IronMQMessage msg = new IronMQMessage();
        msg.setJobName("testJob");
        Assert.assertEquals("testJob", msg.getJobName());
    }

    @Test
    public void IronMQ_Message_Message_Is_Not_Null() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotNull(msg.getBuildResult());
    }

    @Test
    public void IronMQ_Message_Message_Stores_Build_Result() {
        IronMQMessage msg = new IronMQMessage();
        msg.setBuildResult("testResult");
        Assert.assertEquals("testResult", msg.getBuildResult());
    }

    @Test
    public void IronMQ_Message_Expiry_Is_Not_Null() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotNull(msg.getExpirySeconds());
    }

    @Test
    public void IronMQ_Message_Expiry_Can_Be_Set_And_Returned() {
        IronMQMessage msg = new IronMQMessage();
        Long expected = 10000L;
        msg.setExpirySeconds(expected);
        Assert.assertEquals(expected, msg.getExpirySeconds());
    }

    @Test
    public void IronMQ_Has_A_Version_For_API_Consistency() {
        IronMQMessage msg = new IronMQMessage();
        msg.setMessageVersion("1.0.5");
        Assert.assertEquals("1.0.5", msg.getMessageVersion());
    }

    @Test
    public void IronMQ_Has_A_Version_Default_If_Not_Set() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotEquals(0, msg.getMessageVersion().trim().length());
    }

    @Test
    public void IronMQ_Has_A_Version_That_Is_Not_Null() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotNull(msg.getMessageVersion());
    }

    @Test
    public void IronMQ_Message_Can_Return_A_toJSON() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotNull(msg.toJson());
    }

    @Test
    public void IronMQ_Message_Returns_Json_with_data() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotEquals("", msg.toJson());
    }

    @Test
    public void IronMQ_Message_Returns_A_Date_Object_As_well_as_A_String() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertEquals("Date", msg.getSubmissionDate().getClass().getSimpleName());
    }

    @Test
    public void IronMQ_Message_Has_A_Function_To_Calculate_Expriry_Time_Expected() {
        IronMQMessage msg = new IronMQMessage();
        msg.setExpirySeconds(806400L);
        Assert.assertNotNull(msg.getExpiresDate());
    }

    @Test
    public void IronMQ_Message_ExpiresDate_Should_Be_Set_Properly() {
        IronMQMessage msg = new IronMQMessage();
        long testExpiry = 500L;
        msg.setExpirySeconds(testExpiry);

        Date startDate = msg.getSubmissionDate();

        long expectedResultDate = startDate.getTime();
        Date expectedNewDateTime = new Date(expectedResultDate + (testExpiry * 1000));

        Assert.assertEquals(expectedNewDateTime, msg.getExpiresDate());
    }
}
