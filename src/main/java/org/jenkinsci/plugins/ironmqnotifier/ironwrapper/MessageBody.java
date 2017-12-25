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

class MessageBody {

    private String messageVersion = "3.0";
    private String jobName;
    private String buildResult;
    private Date submissionDate;


    void setMessageVersion(final String messageVersion) {
        this.messageVersion = messageVersion;
    }

    /**
     * <p>Setter for the field <code>jobName</code>.</p>
     *
     * @param jobName a {@link java.lang.String} object.
     */
    void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    void setBuildResult(final String buildResult) {
        this.buildResult = buildResult;
    }


    void setSubmissionDate(final Date submissionDate) {

        if (submissionDate == null) {

            this.submissionDate = new Date();

        }

        else
        {
            this.submissionDate =  (Date) submissionDate.clone();
        }



    }


    String getJobName() {
        return jobName;
    }

    String getBuildResult() {
        return buildResult;
    }

    String getMessageVersion() {
        return messageVersion;
    }

    Date getSubmissionDate() {


        if ( this.submissionDate == null) {
            return new Date();
        }
        else
        {
            return (Date) this.submissionDate.clone();
        }
    }

}
