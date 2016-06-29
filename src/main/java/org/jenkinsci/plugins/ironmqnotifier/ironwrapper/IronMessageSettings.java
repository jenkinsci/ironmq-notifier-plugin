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


public class IronMessageSettings {

    private IronConstants ironConstants = new IronConstants();

    private Long expirySeconds;
    private String setBuildResultString;
    private String jobName = "UNKNOWN";
    private String queueName = ironConstants.DEF_QUEUE_NAME;

    public IronMessageSettings(final String jobName, final String resultString, final String queueName, final Long expirySeconds ) {
        this.jobName = jobName;
        this.setBuildResultString = resultString;
        this.queueName = queueName;
        this.expirySeconds = expirySeconds;
    }


    public IronMessageSettings() {

    }

    public void setExpirySeconds(final Long expirySeconds)  {

        this.expirySeconds = expirySeconds;
    }

    public Long getExpirySeconds() {
        return this.expirySeconds;
    }

    public void setBuildResultString(final String buildResultString) {
        this.setBuildResultString = buildResultString;
    }

    public String getBuildResultString() {
        return this.setBuildResultString;
    }

    public void setJobName(final String jobName) {
     this.jobName = jobName;
    }

    public String getJobName() {
        return this.jobName;
    }

    public String getQueueName() {

        return this.queueName;

    }

    public void setQueueName(final String queueName) {
        if (queueName==null) {
            throw new IllegalArgumentException("queueName Cannot be null");
        }

        else {

            this.queueName = queueName;

        }

    }
}
