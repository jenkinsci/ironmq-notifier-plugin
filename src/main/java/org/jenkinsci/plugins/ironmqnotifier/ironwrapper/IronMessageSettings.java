package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


public class IronMessageSettings {

    private IronConstants ironConstants = new IronConstants();

    private Long expirySeconds;
    private String setBuildResultString;
    private String jobName = "UNKNOWN";
    private String queueName = ironConstants.DEF_QUEUE_NAME;

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
