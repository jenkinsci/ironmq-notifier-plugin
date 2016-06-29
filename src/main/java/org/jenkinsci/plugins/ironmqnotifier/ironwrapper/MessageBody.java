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

import java.util.Date;

public class MessageBody {

    private String messageVersion = "3.0";
    private String jobName;
    private String buildResult;
    private Date submissionDate;


    public void setMessageVersion(final String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    public void setBuildResult(final String buildResult) {
        this.buildResult = buildResult;
    }

    public void setSubmissionDate(final Date submissionDate) {

        if (submissionDate == null) {

            this.submissionDate = new Date();

        }

        else
        {
            this.submissionDate =  (Date) submissionDate.clone();
        }



    }


    public String getJobName() {
        return jobName;
    }

    public String getBuildResult() {
        return buildResult;
    }

    public String getMessageVersion() {
        return messageVersion;
    }

    public Date getSubmissionDate() {


        if ( this.submissionDate == null) {
            return new Date();
        }
        else
        {
            return (Date) this.submissionDate.clone();
        }
    }

}
