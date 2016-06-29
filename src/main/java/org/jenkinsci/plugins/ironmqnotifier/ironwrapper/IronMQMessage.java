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

import com.google.gson.Gson;

import java.util.Date;

class IronMQMessage{

    private String messageVersion;
    private String jobName;
    private String buildResult;
    private Long expirySeconds;
    private Date submissionDate;


    IronMQMessage() {

        IronConstants ironConstants = new IronConstants();

        this.messageVersion = ironConstants.DEF_MESSAGE_VERSION;

        this.jobName = "";
        this.buildResult = "";
        this.expirySeconds = ironConstants.DEF_EXPIRY_SEC;
        this.submissionDate = new Date();


    }

    final String getJobName() {
        return this.jobName;
    }

    final void setJobName( final String name ) {
        this.jobName = name;
    }

    /**
     * <p>Getter for the field <code>buildResult</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBuildResult() {
        return buildResult;
    }

    /**
     * <p>Setter for the field <code>buildResult</code>.</p>
     *
     * @param resultString a {@link java.lang.String} object.
     */
    public void setBuildResult( final String resultString ) {
        this.buildResult = resultString;
    }

    /**
     * <p>Getter for the field <code>expirySeconds</code>.</p>
     *
     * @return a long.
     */

    public long getExpirySeconds() {
        return expirySeconds;

    }

    /**
     * <p>Setter for the field <code>expirySeconds</code>.</p>
     *
     * @param expirySeconds a long.
     */
    public void setExpirySeconds( final long expirySeconds ) {
        this.expirySeconds
                = expirySeconds;

    }

    /**
     * <p>Getter for the field <code>messageVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMessageVersion() {
        return this.messageVersion;
    }

    /**
     * <p>Setter for the field <code>messageVersion</code>.</p>
     *
     * @param messageVersion a {@link java.lang.String} object.
     */
    public void setMessageVersion( final String messageVersion ) {
        this.messageVersion = messageVersion;
    }

    /**
     * <p>toJson.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toJson() {

        MessageBody messageBody = new MessageBody();
        messageBody.setMessageVersion(getMessageVersion());
        messageBody.setJobName(getJobName());
        messageBody.setBuildResult(getBuildResult());
        messageBody.setSubmissionDate(getSubmissionDate());


        Gson gson = new Gson();
        return gson.toJson(messageBody);

    }

    /**
     * <p>Getter for the field <code>submissionDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getSubmissionDate() {
        return submissionDate;
    }

}

