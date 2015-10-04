package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;

public class IronMessageSettings {

    private long expirySeconds;
    private String setBuildResultString;
    private String jobName = "UNKNOWN";
    private String queueName = IronConstants.DEF_QUEUE_NAME;

    public void setExpirySeconds(final long expirySeconds)  {

        this.expirySeconds = expirySeconds;
    }

    public long getExpirySeconds() {
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

    public void setQueueName(final String queueName) throws IllegalArgumentException{
        if (queueName==null) {
            throw new IllegalArgumentException("queueName Cannot be null");
        }

        this.queueName = queueName;

    }
}
