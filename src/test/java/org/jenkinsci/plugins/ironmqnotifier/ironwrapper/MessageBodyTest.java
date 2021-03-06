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


package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public void An_Empty_SubmissionDate_Defaults_to_today_if_set_to_null () {

        MessageBody messageBody = new MessageBody();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        String expectedDate = dateFormat.format(cal.getTime());

        String functionDate = dateFormat.format(messageBody.getSubmissionDate());

        messageBody.setSubmissionDate(null);

        Assert.assertEquals(expectedDate, functionDate);

    }

    @Test
    public void An_Empty_SubmissionDate_Defaults_to_today_if_date_not_set () {

        MessageBody messageBody = new MessageBody();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        String expectedDate = dateFormat.format(cal.getTime());

        String functionDate = dateFormat.format(messageBody.getSubmissionDate());

        Assert.assertEquals(expectedDate, functionDate);

    }

    @Test
    public void Correct_SubmissionDate_returned_if_set () {

        MessageBody messageBody = new MessageBody();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        String expectedDate = dateFormat.format(cal.getTime());

        String functionDate = dateFormat.format(messageBody.getSubmissionDate());

        messageBody.setSubmissionDate(cal.getTime());

        Assert.assertEquals(expectedDate, functionDate);

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
