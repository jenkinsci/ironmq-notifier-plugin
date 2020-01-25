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
        Object testExpiry = msg.getExpirySeconds();
        String type = testExpiry.getClass().getSimpleName();
        Assert.assertEquals("Long", type);
                    }

    @Test
    public void IronMQ_Message_Expiry_Can_Be_Set_And_Returned() {
        IronMQMessage msg = new IronMQMessage();
        long expected = (long) 10000;
        msg.setExpirySeconds(expected);
        Assert.assertEquals(expected, msg.getExpirySeconds());
    }

    @Test
    public void IronMQ_Has_A_Version_For_API_Consistency() {
        IronMQMessage msg = new IronMQMessage();
        final String testString = "1.0.5";
        msg.setMessageVersion(testString);
        Assert.assertEquals(testString, msg.getMessageVersion());
    }

    @Test
    public void IronMQ_Has_Message_Version_GT_3_By_Default() {
        IronMQMessage msg = new IronMQMessage();

        final String stringToMatch = "3";

        Boolean result = msg.getMessageVersion().startsWith(stringToMatch);


        Assert.assertEquals("Expected a version greater than 3 but was not", true, result);
    }
    @Test
    public void IronMQ_Version_Default_Is_Obtained_From_Constants_For_Easy_Upgrade() {
        IronMQMessage msg = new IronMQMessage();

        IronConstants ironConstants = new IronConstants();

        Assert.assertEquals("Expected a version that matches Constants but was not set", ironConstants.DEF_MESSAGE_VERSION, msg.getMessageVersion());

    }

    @Test
    public void IronMQ_Has_A_Version_Default_If_Not_Set() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertNotSame(0, msg.getMessageVersion().trim().length());
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
        Assert.assertNotSame("", msg.toJson());
    }

    @Test
    public void IronMQ_Message_Returns_A_Date_Object_As_well_as_A_String() {
        IronMQMessage msg = new IronMQMessage();
        Assert.assertEquals("Date", msg.getSubmissionDate().getClass().getSimpleName());
    }




}
